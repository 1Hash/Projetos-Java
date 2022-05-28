package hospital.Controles;

import hospital.Menu;
import hospital.Modelos.EnumTipoAtendimento;
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

public class ListarTodasConsultasController implements Initializable {

    @FXML
    private ListView<String> lista;

    List<String> listacon = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        boolean consultas = false;

        for (int i = 0; i < Hospital.listaConsulta.size(); i++) {
            if (Hospital.listaConsulta.get(i).tipoAtendimento == EnumTipoAtendimento.ConsultaMedica || Hospital.listaConsulta.get(i).tipoAtendimento == EnumTipoAtendimento.ConsultaEmergencia) {
                
                consultas = true;
                
                if (Hospital.listaConsulta.get(i).minuto == 0) {

                    if (Hospital.listaConsulta.get(i).hora < 10) {
                        listacon.add("0" + Integer.toString(Hospital.listaConsulta.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                    } else {
                        listacon.add(Integer.toString(Hospital.listaConsulta.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                    }
                } else {
                    if (Hospital.listaConsulta.get(i).hora < 10) {
                        listacon.add("0" + Integer.toString(Hospital.listaConsulta.get(i).hora) + ":" + Integer.toString(Hospital.listaConsulta.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                    } else {
                        listacon.add(Integer.toString(Hospital.listaConsulta.get(i).hora) + ":" + Integer.toString(Hospital.listaConsulta.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                    }
                }
            }
        }

        ObservableList<String> items = FXCollections.observableArrayList(listacon);
        lista.setItems(items);

        if (!consultas) {
            lista.getItems().add(lista.getItems().size(), "O Hospital não possuí nenhuma consulta!");
        }

    }

    @FXML
    private void Sair(ActionEvent event) {
        application.DonoLogado(application.getUsuarioLogado(), true);
    }

}
