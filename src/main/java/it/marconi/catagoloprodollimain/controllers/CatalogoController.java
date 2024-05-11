package it.marconi.catagoloprodollimain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.marconi.catagoloprodollimain.domains.Prodotto;
import it.marconi.catagoloprodollimain.services.CatalogoService;

@Controller
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/catalogo")
public String mostraCatalogo(Model model) {
    
    // errore
    List<Prodotto> prodotti = catalogoService.getProdotti();
    model.addAttribute("prodotti", prodotti);
    return "catalogo";
}

    // pagina di nuovo prodotto
    @GetMapping("/prodotto/nuovo")
    public String mostraFormNuovoProdotto(Model model) {
        model.addAttribute("prodotto", new Prodotto(null, null, null, 0, 0));
        return "formNuovoProdotto";
    }

    // aggiunta del prodotto
    @PostMapping("/prodotto/nuovo")
    public String aggiungiProdotto(@ModelAttribute Prodotto prodotto) {
        catalogoService.aggiungiProdotto(prodotto);
        return "redirect:/catalogo";
    }
    
    // visualizzazione dettaglio prodotto
    @GetMapping("/prodotto/{codice}")
    public String mostraDettaglioProdotto(@PathVariable("codice") String codice, Model model) {
        Prodotto prodotto = catalogoService.getCatalogo().stream()
                                .filter(p -> p.getCodice().equals(codice))
                                .findFirst()
                                .orElse(null);
        if (prodotto != null) {
            model.addAttribute("prodotto", prodotto);
            return "dettaglioProdotto";
        } else {
            return "redirect:/catalogo";
        }
    }

    // eliminazione prodotto
    @PostMapping("/prodotto/{codice}/elimina")
    public String eliminaProdotto(@PathVariable("codice") String codice) {
        catalogoService.getCatalogo().removeIf(p -> p.getCodice().equals(codice));
        return "redirect:/catalogo";
    }

    // pagina di modifica prodotto specifico
    @GetMapping("/modifica/{codice}")
    public String mostraFormModifica(@PathVariable("codice") String codice, Model model) {
        Prodotto prodotto = catalogoService.getCatalogo().stream()
                                .filter(p -> p.getCodice().equals(codice))
                                .findFirst()
                                .orElse(null);
        if (prodotto != null) {
            model.addAttribute("prodotto", prodotto);
            return "formModificaProdotto";
        } else {
            return "redirect:/catalogo";
        }
    }

    // modifica prodotto effettiva
    @PostMapping("/modifica/{codice}")
    public String modificaProdotto(@ModelAttribute Prodotto prodotto) {
        // Implementa la logica per la modifica del prodotto
        return "redirect:/catalogo";
    }

    // svuotamento catalogo
    @PostMapping("/catalogo/svuota")
    public String svuotaCatalogo() {
        catalogoService.svuotaCatalogo();
        return "redirect:/catalogo";
    }
}
