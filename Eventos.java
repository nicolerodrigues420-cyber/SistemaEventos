import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Eventos {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;
    private boolean participante;

    public Eventos(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participante = false;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public boolean isParticipante() { return participante; }
    public void setParticipante(boolean participante) { this.participante = participante; }

    public void cancelarInscricao() {
        this.participante = false;
    }

    public String getStatus() {
        LocalDateTime agora = LocalDateTime.now();
        if (horario.isAfter(agora)) {
            return "A acontecer";
        } else {
            return "Já ocorreu";
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "\nNome: " + nome +
                "\nEndereço: " + endereco +
                "\nCategoria: " + categoria +
                "\nHorário: " + horario.format(formato) +
                "\nDescrição: " + descricao +
                "\nStatus: " + getStatus();
    }
}