public class Endereco {

    private Long idEndereco;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;

    public Endereco(Long idEndereco, String logradouro, String numero, String complemento,
                    String bairro, String cidade, String cep) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return logradouro + ", " + numero +
                (complemento == null || complemento.isBlank() ? "" : " - " + complemento) +
                " - " + bairro + " - " + cidade + " - " + cep;
    }
}
