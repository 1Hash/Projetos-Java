package hospital.Controles;

import hospital.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CadastrarFuncionarioController implements Initializable {

    @FXML
    private Button cadastrarMedico;
    @FXML
    private Button cadastrarEnfermeiro;
    @FXML
    private Button cadastrarAuxiliarEnf;
    @FXML
    private Button Sair;
    @FXML
    private Label logado;

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void cadastrarMedico(ActionEvent event) {
        application.CadastrarMedico(application.getUsuarioLogado(), true);
    }

    @FXML
    private void cadastrarEnfermeiro(ActionEvent event) {
        application.CadastrarEnfermeiro(application.getUsuarioLogado(), true);
    }

    @FXML
    private void cadastrarAuxiliarEnf(ActionEvent event) {
        application.CadastrarAuxEnf(application.getUsuarioLogado(), true);
    }

    @FXML
    private void Sair(ActionEvent event) {
        application.DonoLogado(application.getUsuarioLogado(), true);
    }
}