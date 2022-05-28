package hospital.Controles;

import hospital.Menu;
import hospital.Modelos.Listas.Hospital;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.ListView;

public class ValidarAtendimentosController implements Initializable {

    @FXML
    private ListView<String> lista;

    List<String> listacon = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        boolean verifica = false;

        for (int i = 0; i < Hospital.listaConsulta.size(); i++) {
            if (Hospital.listaConsulta.get(i).medico != null) {
                if (Hospital.listaConsulta.get(i).medico.login == null ? Menu.ul == null : Hospital.listaConsulta.get(i).medico.login.equals(Menu.ul)) {

                    verifica = true;

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
        }

        ObservableList<String> items = FXCollections.observableArrayList(listacon);
        lista.setItems(items);

        if (verifica == false) {
            lista.getItems().add(lista.getItems().size(), "Voce não possui nenhum atendimento para validar!");
        }
    }

    @FXML
    private void validarAtendimentos(ActionEvent event) throws FileNotFoundException {

        String selecionado = lista.getSelectionModel().getSelectedItem();

        String comparar;

        for (int i = 0; i < Hospital.listaConsulta.size(); i++) {
            if (Hospital.listaConsulta.get(i).medico != null) {
                if (Hospital.listaConsulta.get(i).medico.login == null ? Menu.ul == null : Hospital.listaConsulta.get(i).medico.login.equals(Menu.ul)) {

                    if (Hospital.listaConsulta.get(i).minuto == 0) {

                        if (Hospital.listaConsulta.get(i).hora < 10) {
                            comparar = ("0" + Integer.toString(Hospital.listaConsulta.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                Hospital.listaAtendimentosValidados.add(Hospital.listaConsulta.get(i));
                                Hospital.listaConsulta.remove(i);

                                Menu.stream.toXML(Hospital.listaAtendimentosValidados, new FileOutputStream("xml/atendimentosValidados.xml"));
                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            }
                        } else {
                            comparar = (Integer.toString(Hospital.listaConsulta.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                Hospital.listaAtendimentosValidados.add(Hospital.listaConsulta.get(i));
                                Hospital.listaConsulta.remove(i);

                                Menu.stream.toXML(Hospital.listaAtendimentosValidados, new FileOutputStream("xml/atendimentosValidados.xml"));
                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            }
                        }
                    } else {
                        if (Hospital.listaConsulta.get(i).hora < 10) {
                            comparar = ("0" + Integer.toString(Hospital.listaConsulta.get(i).hora) + ":" + Integer.toString(Hospital.listaConsulta.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                Hospital.listaAtendimentosValidados.add(Hospital.listaConsulta.get(i));
                                Hospital.listaConsulta.remove(i);

                                Menu.stream.toXML(Hospital.listaAtendimentosValidados, new FileOutputStream("xml/atendimentosValidados.xml"));
                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            }
                        } else {
                            comparar = (Integer.toString(Hospital.listaConsulta.get(i).hora) + ":" + Integer.toString(Hospital.listaConsulta.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaConsulta.get(i).sala) + " - " + Hospital.listaConsulta.get(i).medico.nome + " - " + Hospital.listaConsulta.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                Hospital.listaAtendimentosValidados.add(Hospital.listaConsulta.get(i));
                                Hospital.listaConsulta.remove(i);

                                Menu.stream.toXML(Hospital.listaAtendimentosValidados, new FileOutputStream("xml/atendimentosValidados.xml"));
                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            }
                        }
                    }
                }
            }
        }

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("");
        dialogoInfo.setHeaderText("Atendimento validado!");
        dialogoInfo.setContentText("Clique em OK para voltar ao menu!");
        dialogoInfo.showAndWait();
        
        application.MedicoLogado(application.getUsuarioLogado(), true);

    }

    @FXML
    private void Sair(ActionEvent event) {
        application.MedicoLogado(application.getUsuarioLogado(), true);
    }
}
