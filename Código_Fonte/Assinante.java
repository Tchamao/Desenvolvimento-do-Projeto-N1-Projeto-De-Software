public class Assinante {

    private Long idAssinante;
    private String nome;
    private String celular;
    private String email;

    public Assinante(Long idAssinante, String nome, String celular, String email) {
        this.idAssinante = idAssinante;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public Long getIdAssinante() {
        return idAssinante;
    }

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }
}
