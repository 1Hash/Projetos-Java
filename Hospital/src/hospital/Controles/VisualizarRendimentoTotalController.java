package hospital.Controles;

import hospital.Menu;
import hospital.Modelos.Listas.Hospital;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class VisualizarRendimentoTotalController implements Initializable {

    @FXML
    private Label totalHospital;
    
    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        double totalH = 0.0;
        
        for(int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if(Hospital.listaAtendimento.get(i).valor != 0.0) {
                totalH = totalH + Hospital.listaAtendimento.get(i).valor;
            }
        }
        
        totalHospital.setText("O rendimento total do hospital é de: " + totalH);
        
    }    

    @FXML
    private void Sair(ActionEvent event) {
        application.DonoLogado(application.getUsuarioLogado(), true);
    }
    
}
