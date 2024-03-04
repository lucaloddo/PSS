package it.work2gether.quickAnswer.controller;

import it.work2gether.quickAnswer.entity.Preventivo;
import it.work2gether.quickAnswer.entity.Utente;
import it.work2gether.quickAnswer.exception.DomandaNotFoundException;
import it.work2gether.quickAnswer.exception.PreventivoNotFoundException;
import it.work2gether.quickAnswer.exception.SaldoNotEnoughException;
import it.work2gether.quickAnswer.service.DomandaService;
import it.work2gether.quickAnswer.service.PreventivoService;
import it.work2gether.quickAnswer.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("preventivi")
public class PreventivoController {
    private final PreventivoService preventivoService;
    private final UtenteService utenteService;
    private final DomandaService domandaService;

    public PreventivoController(PreventivoService preventivoService,
                                UtenteService utenteService,
                                DomandaService domandaService) {
        this.preventivoService = preventivoService;
        this.utenteService = utenteService;
        this.domandaService = domandaService;
    }

    @GetMapping("/elencoMieiPreventivi")
    public String elencoPreventivi(Model model, Authentication auth) {
        Utente utente = utenteService.findByEmail(auth.getName());
        model.addAttribute("preventivi", preventivoService.findPreventiviByResponder(utente.getId()));
        return "elencoMieiPreventivi";
    }

    @PostMapping("/inviaPreventivo")
    public String inviaPreventivo(@RequestParam("id") Integer domandaId) {
        return "preventivoForm";
    }

    @PostMapping("/accettaPreventivo")
    public String accettaPreventivo(@RequestParam("idPreventivo") Integer idPreventivo,
                                    @RequestParam("idDomanda") Integer idDomanda, Authentication auth,
                                    RedirectAttributes redirectedAttributes) {
        try {
            Preventivo preventivo = preventivoService.findById(idPreventivo);
            Utente utente = utenteService.findByEmail(auth.getName());
            utenteService.changeSaldo(utente, -preventivo.getPrezzo());
            utenteService.changeSaldo(preventivo.getUtenteResponder(), preventivo.getPrezzo());
            preventivoService.acceptPreventivo(idPreventivo);
        } catch (PreventivoNotFoundException e) {
            return "redirect:/error";
        } catch (SaldoNotEnoughException e) {
            redirectedAttributes.addFlashAttribute("preventivoAccettatoSaldoInsuff", "Saldo insufficiente");
            redirectedAttributes.addFlashAttribute("preventivoAccettatoIdPreventivoScelto", idPreventivo);
            return "redirect:/domande/domandaSingola/" + idDomanda.toString();
        }
        return "redirect:/domande/elencoMieDomande";
    }


    @PostMapping("/inserisciPreventivo")
    public String inserisciPreventivo(@ModelAttribute("preventivo") Preventivo preventivo,
                                      @RequestParam("domandaId") Integer domandaId, Authentication auth) {
        preventivo.setUtenteResponder(utenteService.findByEmail(auth.getName()));
        try {
            preventivo.setDomanda(domandaService.findById(domandaId));
        } catch (DomandaNotFoundException e) {
            throw new RuntimeException(e);
        }
        preventivo.setData(LocalDate.now());
        preventivo.setIsAccettato(false);
        preventivoService.savePreventivo(preventivo);
        return "redirect:/";
    }

}
