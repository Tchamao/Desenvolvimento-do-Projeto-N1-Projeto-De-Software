import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo {

    private Long idCatalogo;
    private LocalDate dataSemana;
    private List<Produto> produtos;

    public Catalogo(Long idCatalogo, LocalDate dataSemana) {
        this.idCatalogo = idCatalogo;
        this.dataSemana = dataSemana;
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listarPorTipo(TipoProduto tipo) {
        return produtos.stream()
                .filter(p -> p.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    public LocalDate getDataSemana() {
        return dataSemana;
    }
}
