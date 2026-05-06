import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssinaturaService {
    private List<Plano> planos;
    private Catalogo catalogo;
    private long sequenciaCesta = 1;
    private long sequenciaAssinatura = 1;

    public AssinaturaService() {
        this.planos = CSVLoader.carregarPlanos("Plano.csv");
        if (this.planos == null || this.planos.isEmpty()) {
            System.out.println("Plano.csv não encontrado. Usando exemplo.");
            this.planos = criarPlanosExemplo();
        }
        this.catalogo = new Catalogo(1L, LocalDate.now());
        List<Produto> produtos = CSVLoader.carregarProdutos("Produto.csv");
        if (produtos == null || produtos.isEmpty()) {
            produtos = criarProdutosExemplo();
        }
        for (Produto p : produtos) {
            this.catalogo.adicionarProduto(p);
        }
    }

    private List<Plano> criarPlanosExemplo() {
        List<Plano> lista = new ArrayList<>();
        lista.add(new Plano(1L, "basico", new BigDecimal("100.00"), 2, 2, 2));
        lista.add(new Plano(2L, "basico plus", new BigDecimal("150.00"), 4, 4, 4));
        lista.add(new Plano(3L, "premium", new BigDecimal("200.00"), 8, 8, 4));
        return lista;
    }

    private List<Produto> criarProdutosExemplo() {
        List<Produto> lista = new ArrayList<>();
        lista.add(new Produto(1L, "Banana", TipoProduto.FRUTA));
        lista.add(new Produto(2L, "Maçã", TipoProduto.FRUTA));
        lista.add(new Produto(16L, "Cenoura", TipoProduto.LEGUME));
        lista.add(new Produto(31L, "Alface", TipoProduto.VERDURA));
        return lista;
    }

    public boolean validarCodigoSms(String codigoDigitado, String codigoGerado) {
        return "123456".equals(codigoDigitado);
    }

    public List<Plano> listarPlanos() {
        return new ArrayList<>(planos);
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public Cesta criarCesta() {
        return new Cesta(sequenciaCesta++, LocalDate.now());
    }

    public BigDecimal calcularValorTotal(Plano plano) {
        return plano.getValorBase();
    }

    public Assinatura criarAssinatura(Assinante assinante, Plano plano, Cesta cesta, 
                                    Endereco endereco, Pagamento pagamento, String protocolo) {
        return new Assinatura(sequenciaAssinatura++, assinante, plano, cesta, endereco, pagamento, protocolo);
    }

    public Entrega agendarEntrega(Assinatura assinatura) {
        Entrega entrega = new Entrega(1L, LocalDate.now().plusDays(3), "08:00 - 12:00");
        assinatura.setEntrega(entrega);
        return entrega;
    }

    public String gerarProtocolo() {
        return "PRT-" + System.currentTimeMillis();
    }
}
