package it.work2gether.quickAnswer.controller;

import it.work2gether.quickAnswer.entity.Commento;
import it.work2gether.quickAnswer.entity.Utente;
import it.work2gether.quickAnswer.exception.UtenteNotFoundException;
import it.work2gether.quickAnswer.service.CommentoService;
import it.work2gether.quickAnswer.service.RispostaService;
import it.work2gether.quickAnswer.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("commenti")
public class CommentoController {
    private final CommentoService commentoService;
    private final UtenteService utenteService;
    private final RispostaService rispostaService;

    public CommentoController(CommentoService commentoService, UtenteService utenteService,
                              RispostaService rispostaService) {
        this.commentoService = commentoService;
        this.utenteService = utenteService;
        this.rispostaService = rispostaService;
    }

    @GetMapping(path = {"/elencoCommenti/{idUtente}", "/elencoCommenti"})
    public String elencoCommenti(@PathVariable(required = false) Integer idUtente, Authentication auth, Model model) {
        Utente commentato;
        String headerCommenti;
        if (idUtente != null) {
            try {
                commentato = utenteService.findById(idUtente);
                headerCommenti = "I commenti di " + commentato.getNickname() + ":";
            } catch (UtenteNotFoundException e) {
                return "redirect:/error";
            }
        } else {
            commentato = utenteService.findByEmail(auth.getName());
            headerCommenti = "I tuoi commenti:";
        }


        model.addAttribute("headerCommenti", headerCommenti);
        model.addAttribute("commenti", commentoService.findCommentiByIdReceiver(commentato.getId()));

        return "elencoCommenti";
    }

    @PostMapping("/nuovoCommento")
    public String nuovoCommento(@ModelAttribute("newCommento") Commento newCommento,
                                @RequestParam("commentatoId") Integer commentatoId, Authentication auth) {
        Utente commentatore = utenteService.findByEmail(auth.getName());
        newCommento.setData(LocalDateTime.now());
        newCommento.setUtenteCommentatore(commentatore);

        try {
            newCommento.setUtenteCommentato(utenteService.findById(commentatoId));
        } catch (UtenteNotFoundException e) {
            return "redirect:/error";
        }

        newCommento.setCommentatoreAcquirente(!rispostaService.findRisposteByAskerResponderUntil(commentatore.getId(),
                commentatoId, LocalDateTime.now()).isEmpty());

        commentoService.saveCommento(newCommento);
        return "redirect:/";
    }


    @GetMapping("/elencoCommentiFatti")
    public String elencoCommentiFatti(Model model, Authentication auth) {
        Utente utente = utenteService.findByEmail(auth.getName());
        model.addAttribute("commenti", commentoService.findCommentiByIdSender(utente.getId()));
        return "elencoCommentiFatti";
    }

    @PostMapping("/eliminaCommentoProcess")
    public String eliminaCommentoProcess(@RequestParam("idCommento") Integer idCommento) {
        commentoService.deleteCommento(idCommento);
        return "redirect:/commenti/elencoCommentiFatti";
    }

}
