package hospital.Controles;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hospital.Modelos.Data;
import hospital.Modelos.EnumFuncionarios;
import hospital.Modelos.EnumTipoAtendimento;
import hospital.Menu;
import hospital.Modelos.AuxiliarEnfermagem;
import hospital.Modelos.Enfermeiro;
import hospital.Modelos.Listas.Hospital;
import hospital.Modelos.Pessoa;
import static hospital.Menu.limpar;
import hospital.Modelos.EntidadePagadora;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Funcionario extends Pessoa {

    static XStream stream = new XStream(new DomDriver());

    public int periodo;

    public Funcionario(String nome, String endereco, String cpf, Data dtNasc, String telefone, String email, String login, String senha, int periodo) {
        super(nome, endereco, cpf, dtNasc, telefone, email, login, senha);
    }

    public static void menuEnfAuxEnf(String usuario, EnumFuncionarios tipoFuncionario) throws InterruptedException, FileNotFoundException {

        Scanner scanf = new Scanner(System.in);

        int opcao;

        if (tipoFuncionario == EnumFuncionarios.Enfermeiro) {
            imprimirMenuEnfermeiro();
        } else if (tipoFuncionario == EnumFuncionarios.AuxiliarEnfermagem) {
            imprimirMenuAuxEnf();
        }

        do {

            opcao = scanf.nextInt();

            switch (opcao) {
                case 1:
                    limpar();
                    Funcionario.visualizarGrade(usuario, tipoFuncionario);
                    break;
                case 2:
                    limpar();
                    Funcionario.visualizarTotalRendimento(usuario, tipoFuncionario);
                    break;
                case 3:
                    limpar();
                    criarObjetoCliente(tipoFuncionario);
                    break;
                case 4:
                    limpar();
                    Funcionario.cadastrarConsultaEmergencia(usuario, tipoFuncionario);
                    break;
                case 5:
                    Menu.main(new String[0]);
                    break;
                default:

                    if (tipoFuncionario == EnumFuncionarios.Enfermeiro) {
                        imprimirMenuEnfermeiro();
                    } else if (tipoFuncionario == EnumFuncionarios.AuxiliarEnfermagem) {
                        imprimirMenuAuxEnf();
                    }

                    System.out.print("Selecione uma opção valida: ");
                    break;
            }
        } while (opcao != 1 || opcao != 2 || opcao != 3);
    }

    public static void menuMedico(String usuario, EnumFuncionarios tipoFuncionario) throws InterruptedException, FileNotFoundException {

        Scanner scanf = new Scanner(System.in);
        Scanner scanf2 = new Scanner(System.in);
        Scanner scanf3 = new Scanner(System.in);
        Scanner scanf4 = new Scanner(System.in);
        Scanner scanf7 = new Scanner(System.in);

        int opcao;
        int seleAt;
        int seleEnf;
        int seleAux;
        int seleDia;
        EnumTipoAtendimento tipoAtendimento = null;

        imprimirMenuMedico();

        do {

            opcao = scanf.nextInt();

            switch (opcao) {
                case 1:
                    limpar();
                    Funcionario.visualizarGrade(usuario, tipoFuncionario);
                    break;
                case 2:
                    limpar();
                    Funcionario.visualizarTotalRendimento(usuario, tipoFuncionario);
                    break;
                case 3:
                    limpar();
                    criarObjetoCliente(tipoFuncionario);
                    break;
                case 4:
                    limpar();
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Selecione o tipo de atendimento: ");
                    System.out.println("1 - Cirurgia");
                    System.out.println("2 - Internação");

                    seleAt = scanf2.nextInt();

                    if (seleAt == 1) {
                        tipoAtendimento = EnumTipoAtendimento.Cirurgia;
                    } else if (seleAt == 2) {
                        tipoAtendimento = EnumTipoAtendimento.Internacao;
                    }

                    limpar();
                    System.out.println("---------------------------------------------------------------");
                    for (int i = 0; i < Hospital.listaEnfermeiro.size(); i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(Hospital.listaEnfermeiro.get(i).nome);
                    }
                    System.out.println("Selecione um enfermeiro: ");

                    seleEnf = scanf3.nextInt();

                    limpar();
                    System.out.println("---------------------------------------------------------------");
                    for (int i = 0; i < Hospital.listaAuxEnf.size(); i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(Hospital.listaAuxEnf.get(i).nome);
                    }
                    System.out.println("Selecione um auxiliar de enfermagem: ");

                    seleAux = scanf4.nextInt();

                    limpar();
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Selecione o dia: ");
                    System.out.println("1 - Primeiro dia");
                    System.out.println("2 - Segundo dia");

                    seleDia = scanf7.nextInt();

                    alocarRecursos(usuario, tipoAtendimento, Hospital.listaEnfermeiro.get(seleEnf - 1), Hospital.listaAuxEnf.get(seleAux - 1), seleDia);
                    break;
                case 5:
                    validarAtendimentos(usuario);
                    break;
                case 6:
                    Menu.main(new String[0]);
                    break;
                default:
                    imprimirMenuMedico();
                    System.out.print("Selecione uma opção valida: ");
                    break;
            }
        } while (opcao != 1 || opcao != 2 || opcao != 3 || opcao != 4 || opcao != 5 || opcao != 6);
    }

    public static void imprimirMenuMedico() {
        limpar();

        System.out.println("---------------------------------------------------------------");
        System.out.println("Voce está logado(a) como: MÉDICO");
        System.out.println("Por favor, selecione uma opção: ");
        System.out.println("1 - Visualizar sua grade de trabalho");
        System.out.println("2 - Visualizar o seu rendimento");
        System.out.println("3 - Cadastrar cliente");
        System.out.println("4 - Cadastrar cirurgia ou internação");
        System.out.println("5 - Validar Atendimento");
        System.out.println("6 - Sair");
    }

    public static void imprimirMenuEnfermeiro() {
        limpar();

        System.out.println("---------------------------------------------------------------");
        System.out.println("Voce está logado(a) como: ENFERMEIRO ");
        System.out.println("Por favor, selecione uma opção: ");
        System.out.println("1 - Visualizar sua grade de trabalho");
        System.out.println("2 - Visualizar o seu rendimento");
        System.out.println("3 - Cadastrar cliente");
        System.out.println("4 - Cadastrar consulta de emergência");
        System.out.println("5 - Sair");
    }

    public static void imprimirMenuAuxEnf() {
        limpar();

        System.out.println("---------------------------------------------------------------");
        System.out.println("Voce está logado(a) como: AUXILIAR DE ENFERMAGEM");
        System.out.println("Por favor, selecione uma opção: ");
        System.out.println("1 - Visualizar sua grade de trabalho");
        System.out.println("2 - Visualizar o seu rendimento");
        System.out.println("3 - Cadastrar cliente");
        System.out.println("4 - Cadastrar consulta de emergência");
        System.out.println("5 - Sair");
    }

    public static void criarObjetoCliente(EnumFuncionarios tipoFuncionario) throws FileNotFoundException {
        Scanner scanf = new Scanner(System.in);
        Scanner scanf2 = new Scanner(System.in);
        Scanner scanf3 = new Scanner(System.in);
        Scanner scanf4 = new Scanner(System.in);
        Scanner scanf5 = new Scanner(System.in);
        Scanner scanf6 = new Scanner(System.in);
        Scanner scanf7 = new Scanner(System.in);
        Scanner scanf8 = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanf.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanf2.nextLine();

        System.out.print("CPF: ");
        String cpf = scanf3.nextLine();

        System.out.println("Data de nascimento: ");
        System.out.print("Dia: ");
        int dia = scanf.nextInt();
        System.out.print("Mes: ");
        int mes = scanf2.nextInt();
        System.out.print("Ano: ");
        int ano = scanf3.nextInt();

        System.out.print("Telefone: ");
        String telefone = scanf4.nextLine();

        System.out.print("E-mail: ");
        String email = scanf5.nextLine();

        System.out.print("Usuario: ");
        String login = scanf6.nextLine();

        System.out.print("Senha: ");
        String senha = scanf7.nextLine();
        
        EntidadePagadora s = null;

        Cliente cliente = new Cliente(nome, endereco, cpf, new Data(dia, mes, ano), telefone, email, login, senha, s);

        Funcionario.cadastrarCliente(cliente);

        limpar();
        System.out.println("Paciente " + nome + " cadastrado!");

        System.out.println("Pressione uma tecla para continuar...");
        String sair = scanf8.nextLine();

        if (tipoFuncionario == EnumFuncionarios.Medico) {
            imprimirMenuMedico();
        } else if (tipoFuncionario == EnumFuncionarios.Enfermeiro) {
            imprimirMenuEnfermeiro();
        } else if (tipoFuncionario == EnumFuncionarios.AuxiliarEnfermagem) {
            imprimirMenuAuxEnf();
        }
    }

    public static void visualizarGrade(String usuario, EnumFuncionarios tipoFuncionario) throws InterruptedException, FileNotFoundException {

        Scanner scanf = new Scanner(System.in);

        System.out.println("Grade de trabalho: ");

        for (int i = 0, c = 1; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico != null) {
                if (Hospital.listaAtendimento.get(i).medico.login.equals(usuario)) {
                    System.out.print(c + " - ");
                    c++;
                    System.out.print("Hora: " + Hospital.listaAtendimento.get(i).hora + ":");
                    if (Hospital.listaAtendimento.get(i).minuto == 0) {
                        System.out.print("00 - ");
                    } else {
                        System.out.print(Hospital.listaAtendimento.get(i).minuto + " - ");
                    }

                    System.out.print("Sala: " + Hospital.listaAtendimento.get(i).sala + " - ");
                    System.out.println("Tipo de atendimento: " + Hospital.listaAtendimento.get(i).tipoAtendimento);

                } else if (Hospital.listaAtendimento.get(i).enfermeiro != null) {
                    if (Hospital.listaAtendimento.get(i).enfermeiro.login.equals(usuario) || Hospital.listaAtendimento.get(i).auxiliarEnfermagem.login.equals(usuario)) {
                        System.out.print(c + " - ");
                        c++;
                        System.out.print("Tipo de consulta: " + Hospital.listaAtendimento.get(i).tipoAtendimento + " - ");
                        System.out.print("Hora: " + Hospital.listaAtendimento.get(i).hora + ":");
                        if (Hospital.listaAtendimento.get(i).minuto == 0) {
                            System.out.print("00 - ");
                        } else {
                            System.out.print(Hospital.listaAtendimento.get(i).minuto + " - ");
                        }

                        System.out.print("Sala: " + Hospital.listaAtendimento.get(i).sala + " - ");
                        System.out.println("Tipo de atendimento: " + Hospital.listaAtendimento.get(i).tipoAtendimento);
                    }
                }
            } else {
                if (c == 1) {
                    System.out.println("Voce não possui nenhuma grade de trabalho ainda!");
                }
                break;
            }
        }

        System.out.println("Pressione uma tecla para continuar...");
        String tecla = scanf.nextLine();

        if (tipoFuncionario == EnumFuncionarios.Medico) {
            menuMedico(usuario, tipoFuncionario);
        } else if (tipoFuncionario == EnumFuncionarios.Enfermeiro || tipoFuncionario == EnumFuncionarios.AuxiliarEnfermagem) {
            menuEnfAuxEnf(usuario, tipoFuncionario);
        }
    }

    //Lista o total de rendimento (valor) das consultas feitas pelo médico
    public static void visualizarTotalRendimento(String usuario, EnumFuncionarios tipoFuncionario) throws InterruptedException, FileNotFoundException {
        Scanner scanf = new Scanner(System.in);
        double totalRendimento = 0;
        for (int i = 0, c = 1; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico != null) {
                if (Hospital.listaAtendimento.get(i).medico.login.equals(usuario)) {
                    if (Hospital.listaAtendimento.get(i).cliente != null) {
                        totalRendimento += Hospital.listaAtendimento.get(i).valor;
                    }
                }
            }
        }
        System.out.println("O total de rendimento é " + totalRendimento);
        System.out.println("Pressione uma tecla para continuar...");
        String tecla = scanf.nextLine();

        if (tipoFuncionario == EnumFuncionarios.Medico) {
            menuMedico(usuario, tipoFuncionario);
        } else if (tipoFuncionario == EnumFuncionarios.Enfermeiro || tipoFuncionario == EnumFuncionarios.AuxiliarEnfermagem) {
            menuEnfAuxEnf(usuario, tipoFuncionario);
        }
    }

    //Ao final do cadastro, adiciona cliente na lista de clientes cadastrados
    public static void cadastrarCliente(Cliente cliente) throws FileNotFoundException {
        Hospital.listaCliente.add(cliente);
        stream.toXML(Hospital.listaCliente, new FileOutputStream("xml/cliente.xml"));
    }

    public static void cadastrarConsultaEmergencia(String usuario, EnumFuncionarios tipoFuncionario) throws InterruptedException, FileNotFoundException {

        Scanner scanf = new Scanner(System.in);
        Scanner scanf2 = new Scanner(System.in);

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).tipoAtendimento != EnumTipoAtendimento.ConsultaEmergencia) {
                System.out.print(i + 1 + " - ");
                System.out.print("Hora: " + Hospital.listaAtendimento.get(i).hora + ":");
                if (Hospital.listaAtendimento.get(i).minuto == 0) {
                    System.out.print("00 - ");
                } else {
                    System.out.print(Hospital.listaAtendimento.get(i).minuto + " - ");
                }
                System.out.println("Sala: " + Hospital.listaAtendimento.get(i).sala);
            }
        }

        System.out.println("Escolha um horário para cadastrar uma consulta de emergencia: ");
        int valor = scanf2.nextInt();

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (i == (valor - 1)) {
                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
            }
        }

        limpar();

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).tipoAtendimento == EnumTipoAtendimento.ConsultaEmergencia) {
                System.out.print(i + 1 + " - ");
                System.out.print("Hora: " + Hospital.listaAtendimento.get(i).hora + ":");
                if (Hospital.listaAtendimento.get(i).minuto == 0) {
                    System.out.print("00 - ");
                } else {
                    System.out.print(Hospital.listaAtendimento.get(i).minuto + " - ");
                }
                System.out.print("Sala: " + Hospital.listaAtendimento.get(i).sala + " - ");
                System.out.println("Tipo de Atendimento: " + Hospital.listaAtendimento.get(i).tipoAtendimento);
            }
        }

        stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));

        System.out.println("Pressione uma tecla para continuar...");
        String tecla = scanf.nextLine();
        menuEnfAuxEnf(usuario, tipoFuncionario);
    }

    public static void alocarRecursos(String usuario, EnumTipoAtendimento tipoAtendimento, Enfermeiro enfermeiro, AuxiliarEnfermagem auxiliarEnfermagem, int dia) throws FileNotFoundException {

        Scanner scanf = new Scanner(System.in);

        boolean cond = true;

        for (int i = 0, cont = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico.login.equals(usuario)) {

                if (dia == 1) {
                    cont++;
                    Hospital.listaAtendimento.get(i).tipoAtendimento = tipoAtendimento;
                    Hospital.listaAtendimento.get(i).enfermeiro = enfermeiro;
                    Hospital.listaAtendimento.get(i).auxiliarEnfermagem = auxiliarEnfermagem;
                    Hospital.listaAtendimento.get(i).valor = 240.0;

                    if (cont >= 16) {
                        break;
                    }

                } else if (dia == 2) {
                    if (cond == true) {
                        i += 48;
                        cond = false;
                    }

                    Hospital.listaAtendimento.get(i).tipoAtendimento = tipoAtendimento;
                    Hospital.listaAtendimento.get(i).enfermeiro = enfermeiro;
                    Hospital.listaAtendimento.get(i).auxiliarEnfermagem = auxiliarEnfermagem;

                }
            }
        }

        stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));

        limpar();

        System.out.println("Recurso alocado!");

        System.out.println("Pressione uma tecla para continuar...");

        String tecla = scanf.nextLine();

        imprimirMenuMedico();
    }

    public static void validarAtendimentos(String usuario) throws FileNotFoundException {

        limpar();

        Scanner scanf = new Scanner(System.in);
        Scanner scanf2 = new Scanner(System.in);

        int cont = 0;

        for (int i = 0; i < Hospital.listaConsulta.size(); i++) {
            if (Hospital.listaConsulta.get(i).medico.login.equals(usuario)) {
                cont++;
                System.out.print(i + 1 + " - ");
                System.out.print("Hora: " + Hospital.listaConsulta.get(i).hora + ":");
                if (Hospital.listaConsulta.get(i).minuto == 0) {
                    System.out.print("00 - ");
                } else {
                    System.out.print(Hospital.listaConsulta.get(i).minuto + " - ");
                }
                System.out.print("Sala: " + Hospital.listaConsulta.get(i).sala + " - ");
                System.out.print("Médico(a): " + Hospital.listaConsulta.get(i).medico.nome + " - ");
                System.out.println("Cliente: " + Hospital.listaConsulta.get(i).cliente.nome);
            }

        }

        if (cont == 0) {
            System.out.println("Não existe nenhum atendimento para validar!");
        } else {

            System.out.println("Escolha uma consulta para validar: ");
            int valor = scanf.nextInt();

            for (int j = 0; j < Hospital.listaConsulta.size(); j++) {
                if (j == (valor - 1)) {
                    Hospital.listaAtendimentosValidados.add(Hospital.listaConsulta.get(j));
                    Hospital.listaConsulta.remove(j);
                }
            }

            stream.toXML(Hospital.listaAtendimentosValidados, new FileOutputStream("xml/atendimentosValidados.xml"));
            stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
        }

        limpar();
        
        if (cont == 0) {
            System.out.println("Não existe nenhum atendimento para validar!");
        } else {
            System.out.println("Atendimento validado!!");
        }

        System.out.println("Pressione uma tecla para continuar...");

        String tecla = scanf2.nextLine();

        imprimirMenuMedico();
    }
}
