package hospital.Controles;

import hospital.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MenuDonoController implements Initializable {

    @FXML
    private Button cadastrarFuncionario;
    
    private Menu application;
    @FXML
    private Button gerarAtendimentos;
    @FXML
    private Button totalRendimentos;
    @FXML
    private Button listarMedicos;
    @FXML
    private Button listarConsultasDoDia;
    @FXML
    private Button Sair;
    @FXML
    private Label logado;
    
    public void setApp(Menu application) {
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  

    @FXML
    private void cadastrarFuncionario(ActionEvent event) {
        application.CadastrarFuncionario(application.getUsuarioLogado(), true);
    }

    @FXML
    private void gerarAtendimentos(ActionEvent event) {
        application.CriarRotinaTeste(application.getUsuarioLogado(), true);
    }

    @FXML
    private void totalRendimentos(ActionEvent event) {
        application.VisualizarRendimentoTotal(application.getUsuarioLogado(), true);
    }

    @FXML
    private void listarMedicos(ActionEvent event) {
        application.ListarMedicos(application.getUsuarioLogado(), true);
    }

    @FXML
    private void listarConsultasDoDia(ActionEvent event) {
        application.ListarTodasConsultas(application.getUsuarioLogado(), true);
    }
    
    @FXML
    public void Sair(ActionEvent event) {
        if (application == null){
            return;
        }
        
        application.Deslogar();
    }
}