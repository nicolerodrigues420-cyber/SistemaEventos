import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorEventos gerenciador = new GerenciadorEventos();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- SISTEMA DE EVENTOS ---");
            System.out.println("1. Cadastrar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Listar Eventos Ocorrendo Agora");
            System.out.println("4. Sair");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    gerenciador.listarEventos();
                    break;
                case 3:
                    gerenciador.listarEventosOcorrendoAgora();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private static void cadastrarEvento() {
        String nome = lerTexto("Nome do evento: ");
        String endereco = lerTexto("Endereço: ");
        String categoria = lerTexto("Categoria: ");
        String dataHoraStr = lerTexto("Data e Horário (dd/MM/yyyy HH:mm): ");
        String descricao = lerTexto("Descrição: ");

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime horario = LocalDateTime.parse(dataHoraStr, formato);
            Eventos novoEvento = new Eventos(nome, endereco, categoria, horario, descricao);
            gerenciador.cadastrarEvento(novoEvento);
            System.out.println("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro no formato da data/horário. Use o formato: dd/MM/yyyy HH:mm");
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite un número válido.");
            }
        }
    }
}