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

public class ListarConsultasController implements Initializable {

    @FXML
    private ListView<String> lista2;

    List<String> listavis = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        boolean consultas = false;

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).cliente != null) {
                if (Hospital.listaAtendimento.get(i).cliente.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).cliente.login.equals(Menu.ul)) {

                    consultas = true;

                    if (Hospital.listaAtendimento.get(i).minuto == 0) {

                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        } else {
                            listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        }
                    } else {
                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        } else {
                            listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        }
                    }
                }
            }
        }
        
        ObservableList<String> items = FXCollections.observableArrayList(listavis);
        lista2.setItems(items);

        if (consultas == false) {
            
            lista2.getItems().add(lista2.getItems().size(), "Voce não possui nenhuma consulta marcada!");
            
        }
    }

    @FXML
    private void Sair(ActionEvent event) {
        application.ClienteLogado(application.getUsuarioLogado(), true);
    }

}
