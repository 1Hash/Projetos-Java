package hospital.Modelos.Listas;
import hospital.Modelos.Atendimento;
import hospital.Modelos.AuxiliarEnfermagem;
import hospital.Controles.Cliente;
import hospital.Modelos.Enfermeiro;
import hospital.Modelos.EntidadePagadora;
import hospital.Controles.Funcionario;
import hospital.Modelos.Medico;
import java.util.ArrayList;
import java.util.List;

//Listas de objetos: Clientes, Funcionários, Atendimentos e Planos de Saúde
public class Hospital {
    public static List<Cliente> listaCliente = new ArrayList();
    public static List<Atendimento> listaAtendimento = new ArrayList();
    public static List<Funcionario> listaFuncionario = new ArrayList();
    public static List<EntidadePagadora> listaPlanoSaude = new ArrayList();
    public static List<Medico> listaMedico = new ArrayList();
    public static List<Enfermeiro> listaEnfermeiro = new ArrayList();
    public static List<AuxiliarEnfermagem> listaAuxEnf = new ArrayList();
    public static List<Atendimento> listaConsulta = new ArrayList();
    public static List<Atendimento> listaAtendimentosValidados = new ArrayList();
}








