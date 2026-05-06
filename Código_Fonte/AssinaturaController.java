import java.math.BigDecimal;
import java.util.List;

public class AssinaturaController {

    private final AssinaturaService assinaturaService;
    private final PagamentoService pagamentoService;

    public AssinaturaController(AssinaturaService assinaturaService,
                                PagamentoService pagamentoService) {
        this.assinaturaService = assinaturaService;
        this.pagamentoService = pagamentoService;
    }

    public List<Plano> obterPlanos() {
        return assinaturaService.listarPlanos();
    }

    public List<Produto> obterProdutosPorTipo(TipoProduto tipo) {
        return assinaturaService.getCatalogo().listarPorTipo(tipo);
    }

    public Cesta criarCesta() {
        return assinaturaService.criarCesta();
    }

    public BigDecimal calcularValor(Plano plano) {
        return assinaturaService.calcularValorTotal(plano);
    }

    public Pagamento realizarPagamento(Cartao cartao, BigDecimal valor) {
        return pagamentoService.processarPagamento(cartao, valor);
    }

    public Assinatura finalizarAssinatura(Assinante assinante,
                                          Plano plano,
                                          Cesta cesta,
                                          Endereco endereco,
                                          Pagamento pagamento) {

        String protocolo = assinaturaService.gerarProtocolo();

        Assinatura assinatura = assinaturaService.criarAssinatura(
                assinante,
                plano,
                cesta,
                endereco,
                pagamento,
                protocolo
        );

        assinaturaService.agendarEntrega(assinatura);

        return assinatura;
    }
}
