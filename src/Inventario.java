import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Inventario {
    private List<Prodotto> listaProdotti = new ArrayList<>();

    public void aggiungiProdotto(Prodotto prodotto) throws Exception{
        Optional<Prodotto> pt = listaProdotti.stream().filter(p -> p.getNome().equals(prodotto.getNome())).findAny();
        if(pt.isPresent()){
            throw new Exception("Prodotto già esistente " + prodotto.getNome());
        }
        listaProdotti.add(prodotto);
    }

    //Rimuovere un prodotto (lanciare un'eccezione se il prodotto non esiste).

    public void rimuoviProdotto(Prodotto prodotto) throws Exception{
        listaProdotti
                .stream()
                .filter(p -> p.getNome().equals(prodotto.getNome()))
                .findFirst()
                .map(p-> listaProdotti.remove(p)).orElseThrow(() -> new Exception("Prodotto non trovato"));
    }

    // Cercare un prodotto per nome (restituire il prodotto o lanciare un'eccezione se non trovato).

    public Prodotto cercaPerNome(String nomeProdotto) throws Exception{
        for(Prodotto element : listaProdotti){
            if(element.getNome().equals(nomeProdotto)){
                return element;
            }
        }
        throw new Exception("Prodotto non trovato");
    }

    public void stampaProdottiByCategoria(String categoria){
        Categoria c = Categoria.valueOf(categoria);
       // listaProdotti.stream().filter(p-> p.getCategoria().name().equals(categoria)).map(l -> System.out.println(l.toString())).toList();
        listaProdotti.forEach(p->{
            if(p.getCategoria().equals(c)){
                System.out.println(p);
            }

        });
    }

    //Stampare la dimensione dell'inventario e verificare se è vuoto.
    public int dimensioneInventario() throws Exception{
        if(listaProdotti.isEmpty()){
            throw new Exception("Lista vuota");
        }
         return listaProdotti.size();
    }

    //Ordinare i prodotti per prezzo o per nome.

    public void ordinaProdotti(){
        listaProdotti.sort(Comparator.comparingDouble(p->p.getPrezzo()));
    }

    public void rimuoviProdottiByCategoria(String categoria){
        listaProdotti = listaProdotti.stream().filter(p-> !p.getCategoria().name().equals(categoria)).toList();
        //
        listaProdotti.removeIf(p->p.getCategoria().name().equals(categoria));
    }

}
