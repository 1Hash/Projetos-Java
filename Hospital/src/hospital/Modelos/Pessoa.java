package hospital.Modelos;

public class Pessoa {
  
    public String nome;
    protected String endereco;
    public String cpf;
    protected Data dtNasc;
    protected String telefone;
    protected String email;
    public String login;
    public String senha;
    
    //private static final Map<String, Pessoa> USERS = new HashMap<String, Pessoa>();

    /*public static Pessoa of(String id) {
        Pessoa user = USERS.get(id);
        if (user == null) {
            user = new Pessoa(id);
            USERS.put(id, user);
        }
        return user;
    }*/

    public Pessoa(String nome, String endereco, String cpf, Data dtNasc, String telefone, String email, String login, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.telefone = telefone;
        this.email = email;
        this.login = login;
        this.senha = senha;

    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public Data getDtNasc() {
        return dtNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
    
}
