package hospital.Controles;

import hospital.Modelos.Listas.Hospital;
import hospital.Menu;
import hospital.Modelos.Data;
import hospital.Modelos.EntidadePagadora;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroClienteController implements Initializable {

    @FXML
    private TextField nome;
    @FXML
    private TextField endereco;
    @FXML
    private TextField cpf;
    @FXML
    private TextField telefone;
    @FXML
    private TextField email;
    @FXML
    private TextField login;
    @FXML
    private TextField senha;

    private Menu application;
    @FXML
    private TextField dia;
    @FXML
    private TextField mes;
    @FXML
    private TextField ano;
    @FXML
    private Label cadastrado;
    @FXML
    private ComboBox<String> planoSaude;
    private ComboBox<EntidadePagadora> planoSaude2;

    List<String> listaPlanos = new ArrayList<>();

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listaPlanos.add("Não possuo plano de saúde");
        
        for (int i = 0; i < Hospital.listaPlanoSaude.size(); i++) {
            listaPlanos.add(Hospital.listaPlanoSaude.get(i).nome);
        }

        ObservableList<String> plano = FXCollections.observableArrayList(listaPlanos);
        planoSaude.setItems(plano);

    }

    @FXML
    private void botaoCadastrar(ActionEvent event) throws FileNotFoundException, InterruptedException {

        boolean condCadastro = true;

        for (int i = 0; i < Hospital.listaCliente.size(); i++) {
            if (login.getText().equals(Hospital.listaCliente.get(i).login)) {
                condCadastro = false;
            }
        }

        String valor = planoSaude.getSelectionModel().getSelectedItem();
        EntidadePagadora ps = null;

        if ("Não possuo plano de saúde".equals(valor)) {
            
            ps = null;
            
        } else {

            for (int i = 0; i < Hospital.listaPlanoSaude.size(); i++) {
                if (valor.equals(Hospital.listaPlanoSaude.get(i).nome)) {
                    ps = Hospital.listaPlanoSaude.get(i);
                }
            }
        }

        if (condCadastro) {

            int diaint = Integer.parseInt(dia.getText());
            int mesint = Integer.parseInt(mes.getText());
            int anoint = Integer.parseInt(ano.getText());

            Cliente cliente = new Cliente(nome.getText(), endereco.getText(), cpf.getText(), new Data(diaint, mesint, anoint), telefone.getText(), email.getText(), login.getText(), senha.getText(), ps);

            Funcionario.cadastrarCliente(cliente);

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("");
            dialogoInfo.setHeaderText("O paciente " + nome.getText() + " foi cadastrado com sucesso!");
            dialogoInfo.setContentText("Clique em OK para voltar ao menu!");
            dialogoInfo.showAndWait();

            application.EnfLogado(application.getUsuarioLogado(), true);

        } else {
            cadastrado.setText("Usuario " + login.getText() + " já existe, escolha outro!");
        }
    }

    @FXML
    private void botaoVoltar(ActionEvent event) throws InterruptedException, IOException {

        boolean loginMed = false;
        boolean loginAuxEnf = false;
        boolean loginEnf = false;

        for (int i = 0; i < Hospital.listaMedico.size(); i++) {
            if (application.getUsuarioLogado().equals(Hospital.listaMedico.get(i).login)) {
                loginMed = true;
            }
        }

        for (int i = 0; i < Hospital.listaAuxEnf.size(); i++) {
            if (application.getUsuarioLogado().equals(Hospital.listaAuxEnf.get(i).login)) {
                loginAuxEnf = true;
            }
        }

        for (int i = 0; i < Hospital.listaEnfermeiro.size(); i++) {
            if (application.getUsuarioLogado().equals(Hospital.listaEnfermeiro.get(i).login)) {
                loginEnf = true;
            }
        }

        if (loginMed == true) {
            application.MedicoLogado(application.getUsuarioLogado(), loginMed);

        } else if (loginEnf == true) {
            application.EnfLogado(application.getUsuarioLogado(), loginEnf);

        } else if (loginAuxEnf == true) {
            application.AuxEnfLogado(application.getUsuarioLogado(), loginAuxEnf);
        }
    }
}
