public class Cartao {

    private String nomeImpresso;
    private String numero;
    private String bandeira;
    private int validadeMes;
    private int validadeAno;

    public Cartao(String nomeImpresso, String numero, String bandeira,
                  int validadeMes, int validadeAno) {
        this.nomeImpresso = nomeImpresso;
        this.numero = numero;
        this.bandeira = bandeira;
        this.validadeMes = validadeMes;
        this.validadeAno = validadeAno;
    }

    public String getNumero() {
        return numero;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public String getBandeira() {
        return bandeira;
    }

    public int getValidadeMes() {
        return validadeMes;
    }

    public int getValidadeAno() {
        return validadeAno;
    }

    public String getNumeroMascarado() {
        if (numero == null || numero.length() < 4) return "****";
        return "**** **** **** " + numero.substring(numero.length() - 4);
    }
}
