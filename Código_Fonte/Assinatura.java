import java.time.LocalDate;

public class Assinatura {

    private Long idAssinatura;
    private Assinante assinante;
    private Plano plano;
    private Cesta cesta;
    private Endereco endereco;
    private Pagamento pagamento;
    private Entrega entrega;
    private String protocolo;
    private String status;
    private LocalDate dataCriacao;

    public Assinatura(Long idAssinatura,
                      Assinante assinante,
                      Plano plano,
                      Cesta cesta,
                      Endereco endereco,
                      Pagamento pagamento,
                      String protocolo) {

        this.idAssinatura = idAssinatura;
        this.assinante = assinante;
        this.plano = plano;
        this.cesta = cesta;
        this.endereco = endereco;
        this.pagamento = pagamento;
        this.protocolo = protocolo;
        this.status = "APROVADA";
        this.dataCriacao = LocalDate.now();
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Long getIdAssinatura() {
        return idAssinatura;
    }

    public String getProtocolo() {
        return protocolo;
    }

    @Override
    public String toString() {
        return "Assinatura #" + idAssinatura +
                " | Protocolo: " + protocolo +
                " | Assinante: " + assinante.getNome() +
                " | Plano: " + plano.getNome();
    }
}
