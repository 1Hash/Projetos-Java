package hospital.Controles;

import hospital.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuAuxEnfController implements Initializable {

    @FXML
    private Button visualizarGrade;
    @FXML
    private Button visualizarRendimento;
    @FXML
    private Button cadastrarCliente;
    @FXML
    private Button cadastrarConsEm;
    
    private Menu application;
    @FXML
    private Button Sair;
    @FXML
    private Label logado;
    
    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logado.setText("Logado como " + Menu.ul);
    }    

    @FXML
    private void botaoVisualizarGrade(ActionEvent event) {
        application.VisualizarGrade(application.getUsuarioLogado(), true);
    }

    @FXML
    private void botaoVisualizarRendimento(ActionEvent event) {
        application.VisualizarRendimento(application.getUsuarioLogado(), true);
    }

    @FXML
    private void botaoCadastrarCliente(ActionEvent event) {
        application.CadastroCliente(application.getUsuarioLogado(), true);
    }

    @FXML
    private void botaoCadastrarConsEm(ActionEvent event) {
        application.CadastrarConsultaEm(application.getUsuarioLogado(), true);
    }
    
    @FXML
    public void Sair(ActionEvent event) {
        if (application == null){
            return;
        }
        
        application.Deslogar();
    }
}