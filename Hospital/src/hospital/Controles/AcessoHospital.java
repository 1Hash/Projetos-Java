package hospital.Controles;

import hospital.Modelos.Atendimento;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hospital.Modelos.Data;
import hospital.Modelos.EntidadePagadora;
import hospital.Modelos.EnumTipoAtendimento;
import hospital.Menu;
import hospital.Modelos.AuxiliarEnfermagem;
import hospital.Modelos.Medico;
import hospital.Modelos.Enfermeiro;
import hospital.Modelos.Listas.Hospital;
import static hospital.Menu.limpar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AcessoHospital {

    public static int cont1 = 0, cont2 = 0, cont3 = 0;

    static XStream stream = new XStream(new DomDriver());
    
    //Vetor para criação de 20 atendimentos
    public static int[] atendimentos = new int[20];

    public static double totalArrecadado = 0;

    public static void menuDono() throws InterruptedException, FileNotFoundException {
        Scanner scanf = new Scanner(System.in);
        Scanner scanf2 = new Scanner(System.in);
        Scanner scanf3 = new Scanner(System.in);
        Scanner scanf4 = new Scanner(System.in);

        Scanner scanf5 = new Scanner(System.in);
        Scanner scanf6 = new Scanner(System.in);
        Scanner scanf7 = new Scanner(System.in);

        int opcao;
        int seleM;
        int seleAt;
        int seleEnf;
        int seleAux;
        int seleDia;
        EnumTipoAtendimento tipoAtendimento = null;
        imprimirMenuDono();

        do {

            opcao = scanf.nextInt();

            switch (opcao) {
                case 1:
                    limpar();
                    listarArrecadacao();
                    break;
                case 2:
                    limpar();
                    System.out.println("---------------------------------------------------------------");
                    for (int i = 0; i < Hospital.listaMedico.size(); i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(Hospital.listaMedico.get(i).nome);
                    }

                    System.out.println("Selecione um médico: ");

                    seleM = scanf.nextInt();

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

                    alocarRecursos(Hospital.listaMedico.get(seleM - 1), tipoAtendimento, Hospital.listaEnfermeiro.get(seleEnf - 1), Hospital.listaAuxEnf.get(seleAux - 1), seleDia);

                    break;
                case 3:
                    limpar();
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("O que deseja cadastrar: ");
                    System.out.println("1 - Médico");
                    System.out.println("2 - Enfermeiro");
                    System.out.println("3 - Auxiliar de enfermagem");

                    int tipoDeFunc = scanf5.nextInt();

                    limpar();
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Deseja cadastrar esse funcionário em qual período de trabalho: ");
                    System.out.println("1 - Da 00:00 as 08:00");
                    System.out.println("2 - Das 08:00 as 16:00");
                    System.out.println("3 - Das 16:00 as 00:00");

                    int periodo = scanf6.nextInt();

                    limpar();

                    criarObjetoFuncionario(tipoDeFunc, periodo);

                    break;
                case 4:
                    Menu.main(new String[0]);
                    break;
                default:
                    imprimirMenuDono();
                    System.out.print("Selecione uma opção valida: ");
                    break;
            }
        } while (opcao != 1 || opcao != 2 || opcao != 3 || opcao != 4);
    }

    public static void imprimirMenuDono() {
        limpar();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Voce está logado(a) como: DONO DO HOSPITAL");
        System.out.println("Por favor, selecione uma opção: ");
        System.out.println("1 - Visualizar arrecadação total");
        System.out.println("2 - Alocar recursos");
        System.out.println("3 - Cadastrar funcionário");
        System.out.println("4 - Sair");
    }

    public static void listarArrecadacao() {

        Scanner scanf = new Scanner(System.in);

        for (int i = 0, j = 1; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).cliente != null) {

                System.out.print(j + " - ");
                j++;
                System.out.print("Paciente: " + Hospital.listaAtendimento.get(i).cliente.nome + " - ");
                System.out.print("Tipo de consulta: " + Hospital.listaAtendimento.get(i).tipoAtendimento + " - ");
                System.out.print("Hora: " + Hospital.listaAtendimento.get(i).hora + ":");
                if (Hospital.listaAtendimento.get(i).minuto == 0) {
                    System.out.print("00 - ");
                } else {
                    System.out.print(Hospital.listaAtendimento.get(i).minuto + " - ");
                }

                System.out.print("Sala: " + Hospital.listaAtendimento.get(i).sala + " - ");
                System.out.print("Medico: " + Hospital.listaAtendimento.get(i).medico.nome + " - ");
                System.out.println("Valor: " + Hospital.listaAtendimento.get(i).valor);

                totalArrecadado += Hospital.listaAtendimento.get(i).valor;
            }
        }

        System.out.println("Total arrecadado em todos os atendimentos: " + totalArrecadado);
        totalArrecadado = 0;

        System.out.println("Pressione uma tecla para continuar...");

        String tecla = scanf.nextLine();

        imprimirMenuDono();
    }

    public static void alocarRecursos(Medico medico, EnumTipoAtendimento tipoAtendimento, Enfermeiro enfermeiro, AuxiliarEnfermagem auxiliarEnfermagem, int dia) {

        Scanner scanf = new Scanner(System.in);

        boolean cond = true;

        for (int i = 0, cont = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico == medico) {

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

        Menu.limpar();

        System.out.println("Recurso alocado!");

        System.out.println("Pressione uma tecla para continuar...");

        String tecla = scanf.nextLine();

        imprimirMenuDono();
    }

    //Ao final do cadastro, adiciona o funcionário na lista de funcionários cadastrados
    public static void cadastrarFuncionario(Funcionario funcionario, Medico medico, Enfermeiro enfermeiro, AuxiliarEnfermagem auxiliarEnfermagem) throws FileNotFoundException {
        Hospital.listaFuncionario.add(funcionario);
        
        stream.toXML(Hospital.listaFuncionario, new FileOutputStream("xml/funcionario.xml"));
        
        if (medico != null) {
            Hospital.listaMedico.add(medico);
            
            stream.toXML(Hospital.listaMedico, new FileOutputStream("xml/medico.xml"));
            
        }
        if (enfermeiro != null) {
            Hospital.listaEnfermeiro.add(enfermeiro);
            
            stream.toXML(Hospital.listaEnfermeiro, new FileOutputStream("xml/enfermeiro.xml"));
            
        }
        if (auxiliarEnfermagem != null) {
            Hospital.listaAuxEnf.add(auxiliarEnfermagem);
            
            stream.toXML(Hospital.listaAuxEnf, new FileOutputStream("xml/auxEnf.xml"));
            
        }

    }

    public static void criarObjetoFuncionario(int opcao, int periodo) throws FileNotFoundException {

        Scanner scanf10 = new Scanner(System.in);

        if (periodo == 1 && cont1 > 4) {
            limpar();
            System.out.println("Todas as salas estão com o primeiro período preenchido!");
            System.out.println("Pressione uma tecla para continuar...");
            String sair = scanf10.nextLine();
        } else if (periodo == 2 && cont2 > 4) {
            limpar();
            System.out.println("Todas as salas estão com o segundo período preenchido!");
            System.out.println("Pressione uma tecla para continuar...");
            String sair = scanf10.nextLine();
        } else if (periodo == 3 && cont3 > 4) {
            limpar();
            System.out.println("Todas as salas estão com o terceiro período preenchido!");
            System.out.println("Pressione uma tecla para continuar...");
            String sair = scanf10.nextLine();
        } else {

            Scanner scanf = new Scanner(System.in);
            Scanner scanf2 = new Scanner(System.in);
            Scanner scanf3 = new Scanner(System.in);
            Scanner scanf4 = new Scanner(System.in);
            Scanner scanf5 = new Scanner(System.in);
            Scanner scanf6 = new Scanner(System.in);
            Scanner scanf7 = new Scanner(System.in);
            Scanner scanf8 = new Scanner(System.in);
            Scanner scanf9 = new Scanner(System.in);

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

            //Funcionário MÉDICO
            if (opcao == 1) {

                System.out.print("CRM: ");
                String crm = scanf8.nextLine();

                Medico medico = new Medico(crm, nome, endereco, cpf, new Data(dia, mes, ano), telefone, email, login, senha, periodo);
                AcessoHospital.cadastrarFuncionario(medico, medico, null, null);

                registrarPeriodo(medico, periodo);
            }

            //Funcionário ENFERMEIRO
            if (opcao == 2) {
                Enfermeiro enfermeiro = new Enfermeiro(nome, endereco, cpf, new Data(dia, mes, ano), telefone, email, login, senha, periodo);
                AcessoHospital.cadastrarFuncionario(enfermeiro, null, enfermeiro, null);
            }

            //Funcionário AUXILIAR DE ENFERMAGEM
            if (opcao == 3) {
                AuxiliarEnfermagem auxiliarEnfermagem = new AuxiliarEnfermagem(nome, endereco, cpf, new Data(dia, mes, ano), telefone, email, login, senha, periodo);
                AcessoHospital.cadastrarFuncionario(auxiliarEnfermagem, null, null, auxiliarEnfermagem);
            }

            limpar();

            System.out.println("Funcionário(a) " + nome + " cadastrado!");

            System.out.println("Pressione uma tecla para continuar...");
            String sair = scanf9.nextLine();

        }

        imprimirMenuDono();
    }

    public static void criarObjetoEntidadePagadora() throws FileNotFoundException {
        Scanner scanf = new Scanner(System.in);

        System.out.print("Plano de saúde: ");
        String nome = scanf.nextLine();

        EntidadePagadora planoSaude = new EntidadePagadora(nome);
        cadastrarEntidadePagadora(planoSaude);
    }

    //Padrão: cada período tem 8 horas ou seja, 1 dia tem 3 períodos
    public static void registrarPeriodo(Medico medico, int periodo) {
        boolean cond = true;
        int reg = 0;

        OUTER:
        for (int i = 0, aux1 = 0, aux2 = 16, aux3 = 32; i < Hospital.listaAtendimento.size(); i++) {
            switch (periodo) {
                case 1:
                    if (Hospital.listaAtendimento.get(aux1).medico == null) {

                        //Registra o médico na sala 1,2,3 no periodo 1
                        cont1++;
                        for (int j = 0; j <= 15; j++) {
                            reg = aux1 + j;

                            Hospital.listaAtendimento.get(reg).medico = medico;
                        }

                        if (cond == true) {
                            aux1 += 48;
                            cond = false;
                        } else {
                            break OUTER;
                        }
                    } else {
                        aux1 += 96;

                    }
                    break;
                case 2:
                    if (Hospital.listaAtendimento.get(aux2).medico == null) {

                        //Registra o médico na sala 1,2,3 no periodo 2
                        cont2++;
                        for (int j = 0; j <= 15; j++) {
                            reg = aux2 + j;

                            Hospital.listaAtendimento.get(reg).medico = medico;
                        }
                        if (cond == true) {
                            aux2 += 48;
                            cond = false;
                        } else {
                            break OUTER;
                        }
                    } else {
                        aux2 += 96;

                    }
                    break;
                case 3:
                    if (Hospital.listaAtendimento.get(aux3).medico == null) {

                        //Registra o médico na sala 1,2,3 no periodo 3
                        cont3++;
                        for (int j = 0; j <= 15; j++) {
                            reg = aux3 + j;

                            Hospital.listaAtendimento.get(reg).medico = medico;
                        }
                        if (cond == true) {
                            aux3 += 48;
                            cond = false;
                        } else {
                            break OUTER;
                        }
                    } else {
                        aux3 += 96;

                    }
                    break;
                default:
                    break;
            }
        }
    }

    //Ao final do cadastro, adiciona o plano de saúde na lista de planos de saúde cadastrados
    public static void cadastrarEntidadePagadora(EntidadePagadora planoSaude) throws FileNotFoundException {
        Hospital.listaPlanoSaude.add(planoSaude);
        
        stream.toXML(Hospital.listaPlanoSaude, new FileOutputStream("xml/planoSaude.xml"));
        
    }

    //Ao final do cadastro, adiciona o atendimento na lista de atendimentos cadastrados
    public static void cadastrarAtendimento(Atendimento atendimento) throws FileNotFoundException {
        Hospital.listaAtendimento.add(atendimento);
        
        stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
        
    }

    public static void criarArquivoXML() throws FileNotFoundException {

        stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
        stream.toXML(Hospital.listaCliente, new FileOutputStream("xml/cliente.xml"));
        stream.toXML(Hospital.listaFuncionario, new FileOutputStream("xml/funcionario.xml"));
        stream.toXML(Hospital.listaMedico, new FileOutputStream("xml/medico.xml"));
        stream.toXML(Hospital.listaEnfermeiro, new FileOutputStream("xml/enfermeiro.xml"));
        stream.toXML(Hospital.listaAuxEnf, new FileOutputStream("xml/auxEnf.xml"));
        stream.toXML(Hospital.listaPlanoSaude, new FileOutputStream("xml/planoSaude.xml"));
        stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
        stream.toXML(Hospital.listaAtendimentosValidados, new FileOutputStream("xml/atendimentosValidados.xml"));
        stream.toXML(CadastroMedicoController.cont1, new FileOutputStream("xml/cont1.xml"));
        stream.toXML(CadastroMedicoController.cont2, new FileOutputStream("xml/cont2.xml"));
        stream.toXML(CadastroMedicoController.cont3, new FileOutputStream("xml/cont3.xml"));

    }

    public static void lerXML() throws FileNotFoundException {
        List<Atendimento> atendimento = (List<Atendimento>) stream.fromXML(new FileInputStream("xml/atendimento.xml"));
        List<Cliente> cliente = (List<Cliente>) stream.fromXML(new FileInputStream("xml/cliente.xml"));
        List<Funcionario> funcionario = (List<Funcionario>) stream.fromXML(new FileInputStream("xml/funcionario.xml"));
        List<Medico> medico = (List<Medico>) stream.fromXML(new FileInputStream("xml/medico.xml"));
        List<Enfermeiro> enfermeiro = (List<Enfermeiro>) stream.fromXML(new FileInputStream("xml/enfermeiro.xml"));
        List<AuxiliarEnfermagem> auxenf = (List<AuxiliarEnfermagem>) stream.fromXML(new FileInputStream("xml/auxEnf.xml"));
        List<EntidadePagadora> planoSaude = (List<EntidadePagadora>) stream.fromXML(new FileInputStream("xml/planoSaude.xml"));
        List<Atendimento> consulta = (List<Atendimento>) stream.fromXML(new FileInputStream("xml/consulta.xml"));
        List<Atendimento> atendimentosValidados = (List<Atendimento>) stream.fromXML(new FileInputStream("xml/atendimentosValidados.xml"));
        int cont1 = (int) stream.fromXML(new FileInputStream("xml/cont1.xml"));
        int cont2 = (int) stream.fromXML(new FileInputStream("xml/cont2.xml"));
        int cont3 = (int) stream.fromXML(new FileInputStream("xml/cont3.xml"));
        
        CadastroMedicoController.cont1 = cont1;
        CadastroMedicoController.cont2 = cont2;
        CadastroMedicoController.cont3 = cont3;
        Hospital.listaAtendimento = atendimento;
        Hospital.listaCliente = cliente;
        Hospital.listaFuncionario = funcionario;
        Hospital.listaMedico = medico;
        Hospital.listaEnfermeiro = enfermeiro;
        Hospital.listaAuxEnf = auxenf;
        Hospital.listaPlanoSaude = planoSaude;
        Hospital.listaConsulta = consulta;
        Hospital.listaAtendimentosValidados = atendimentosValidados;        
    }

    public static void imprimirAtendimento() {
        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            System.out.print(i + 1 + " - ");
            System.out.print("Hora: " + Hospital.listaAtendimento.get(i).hora + ":");
            if (Hospital.listaAtendimento.get(i).minuto == 0) {
                System.out.print("00 - ");
            } else {
                System.out.print(Hospital.listaAtendimento.get(i).minuto + " - ");
            }
        }
    }
    
    public static void criarListaAtendimentos(int sala) throws FileNotFoundException {

        for (int i = 0; i < 96; i++) {
            Atendimento at = new Atendimento(null, null, 0, null, null, null, sala, null, null);
            AcessoHospital.cadastrarAtendimento(at);
        }

    }

    public static void criarRotinaDeTeste() throws FileNotFoundException {

        //Criação de salas (2 dias de atendimentos de 30 minutos)
        criarListaAtendimentos(1);
        criarListaAtendimentos(2);
        criarListaAtendimentos(3);

        //Criação de clientes
        Cliente cli1 = new Cliente("Kamilla Kailer Nogueira", "Rua Rio Catasaltas, 159", "190.887.867-80", new Data(9, 4, 1996), "(41)9953-9228", "kailer2011@live.com", "cli1", "senha1", null);
        Cliente cli2 = new Cliente("João Aparecido dos Santos", "Rua Pernalonga, 25", "290.187.767-10", new Data(21, 7, 1993), "(44)9253-8528", "joaosantos@hotmail.com", "cli2", "senha2", null);
        Cliente cli3 = new Cliente("Rosa Margarida Peixoto", "Av. Cristalina, 1029", "090.885.167-30", new Data(10, 11, 1984), "(42)9777-2328", "margarida_rosa@gmail.com", "cli3", "senha3", null);
        Cliente cli4 = new Cliente("Eduardo da Silva Costa Junior", "Rua Mateus Leme, 423", "100.817.767-90", new Data(24, 1, 1995), "(41)3024-9228", "edursjunior@yahoo.com.br", "cli4", "senha4", null);
        Cliente cli5 = new Cliente("Maria Camargo Fonseca", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "mariaaaa@outlook.com", "cli5", "senha5", null);

        //Add clientes na lista
        Funcionario.cadastrarCliente(cli1);
        Funcionario.cadastrarCliente(cli2);
        Funcionario.cadastrarCliente(cli3);
        Funcionario.cadastrarCliente(cli4);
        Funcionario.cadastrarCliente(cli5);

        //Criação de funcionários
        Medico med1 = new Medico("21594-PR", "Renata de Almeida", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "renataalmeida@outlook.com", "med1", "senha1", 1);
        registrarPeriodo(med1, 1);

        Medico med2 = new Medico("35594-SC", "Junior Casagrande", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(42)9453-5788", "jrcasagrande@hotmail.com", "med2", "senha2", 1);
        registrarPeriodo(med2, 1);

        Medico med3 = new Medico("21594-PR", "Carlos Alberto", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "carlosalberto@outlook.com", "med3", "senha3", 2);
        registrarPeriodo(med3, 2);

        Medico med4 = new Medico("21594-PR", "Paulo Renato Caliari", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "paulorc@outlook.com", "med4", "senha4", 2);
        registrarPeriodo(med4, 2);

        Medico med5 = new Medico("21594-PR", "Pedro de Souza Abramovic", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "pedrosa@outlook.com", "med5", "senha5", 3);
        registrarPeriodo(med5, 3);

        Medico med6 = new Medico("21594-PR", "Adriana Gorski", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "adrianagorski@outlook.com", "med6", "senha6", 3);
        registrarPeriodo(med6, 3);

        Enfermeiro enf1 = new Enfermeiro("Carlos Eduardo Gomes", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "adrianagorski@outlook.com", "enf1", "senha1", 1);
        Enfermeiro enf2 = new Enfermeiro("Francisco Grupenmacher", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "adrianagorski@outlook.com", "enf2", "senha2", 2);

        AuxiliarEnfermagem auxEnf1 = new AuxiliarEnfermagem("Patricia Santos", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "adrianagorski@outlook.com", "auxEnf1", "senha1", 1);
        AuxiliarEnfermagem auxEnf2 = new AuxiliarEnfermagem("Patrick da Silva Soares", "Av. Vereador Toaldo Tulio, 2589", "501.831.264-29", new Data(7, 11, 1992), "(41)9453-5788", "adrianagorski@outlook.com", "auxEnf2", "senha2", 2);

        //Add funcionários da lista
        AcessoHospital.cadastrarFuncionario(med1, med1, null, null);
        AcessoHospital.cadastrarFuncionario(med2, med2, null, null);
        AcessoHospital.cadastrarFuncionario(med3, med3, null, null);
        AcessoHospital.cadastrarFuncionario(med4, med4, null, null);
        AcessoHospital.cadastrarFuncionario(med5, med5, null, null);
        AcessoHospital.cadastrarFuncionario(med6, med6, null, null);
        AcessoHospital.cadastrarFuncionario(enf1, null, enf1, null);
        AcessoHospital.cadastrarFuncionario(enf2, null, enf2, null);
        AcessoHospital.cadastrarFuncionario(auxEnf1, null, null, auxEnf1);
        AcessoHospital.cadastrarFuncionario(auxEnf2, null, null, auxEnf2);

        //Criação de plano de saúde
        EntidadePagadora pSaude1 = new EntidadePagadora("Unimed");
        EntidadePagadora pSaude2 = new EntidadePagadora("Clinipam");

        //Add planos de saúde na lista
        AcessoHospital.cadastrarEntidadePagadora(pSaude1);
        AcessoHospital.cadastrarEntidadePagadora(pSaude2);

        //Cria um objeto para a gerar numeros randomicos
        Random numRand = new Random();

        //Cria 20 numeros aleatorios entre os valores 0 - 96
        for (int i = 0; i < 20; i++) {
            atendimentos[i] = numRand.nextInt(96);
        }

        //Armazena os 20 atendimentos selecionados anteriormente e os seta como true para evitar atendimentos no mesmo horário
        for (int i = 0; i < 96; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == atendimentos[j]) {
                    Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                    Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaMedica;
                    Hospital.listaAtendimento.get(i).cliente = cli1;
                    Hospital.listaAtendimento.get(i).valor = 120.00;
                    Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                }
            }
        }
    }
}
