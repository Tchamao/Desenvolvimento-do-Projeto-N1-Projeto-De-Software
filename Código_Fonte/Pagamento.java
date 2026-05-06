import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento {

    public enum StatusPagamento {
        PENDENTE, APROVADO, RECUSADO
    }

    private Long idPagamento;
    private BigDecimal valor;
    private StatusPagamento status;
    private LocalDateTime dataPagamento;
    private Cartao cartao;

    public Pagamento(Long idPagamento, BigDecimal valor, Cartao cartao) {
        this.idPagamento = idPagamento;
        this.valor = valor;
        this.cartao = cartao;
        this.status = StatusPagamento.PENDENTE;
    }

    public void aprovar() {
        this.status = StatusPagamento.APROVADO;
        this.dataPagamento = LocalDateTime.now();
    }

    public void recusar() {
        this.status = StatusPagamento.RECUSADO;
        this.dataPagamento = LocalDateTime.now();
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
