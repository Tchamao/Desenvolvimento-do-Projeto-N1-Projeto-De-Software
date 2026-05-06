import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cesta {

    private Long idCesta;
    private LocalDate dataSemana;
    private List<ItemCesta> itens;

    public Cesta(Long idCesta, LocalDate dataSemana) {
        this.idCesta = idCesta;
        this.dataSemana = dataSemana;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemCesta(produto, quantidade));
    }

    public List<ItemCesta> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cesta da semana " + dataSemana + ":\n");
        for (ItemCesta item : itens) {
            sb.append(" - ").append(item).append("\n");
        }
        return sb.toString();
    }
}
