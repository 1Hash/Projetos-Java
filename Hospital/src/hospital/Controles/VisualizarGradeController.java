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

public class VisualizarGradeController implements Initializable {

    @FXML
    private ListView<String> lista;

    List<String> listavis = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        boolean mensagem = false;

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico != null) {
                if (Hospital.listaAtendimento.get(i).medico.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).medico.login.equals(Menu.ul)) {

                    mensagem = true;

                    if (Hospital.listaAtendimento.get(i).cliente != null) {

                        if (Hospital.listaAtendimento.get(i).minuto == 0) {
                            if (Hospital.listaAtendimento.get(i).hora < 10) {
                                listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            } else {
                                listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            }
                        } else {
                            if (Hospital.listaAtendimento.get(i).hora < 10) {
                                listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            } else {
                                listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            }
                        }
                    } else {
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
            } else if (Hospital.listaAtendimento.get(i).auxiliarEnfermagem != null && Hospital.listaAtendimento.get(i).cliente != null) {
                if (Hospital.listaAtendimento.get(i).auxiliarEnfermagem.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).auxiliarEnfermagem.login.equals(Menu.ul)) {
                    
                    mensagem = true;
                    
                    if (Hospital.listaAtendimento.get(i).cliente != null) {
                        
                        if (Hospital.listaAtendimento.get(i).minuto == 0) {
                            if (Hospital.listaAtendimento.get(i).hora < 10) {
                                listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            } else {
                                listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            }
                        } else {
                            if (Hospital.listaAtendimento.get(i).hora < 10) {
                                listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            } else {
                                listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            }
                        }
                    } else {
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
            } else if (Hospital.listaAtendimento.get(i).enfermeiro != null && Hospital.listaAtendimento.get(i).cliente != null) {
                if (Hospital.listaAtendimento.get(i).enfermeiro.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).enfermeiro.login.equals(Menu.ul)) {
                    
                    mensagem = true;
                    
                    if (Hospital.listaAtendimento.get(i).cliente != null) {
                        
                        if (Hospital.listaAtendimento.get(i).minuto == 0) {
                            if (Hospital.listaAtendimento.get(i).hora < 10) {
                                listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            } else {
                                listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            }
                        } else {
                            if (Hospital.listaAtendimento.get(i).hora < 10) {
                                listavis.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            } else {
                                listavis.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            }
                        }
                    } else {
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
        }

        ObservableList<String> items = FXCollections.observableArrayList(listavis);
        lista.setItems(items);
        
        if (mensagem == false) {

            lista.getItems().add(lista.getItems().size(), "Voce não possui uma grade de trabalho!");
        } 

    }

    @FXML
    private void Sair(ActionEvent event) {

        boolean loginMed = false;
        boolean loginAuxEnf = false;
        boolean loginEnf = false;

        for (int i = 0; i < Hospital.listaMedico.size(); i++) {
            if (application.getUsuarioLogado().equals(Hospital.listaMedico.get(i).login)) {
                loginMed = true;
            }
        }

        for (int i = 0; i < Hospital.listaAuxEnf.size(); i++) {
            if (application.getUsuarioLogado().equals(Hospital.listaAuxEnf.get(i).login)) {
                loginAuxEnf = true;
            }
        }

        for (int i = 0; i < Hospital.listaEnfermeiro.size(); i++) {
            if (application.getUsuarioLogado().equals(Hospital.listaEnfermeiro.get(i).login)) {
                loginEnf = true;
            }
        }

        if (loginMed == true) {
            application.MedicoLogado(application.getUsuarioLogado(), loginMed);

        } else if (loginEnf == true) {
            application.EnfLogado(application.getUsuarioLogado(), loginEnf);

        } else if (loginAuxEnf == true) {
            application.AuxEnfLogado(application.getUsuarioLogado(), loginAuxEnf);
        }
    }
}
