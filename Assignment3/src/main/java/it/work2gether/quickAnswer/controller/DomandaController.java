package it.work2gether.quickAnswer.controller;

import it.work2gether.quickAnswer.entity.Domanda;
import it.work2gether.quickAnswer.entity.Preventivo;
import it.work2gether.quickAnswer.entity.Risposta;
import it.work2gether.quickAnswer.entity.Utente;
import it.work2gether.quickAnswer.exception.DomandaNotFoundException;
import it.work2gether.quickAnswer.service.DomandaService;
import it.work2gether.quickAnswer.service.PreventivoService;
import it.work2gether.quickAnswer.service.RispostaService;
import it.work2gether.quickAnswer.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDate.now;

@Controller
@RequestMapping("domande")
public class DomandaController {
    private final UtenteService utenteService;
    private final DomandaService domandaService;
    private final PreventivoService preventivoService;
    private final RispostaService rispostaService;

    public DomandaController(UtenteService utenteService, DomandaService domandaService,
                             PreventivoService preventivoService, RispostaService rispostaService) {
        this.utenteService = utenteService;
        this.domandaService = domandaService;
        this.preventivoService = preventivoService;
        this.rispostaService = rispostaService;
    }

    @GetMapping("/elencoTutteDomande")
    public String elencoDomande(Model model, Authentication auth) {
        Utente utente = utenteService.findByEmail(auth.getName());
        List<Domanda> domande = domandaService.getElencoDomandeDisponibiliButAsker(utente.getId());
        model.addAttribute("domanda", domande);
        return "elencoTutteDomande";
    }

    @GetMapping("/elencoMieDomande")
    public String elencoMieDomande(Authentication auth, Model model) {
        Utente utente = utenteService.findByEmail(auth.getName());
        model.addAttribute("domande", domandaService.getElencoDomandeByAsker(utente.getId()));
        return "elencoMieDomande";
    }

    @GetMapping("/poniDomanda")
    public String poniDomanda() {
        return "poniDomanda";
    }

    @PostMapping("/inserisciDomanda")
    public String inserisciDomanda(@ModelAttribute("domanda") Domanda domanda, Authentication auth) {
        domanda.setData(now());
        domanda.setUtenteAsker(utenteService.findByEmail(auth.getName()));
        domandaService.saveDomanda(domanda);
        return "redirect:/";
    }

    private String manageDomandaNotFound(Model model) {

        model.addAttribute("domanda", null);
        model.addAttribute("errorMsg", "Domanda non trovata");
        return "domandaSingola";
    }

    @GetMapping("/domandaSingola/{idDomanda}")
    public String domandaSingola(@PathVariable Integer idDomanda, Authentication auth, Model model) {
        Utente utente = utenteService.findByEmail(auth.getName());
        Domanda domanda = null;
        try {
            domanda = domandaService.findById(idDomanda);
        } catch (DomandaNotFoundException e) {
            return manageDomandaNotFound(model);
        }

        model.addAttribute("domanda", domanda);
        model.addAttribute("preventivi", preventivoService.findPreventiviByDomanda(domanda.getId()));

        Preventivo preventivoAccettato = preventivoService.findPreventivoAccettatoByDomanda(domanda.getId());
        model.addAttribute("preventivoAccettato", preventivoAccettato);

        Risposta risposta = null;
        if (preventivoAccettato != null) {
            risposta = rispostaService.findRispostaByPreventivo(preventivoAccettato.getId());
        }
        model.addAttribute("risposta", risposta);

        return "domandaSingola";
    }

    @GetMapping(path = {"/domandaSingola", "/domandaSingola/"})
    public String domandaSingolaNotFound(Model model) {
        return manageDomandaNotFound(model);
    }

}
