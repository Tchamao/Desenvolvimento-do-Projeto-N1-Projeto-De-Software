import java.math.BigDecimal;
public class Plano {

    private Long idPlano;
    private String nome;
    private BigDecimal valorBase;
    private int qtdFrutasPermitidas;
    private int qtdLegumesPermitidos;
    private int qtdVerdurasPermitidas;

    public Plano(Long idPlano, String nome, BigDecimal valorBase,
                 int qtdFrutasPermitidas, int qtdLegumesPermitidos, int qtdVerdurasPermitidas) {
        this.idPlano = idPlano;
        this.nome = nome;
        this.valorBase = valorBase;
        this.qtdFrutasPermitidas = qtdFrutasPermitidas;
        this.qtdLegumesPermitidos = qtdLegumesPermitidos;
        this.qtdVerdurasPermitidas = qtdVerdurasPermitidas;
    }

    public Long getIdPlano() {
        return idPlano;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public int getQtdFrutasPermitidas() {
        return qtdFrutasPermitidas;
    }

    public int getQtdLegumesPermitidos() {
        return qtdLegumesPermitidos;
    }

    public int getQtdVerdurasPermitidas() {
        return qtdVerdurasPermitidas;
    }

    @Override
    public String toString() {
        return idPlano + " - " + nome + " (R$ " + valorBase + ")";
    }
}
