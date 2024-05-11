package it.marconi.catagoloprodollimain.services;
import org.springframework.stereotype.Service;

import it.marconi.catagoloprodollimain.domains.Prodotto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogoService {
    private List<Prodotto> catalogo = new ArrayList<>();

    public CatalogoService() {
        // dati di esempio
        catalogo.add(new Prodotto("3241", "Prodotto 1", "Categoria 1", 2020, 10));
        catalogo.add(new Prodotto("5423", "Prodotto 2", "Categoria 2", 2019, 15));

    }

    public List<Prodotto> getCatalogo() {
        return catalogo;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        catalogo.add(prodotto);
    }

    public void svuotaCatalogo() {
        catalogo.clear();
    }

    
}
