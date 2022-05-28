package hospital.Controles;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hospital.Modelos.Data;
import hospital.Modelos.EnumTipoAtendimento;
import hospital.Menu;
import hospital.Modelos.Listas.Hospital;
import hospital.Modelos.Pessoa;
import static hospital.Menu.limpar;
import hospital.Modelos.EntidadePagadora;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Cliente extends Pessoa {
    
    static XStream stream = new XStream(new DomDriver());
    
    public EntidadePagadora planoSaude;

    public Cliente(String nome, String endereco, String cpf, Data dtNasc, String telefone, String email, String login, String senha, EntidadePagadora planoSaude) {
        super(nome, endereco, cpf, dtNasc, telefone, email, login, senha);
        this.planoSaude = planoSaude;
    }

    public static void menuCliente(String usuario) throws InterruptedException, FileNotFoundException {

        Scanner scanf = new Scanner(System.in);

        int opcao;
        imprimirMenuCliente();

        do {

            opcao = scanf.nextInt();

            switch (opcao) {
                case 1:
                    limpar();
                    Cliente.marcarAtendimento(usuario);
                    break;
                case 2:
                    limpar();
                    Cliente.visualizarAtendimento(usuario);
                    break;
                case 3:
                    Menu.main(new String[0]);
                    break;
                default:
                    imprimirMenuCliente();
                    System.out.print("Selecione uma opção valida: ");
                    break;
            }
        } while (opcao != 1 || opcao != 2 || opcao != 3);
    }

    public static void imprimirMenuCliente() {
        limpar();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Voce está logado(a) como: PACIENTE");
        System.out.println("Por favor, selecione uma opção: ");
        System.out.println("1 - Marcar atendimento");
        System.out.println("2 - Visualizar atendimentos");
        System.out.println("3 - Sair");
    }

    public static void marcarAtendimento(String usuario) throws InterruptedException, FileNotFoundException {

        Scanner scanf = new Scanner(System.in);
        Scanner scanf3 = new Scanner(System.in);
        Scanner scanf4 = new Scanner(System.in);

        Menu.mostrarHorariosDisponiveisTESTE();

        int horario;
        int plano;
        int planoSel;

        do {

            horario = scanf.nextInt();

            if (Hospital.listaAtendimento.get(horario - 1).horarioDisponivel == false) {
                Hospital.listaAtendimento.get(horario - 1).horarioDisponivel = true;

                for (int j = 0; j < Hospital.listaCliente.size(); j++) {
                    if (Hospital.listaCliente.get(j).login.equals(usuario)) {
                        Hospital.listaAtendimento.get(horario - 1).cliente = Hospital.listaCliente.get(j);
                        
                        Hospital.listaAtendimento.get(horario - 1).tipoAtendimento = EnumTipoAtendimento.ConsultaMedica;

                        do {
                            Menu.limpar();
                            System.out.println("Possui um plano de saude?");
                            System.out.println("1 - SIM");
                            System.out.println("2 - NÃO");
                            plano = scanf3.nextInt();

                        } while (plano != 1 && plano != 2);

                        if (plano == 1) {
                            Menu.limpar();
                            for (int i = 0; i < Hospital.listaPlanoSaude.size(); i++) {
                                System.out.print(i + 1 + " - ");
                                System.out.println(Hospital.listaPlanoSaude.get(i).nome);
                            }
                            System.out.println("Escolha o seu plano de saude: ");
                            planoSel = scanf.nextInt();
                            Hospital.listaAtendimento.get(horario - 1).planoSaude = Hospital.listaPlanoSaude.get(planoSel - 1);

                            Menu.limpar();

                            System.out.println("O valor da sua consulta foi coberta pelo seu plano de saúde!");
                            Hospital.listaAtendimento.get(horario - 1).valor = 120.0;

                        } else {
                            Menu.limpar();
                            System.out.println("O valor da sua consulta particular é de R$ 120,00");
                            Hospital.listaAtendimento.get(horario - 1).valor = 120.0;
                        }

                        System.out.println("Atendimento Agendado!");
                        
                        Hospital.listaConsulta.add(Hospital.listaAtendimento.get(horario - 1));
                        
                        stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                        stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                        

                        System.out.println("Pressione uma tecla para continuar...");
                        String tecla = scanf4.nextLine();
                        menuCliente(usuario);
                    }
                }
            } else {
                Menu.limpar();
                Menu.mostrarHorariosDisponiveisTESTE();
                System.out.print("Escolha um horário disponível: ");
            }
        } while (Hospital.listaAtendimento.get(horario - 1).horarioDisponivel == true);

    }

    public static void visualizarAtendimento(String usuario) throws InterruptedException, FileNotFoundException {

        Scanner scanf = new Scanner(System.in);

        System.out.println("Atendimentos agendados: ");

        for (int j = 0, c = 1; j < Hospital.listaAtendimento.size(); j++) {
            if (Hospital.listaAtendimento.get(j).cliente != null) {
                if (Hospital.listaAtendimento.get(j).cliente.login.equals(usuario)) {

                    System.out.print(c + " - ");
                    c++;
                    System.out.print("Tipo de consulta: " + Hospital.listaAtendimento.get(j).tipoAtendimento + " - ");
                    System.out.print("Hora: " + Hospital.listaAtendimento.get(j).hora + ":");
                    if (Hospital.listaAtendimento.get(j).minuto == 0) {
                        System.out.print("00 - ");
                    } else {
                        System.out.print(Hospital.listaAtendimento.get(j).minuto + " - ");
                    }

                    System.out.print("Sala: " + Hospital.listaAtendimento.get(j).sala + " - ");
                    System.out.print("Valor: " + Hospital.listaAtendimento.get(j).valor + " - ");
                    System.out.println("Medico: " + Hospital.listaAtendimento.get(j).medico.nome);
                }
            }
        }

        System.out.println("Pressione uma tecla para continuar...");

        String tecla = scanf.nextLine();
        menuCliente(usuario);
    }
}
