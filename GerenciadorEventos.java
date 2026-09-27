import java.util.ArrayList;
import java.util.List;

public class GerenciadorEventos {
    private List<Eventos> eventos;

    public GerenciadorEventos() {
        this.eventos = ArquivoEventos.carregar();
    }

    public void cadastrarEvento(Eventos evento) {
        eventos.add(evento);
        salvar();
    }

    public void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getNome() + " (" + eventos.get(i).getStatus() + ")");
        }
    }

    public void listarEventosOcorrendoAgora() {
        boolean encontrou = false;
        for (Eventos evento : eventos) {
            if ("A acontecer".equals(evento.getStatus())) {
                System.out.println(evento);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum evento ocorrendo agora.");
        }
    }

    private boolean indiceValido(int indice) {
        if (indice < 0 || indice >= eventos.size()) {
            System.out.println("Evento inválido.");
            return false;
        }
        return true;
    }

    private void salvar() {
        ArquivoEventos.salvar(eventos);
    }

    public List<Eventos> getEventos() {
        return eventos;
    }
}