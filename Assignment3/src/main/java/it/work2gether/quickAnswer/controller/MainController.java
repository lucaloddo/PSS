package it.work2gether.quickAnswer.controller;

import it.work2gether.quickAnswer.entity.*;
import it.work2gether.quickAnswer.exception.UtenteRegistratoException;
import it.work2gether.quickAnswer.service.DomandaService;
import it.work2gether.quickAnswer.service.PreventivoService;
import it.work2gether.quickAnswer.service.RispostaService;
import it.work2gether.quickAnswer.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private final UtenteService utenteService;
    private final DomandaService domandaService;
    private final PreventivoService preventivoService;
    private final RispostaService rispostaService;
    private final PasswordEncoder passwordEncoder;

    public MainController(UtenteService utenteService,
                          DomandaService domandaService, PreventivoService preventivoService,
                          RispostaService rispostaService, PasswordEncoder passwordEncoder) {
        this.utenteService = utenteService;
        this.domandaService = domandaService;
        this.preventivoService = preventivoService;
        this.rispostaService = rispostaService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/faq")
    public String faq() {
        return "FAQs";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "reg";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("utente") Utente utente, Model model) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utente.setEnabled(1);
        utente.setRuolo("ROLE_USER");
        utente.setSaldo(0.);
        utente.setRating(0.);
        try {
            utenteService.saveUtente(utente);
            model.addAttribute("finishReg", "Registrazione completata");
        } catch (UtenteRegistratoException e) {
            model.addAttribute("finishReg", "Utente già registrato");
        }
        return "reg";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }


    private <T> List<T> substringTo3IfPossible(List<T> inputList)
    {
        if (inputList.size() < 3) {
            return inputList;
        }
        return inputList.subList(0, 3);
    }

    @GetMapping("/")
    public String index(Authentication auth, Model model) {
        Utente utente = utenteService.findByEmail(auth.getName());

        Map<Domanda, Risposta> risposteMapIn = rispostaService.findRisposteDomandeByIdResponder(utente.getId());
        List<Domanda> domandeTutte = domandaService.getElencoDomandeDisponibiliButAsker(utente.getId());
        List<Domanda> domandeMie = domandaService.getElencoDomandeByAsker(utente.getId());
        List<Preventivo> preventivi = preventivoService.findPreventiviByResponder(utente.getId());

        model.addAttribute("utente", utente);
        model.addAttribute("domandeTutte", substringTo3IfPossible(domandeTutte));
        model.addAttribute("domandeMie", substringTo3IfPossible(domandeMie));
        model.addAttribute("preventivi", substringTo3IfPossible(preventivi));

        Map<Domanda, Risposta>  risposteMapOut = new HashMap<>();
        for (Domanda domanda: substringTo3IfPossible(risposteMapIn.keySet().stream().toList())) {
            Risposta risposta = risposteMapIn.get(domanda);
            if (risposta instanceof RispostaTesto rispostaTesto) {
                String testo = rispostaTesto.getTesto();

                if(testo.length() > 50) {
                    testo = (testo.substring(0, 50) + " ...");
                }
                rispostaTesto.setTesto(testo);
            }
            risposteMapOut.put(domanda, risposta);
        }model.addAttribute("risposte", risposteMapOut.values());

        model.addAttribute("risposte", risposteMapIn);
        return "index";
    }

}
