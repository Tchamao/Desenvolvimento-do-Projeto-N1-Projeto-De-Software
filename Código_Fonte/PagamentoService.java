import java.math.BigDecimal;

public class PagamentoService {
    public Pagamento processarPagamento(Cartao cartao, BigDecimal valor) {
        Pagamento pagamento = new Pagamento(1L, valor, cartao);
        pagamento.aprovar(); // Sempre aprova para demo
        return pagamento;
    }
}
