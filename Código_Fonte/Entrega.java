import java.time.LocalDate;

public class Entrega {

    private Long idEntrega;
    private LocalDate dataPrevista;
    private String janelaHorario;
    private String status;

    public Entrega(Long idEntrega, LocalDate dataPrevista, String janelaHorario) {
        this.idEntrega = idEntrega;
        this.dataPrevista = dataPrevista;
        this.janelaHorario = janelaHorario;
        this.status = "PENDENTE";
    }

    @Override
    public String toString() {
        return "Entrega em " + dataPrevista + " (" + janelaHorario + ") - " + status;
    }
}
