public class Produto {

    private Long idProduto;
    private String nome;
    private TipoProduto tipo;

    public Produto(Long idProduto, String nome, TipoProduto tipo) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return idProduto + " - " + nome + " (" + tipo + ")";
    }
}
