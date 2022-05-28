package hospital.Controles;

import hospital.Menu;
import hospital.Modelos.EnumTipoAtendimento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import hospital.Modelos.Listas.Hospital;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class MarcarConsultaController implements Initializable {

    @FXML
    private ListView<String> lista;

    List<String> listacon = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).horarioDisponivel == false && Hospital.listaAtendimento.get(i).medico != null && Hospital.listaAtendimento.get(i).tipoAtendimento == null) {

                if (Hospital.listaAtendimento.get(i).minuto == 0) {

                    if (Hospital.listaAtendimento.get(i).hora < 10) {
                        listacon.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                    } else {
                        listacon.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                    }
                } else {
                    if (Hospital.listaAtendimento.get(i).hora < 10) {
                        listacon.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                    } else {
                        listacon.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                    }
                }
            }
        }

        ObservableList<String> items = FXCollections.observableArrayList(listacon);
        lista.setItems(items);

        //lista.getItems().add(lista.getItems().size(), "12345");
    }

    @FXML
    private void marcarConsulta(ActionEvent event) throws FileNotFoundException {

        String selecionado = lista.getSelectionModel().getSelectedItem();

        String comparar;
        boolean alertplano = false;
        String nomeplano = "";

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).horarioDisponivel == false && Hospital.listaAtendimento.get(i).medico != null && Hospital.listaAtendimento.get(i).tipoAtendimento == null) {

                if (Hospital.listaAtendimento.get(i).minuto == 0) {

                    if (Hospital.listaAtendimento.get(i).hora < 10) {
                        comparar = ("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        if (comparar.equals(selecionado)) {
                            Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                            Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaMedica;
                            Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                            
                            for(int j = 0; j < Hospital.listaCliente.size(); j++) {
                                if(application.getUsuarioLogado().equals(Hospital.listaCliente.get(j).login)) {
                                    Hospital.listaAtendimento.get(i).cliente = Hospital.listaCliente.get(j);
                                    Hospital.listaAtendimento.get(i).planoSaude = Hospital.listaCliente.get(j).planoSaude;
                                    
                                    if(Hospital.listaCliente.get(j).planoSaude != null) {
                                        nomeplano = Hospital.listaCliente.get(j).planoSaude.nome;
                                        Hospital.listaAtendimento.get(i).valor = 80.0;
                                        alertplano = true;
                                    } else {
                                        Hospital.listaAtendimento.get(i).valor = 120.0;
                                    }
                                }
                            }

                            Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            break;
                        }
                    } else {
                        comparar = (Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        if (comparar.equals(selecionado)) {
                            Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                            Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaMedica;
                            Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                            
                            for(int j = 0; j < Hospital.listaCliente.size(); j++) {
                                if(application.getUsuarioLogado().equals(Hospital.listaCliente.get(j).login)) {
                                    Hospital.listaAtendimento.get(i).cliente = Hospital.listaCliente.get(j);
                                    Hospital.listaAtendimento.get(i).planoSaude = Hospital.listaCliente.get(j).planoSaude;
                                    if(Hospital.listaCliente.get(j).planoSaude != null) {
                                        nomeplano = Hospital.listaCliente.get(j).planoSaude.nome;
                                        Hospital.listaAtendimento.get(i).valor = 80.0;
                                        alertplano = true;
                                    } else {
                                        Hospital.listaAtendimento.get(i).valor = 120.0;
                                    }
                                }
                            }

                            Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            break;
                        }
                    }
                } else {
                    if (Hospital.listaAtendimento.get(i).hora < 10) {
                        comparar = ("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        if (comparar.equals(selecionado)) {
                            Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                            Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaMedica;
                            Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                            
                            for(int j = 0; j < Hospital.listaCliente.size(); j++) {
                                if(application.getUsuarioLogado().equals(Hospital.listaCliente.get(j).login)) {
                                    Hospital.listaAtendimento.get(i).cliente = Hospital.listaCliente.get(j);
                                    Hospital.listaAtendimento.get(i).planoSaude = Hospital.listaCliente.get(j).planoSaude;
                                    if(Hospital.listaCliente.get(j).planoSaude != null) {
                                        nomeplano = Hospital.listaCliente.get(j).planoSaude.nome;
                                        Hospital.listaAtendimento.get(i).valor = 80.0;
                                        alertplano = true;
                                    } else {
                                        Hospital.listaAtendimento.get(i).valor = 120.0;
                                    }
                                }
                            }

                            Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            break;
                        }
                    } else {
                        comparar = (Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                        if (comparar.equals(selecionado)) {
                            
                            Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                            Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaMedica;
                            Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                            
                            for(int j = 0; j < Hospital.listaCliente.size(); j++) {
                                if(application.getUsuarioLogado().equals(Hospital.listaCliente.get(j).login)) {
                                    Hospital.listaAtendimento.get(i).cliente = Hospital.listaCliente.get(j);
                                    Hospital.listaAtendimento.get(i).planoSaude = Hospital.listaCliente.get(j).planoSaude;
                                    if(Hospital.listaCliente.get(j).planoSaude != null) {
                                        nomeplano = Hospital.listaCliente.get(j).planoSaude.nome;
                                        Hospital.listaAtendimento.get(i).valor = 80.0;
                                        alertplano = true;
                                    } else {
                                        Hospital.listaAtendimento.get(i).valor = 120.0;
                                    }
                                }
                            }

                            Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                            Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            break;
                        }
                    }
                }
            }
        }

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("");
        
        if(alertplano == true) {
            dialogoInfo.setHeaderText("Consulta Marcada! Seu plano " + nomeplano + " cobriu o valor da sua consulta!");
        } else {
            dialogoInfo.setHeaderText("Consulta Marcada! O valor da sua consulta é de R$ 120,00");
        }
        
        dialogoInfo.setContentText("Clique em OK para voltar ao menu!");
        dialogoInfo.showAndWait();

        application.ClienteLogado(application.getUsuarioLogado(), true);

    }

    @FXML
    private void Sair(ActionEvent event) {
        application.ClienteLogado(application.getUsuarioLogado(), true);
    }
}
