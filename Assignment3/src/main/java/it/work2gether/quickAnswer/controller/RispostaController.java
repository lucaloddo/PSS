package it.work2gether.quickAnswer.controller;

import it.work2gether.quickAnswer.entity.*;
import it.work2gether.quickAnswer.exception.RispostaNotFoundException;
import it.work2gether.quickAnswer.service.PreventivoService;
import it.work2gether.quickAnswer.service.RispostaService;
import it.work2gether.quickAnswer.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("risposte")
public class RispostaController {
    private final RispostaService rispostaService;
    private final PreventivoService preventivoService;
    private final UtenteService utenteService;

    public RispostaController(RispostaService rispostaService, PreventivoService preventivoService, UtenteService utenteService) {
        this.rispostaService = rispostaService;
        this.preventivoService = preventivoService;
        this.utenteService = utenteService;
    }

    @GetMapping("/elencoRisposte")
    public String risposte(Authentication auth, Model model) {
        Utente utente = utenteService.findByEmail(auth.getName());
        Map<Domanda, Risposta> risposte = rispostaService.findRisposteDomandeByIdResponder(utente.getId());
        model.addAttribute("risposte", risposte);
        return "elencoRisposte";
    }

    @PostMapping("/visualizzaRisposta/{tipoUtente}")
    public String visualizzaRisposta(@RequestParam("idRisposta") Integer idRisposta,
                                     @PathVariable("tipoUtente") String tipoUtente, Model model) {
        try {
            Risposta risposta = rispostaService.findRispostaById(idRisposta);
            model.addAttribute("risposta", risposta);
        } catch (RispostaNotFoundException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("tipoUtente", tipoUtente);

        return "rispostaSingola";
    }

    @PostMapping("/inviaRisposta")
    public String inviaRisposta(@RequestParam("id") Integer idPreventivo) {
        return "rispostaForm";
    }

    @PostMapping("/inserisciRispostaTesto")
    public String inserisciRispostaTesto(@RequestParam("preventivoId") Integer idPreventivo,
                                         @ModelAttribute("rispostaTesto") RispostaTesto risposta) {
        risposta.setData(LocalDateTime.now());
        risposta.setRating(0);
        RispostaTesto rispostaSalvata = rispostaService.saveRispostaTesto(risposta);
        preventivoService.updateRispostaInPreventivo(idPreventivo, rispostaSalvata.getId());
        return "redirect:/";
    }

    @PostMapping("/inserisciRispostaVideo")
    public String inserisciRispostaVideo(@RequestParam("preventivoId") Integer idPreventivo,
                                         @ModelAttribute("rispostaVideo") RispostaVideo risposta) {
        risposta.setData(LocalDateTime.now());
        risposta.setRating(0);
        String video = risposta.getVideo();
        risposta.setVideo(risposta.getVideo().substring(video.lastIndexOf("=") + 1));
        RispostaVideo rispostaSalvata = rispostaService.saveRispostaVideo(risposta);
        preventivoService.updateRispostaInPreventivo(idPreventivo, rispostaSalvata.getId());
        return "redirect:/";
    }

    @PostMapping("/processRating")
    public String processaRating(@RequestParam("idRisposta") Integer idRisposta,
                                 @RequestParam("selectedRating") Integer selectedRating) {
        rispostaService.updateRatingRisposta(idRisposta, selectedRating);
        Integer idResponder = rispostaService.findResponderByRisposta(idRisposta).getId();
        List<Risposta> risposte = rispostaService.findRisposteRatedByResponder(idResponder);

        Double newRating = 0.0;
        for (Risposta risposta : risposte) {
            newRating += risposta.getRating();
        }

        newRating = newRating / risposte.size();

        utenteService.updateRatingUtente(idResponder, newRating);
        return "redirect:/";
    }
}
