package it.work2gether.quickAnswer.controller;

import it.work2gether.quickAnswer.dto.ProfiloPubblicoDto;
import it.work2gether.quickAnswer.entity.Utente;
import it.work2gether.quickAnswer.exception.SaldoNotEnoughException;
import it.work2gether.quickAnswer.exception.UtenteNotFoundException;
import it.work2gether.quickAnswer.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("utenti")
public class UtenteController {
    private final UtenteService utenteService;
    private final PasswordEncoder passwordEncoder;

    public UtenteController(UtenteService utenteService, PasswordEncoder passwordEncoder) {
        this.utenteService = utenteService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profilo")
    public String profile(Authentication auth, Model model) {
        Utente utente = utenteService.findByEmail(auth.getName());
        model.addAttribute("utente", utente);
        return "profilo";
    }

    @GetMapping("/modificaProfilo")
    public String editProfile(Authentication auth, Model model) {
        Utente utente = utenteService.findByEmail(auth.getName());
        model.addAttribute("utente", utente);
        return "modificaProfilo";
    }

    @PostMapping("/processModificaProfilo")
    public String processModificaProfilo(@ModelAttribute("newProfile") Utente newProfile, Authentication auth) {

        Utente oldProfile = utenteService.findByEmail(auth.getName());
        newProfile.setId(oldProfile.getId());
        newProfile.setRating(oldProfile.getRating());
        newProfile.setRuolo(oldProfile.getRuolo());
        newProfile.setSaldo(oldProfile.getSaldo());
        newProfile.setEnabled(oldProfile.getEnabled());

        if (newProfile.getPassword().equals("")) {
            newProfile.setPassword(oldProfile.getPassword());
        } else {
            newProfile.setPassword(passwordEncoder.encode(newProfile.getPassword()));
        }

        utenteService.updateUtente(newProfile);

        return "redirect:/utenti/profilo";
    }


    @GetMapping("/profiloPubblico")
    public String profiloPubblico(@RequestParam(name = "nickname", required = false) String nicknameInserito,
                                  Model model, Authentication auth) {
        Integer utenteCorrenteId = utenteService.findByEmail(auth.getName()).getId();
        ProfiloPubblicoDto utenteTrovato = null;

        try {
            if (nicknameInserito != null) {
                utenteTrovato = utenteService.publicFindByNickname(nicknameInserito);
            } else {
                utenteTrovato = utenteService.publicFindByEmail(auth.getName());
            }

        } catch (UtenteNotFoundException e) {
            model.addAttribute("errorMsg", "Utente non trovato");
        }

        if (utenteTrovato != null && !utenteTrovato.getId().equals(utenteCorrenteId)) {
            model.addAttribute("ableToComment", true);
        } else {
            model.addAttribute("ableToComment", false);
        }

        model.addAttribute("profiloTrovato", utenteTrovato);
        return "profiloPubblico";
    }

    private void changeSaldo(Authentication auth, Double valore) throws SaldoNotEnoughException {
        utenteService.changeSaldo(utenteService.findByEmail(auth.getName()), valore);
    }

    @PostMapping("/depositaSaldo")
    public String depositaSaldo(@ModelAttribute("valoreDeposito") Double valoreDeposito,
                                Authentication auth) {
        try {
            changeSaldo(auth, valoreDeposito);
        } catch (SaldoNotEnoughException e) {
            return "redirect:/error";
        }
        return "redirect:/utenti/profilo";
    }

    @PostMapping("/prelevaSaldo")
    public String prelevaSaldo(@ModelAttribute("valorePrelievo") Double valorePrelievo, Authentication auth) {
        try {
            changeSaldo(auth, -valorePrelievo);
        } catch (SaldoNotEnoughException e) {
            return "redirect:/utenti/profilo";
        }
        return "redirect:/utenti/profilo";
    }

}

