package hospital.Controles;

import hospital.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuClienteController implements Initializable {

    @FXML
    private Button MarcarConsulta;
    @FXML
    private Button ConsultasMarcadas;
    @FXML
    private Button Sair;
    
    private Menu application;
    @FXML
    private Label logado;
    
    public void setApp(Menu application){
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       logado.setText("Logado como " + Menu.ul);
    }    

    @FXML
    private void botaoMarcarConsulta(ActionEvent event) {
        application.MarcarConsulta(application.getUsuarioLogado(), true);      
    }

    @FXML
    private void botaoConsultasMarcadas(ActionEvent event) {
        application.ListarConsultas(application.getUsuarioLogado(), true);
    }

    @FXML
    public void Sair(ActionEvent event) {
        if (application == null){
            return;
        }
        
        application.Deslogar();
    }  
}