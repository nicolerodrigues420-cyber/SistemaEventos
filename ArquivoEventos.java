import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArquivoEventos {
    private static final String ARQUIVO = "events.data";

    public static void salvar(List<Eventos> eventos) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Eventos evento : eventos) {
                escritor.write(
                        evento.getNome() + ";" +
                                evento.getEndereco() + ";" +
                                evento.getCategoria() + ";" +
                                evento.getHorario() + ";" +
                                evento.getDescricao() + ";" +
                                evento.isParticipante()
                );
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os eventos.");
        }
    }

    public static List<Eventos> carregar() {
        List<Eventos> eventos = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return eventos;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";", -1);
                if (dados.length >= 6) {
                    Eventos evento = new Eventos(
                            dados[0],
                            dados[1],
                            dados[2],
                            LocalDateTime.parse(dados[3]),
                            dados[4]
                    );
                    if (Boolean.parseBoolean(dados[5])) {
                        evento.setParticipante(true);
                    }
                    eventos.add(evento);
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Erro ao carregar os eventos.");
        }
        return eventos;
    }
}