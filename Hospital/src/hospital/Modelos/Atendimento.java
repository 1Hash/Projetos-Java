package hospital.Modelos;

import hospital.Controles.Cliente;

public class Atendimento {

    public Cliente cliente;
    public EnumTipoAtendimento tipoAtendimento;
    public double valor;
    public Data data;
    public Medico medico;
    public EntidadePagadora planoSaude;
    public static int contador = 0;
    public int hora;
    public static int contadorHora = 0;
    public int minuto;
    public boolean horarioDisponivel;
    public int sala;
    public Enfermeiro enfermeiro;
    public AuxiliarEnfermagem auxiliarEnfermagem;

    public Atendimento(Cliente cliente, EnumTipoAtendimento tipoAtendimento, double valor, Data data, Medico medico, EntidadePagadora planoSaude, int sala, Enfermeiro enfermeiro, AuxiliarEnfermagem auxiliarEnfermagem) {
        
        this.cliente = cliente;
        this.tipoAtendimento = tipoAtendimento;
        this.valor = valor;
        this.data = data;
        this.medico = medico;
        this.planoSaude = planoSaude;
        this.horarioDisponivel = false;
        this.sala = sala;
        this.enfermeiro = enfermeiro;
        this.auxiliarEnfermagem = auxiliarEnfermagem;
        
        //CONTADOR PAR = MINUTO 00
        //CONTADOR IMPAR = MINUTO 30
        if (contador%2 == 0) {
            this.minuto = 0; 
            this.hora = contadorHora;
            contador++;
        } else {
            this.hora = contadorHora;
            this.minuto = 30;
            contadorHora++;
            if (contadorHora == 24) {
                contadorHora = 0;
            }
            contador++;
        }
    }  
}
