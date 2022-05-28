package hospital.Controles;

import hospital.Menu;
import hospital.Modelos.Listas.Hospital;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ListarMedicosController implements Initializable {

    @FXML
    private ListView<String> lista;
    
    List<String> listamed = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        boolean medicos = false;
        
        for(int i = 0; i < Hospital.listaMedico.size(); i++) {
            listamed.add(Hospital.listaMedico.get(i).nome);
            if(Hospital.listaMedico.get(i) != null) {
                medicos = true;
            }
        }
        
        ObservableList<String> items = FXCollections.observableArrayList(listamed);
        lista.setItems(items);
        
        if(!medicos) {
            lista.getItems().add(lista.getItems().size(), "O Hospital não possuí nenhum médico!");
        }       
    }    

    @FXML
    private void Sair(ActionEvent event) {
        application.DonoLogado(application.getUsuarioLogado(), true);
    }
    
}
