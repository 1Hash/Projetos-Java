package hospital.Modelos;

import hospital.Controles.Funcionario;

public class AuxiliarEnfermagem extends Funcionario {

    public AuxiliarEnfermagem(String nome, String endereco, String cpf, Data dtNasc, String telefone, String email, String login, String senha, int periodo) {
        super(nome, endereco, cpf, dtNasc, telefone, email, login, senha, periodo);
    }
}
