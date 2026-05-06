public class ItemCesta {

    private Produto produto;
    private int quantidade;

    public ItemCesta(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return produto.getNome() + " x " + quantidade;
    }
}
