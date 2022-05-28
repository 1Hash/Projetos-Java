package hospital.Controles;

import hospital.Controles.Cliente;
import hospital.Menu;
import hospital.Modelos.EntidadePagadora;
import hospital.Modelos.EnumTipoAtendimento;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class CadastrarConsultaEmergenciaController implements Initializable {

    @FXML
    private ListView<String> lista;
    
    @FXML
    private ComboBox<String> paciente;
    
    List<String> listaCli = new ArrayList<>();
    List<String> listaCli2 = new ArrayList<>();

    List<String> listacon = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (int i = 0; i < Hospital.listaCliente.size(); i++) {
            listaCli.add(Hospital.listaCliente.get(i).nome);
            listaCli2.add(Hospital.listaCliente.get(i).login);
        }
        
        ObservableList<String> cli = FXCollections.observableArrayList(listaCli);
        paciente.setItems(cli);

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico != null && Hospital.listaAtendimento.get(i).tipoAtendimento != EnumTipoAtendimento.Cirurgia && Hospital.listaAtendimento.get(i).tipoAtendimento != EnumTipoAtendimento.Internacao && Hospital.listaAtendimento.get(i).tipoAtendimento != EnumTipoAtendimento.ConsultaEmergencia) {

                if (Hospital.listaAtendimento.get(i).cliente != null) {

                    if (Hospital.listaAtendimento.get(i).minuto == 0) {

                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            listacon.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                        } else {
                            listacon.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                        }
                    } else {
                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            listacon.add("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                        } else {
                            listacon.add(Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                        }
                    }
                } else {
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
        }

        ObservableList<String> items = FXCollections.observableArrayList(listacon);
        lista.setItems(items);
    }

    @FXML
    private void cadastrarConsulta(ActionEvent event) throws FileNotFoundException {
        
        String clie = paciente.getSelectionModel().getSelectedItem();
        String loginclie;
        Cliente client = null;
        EntidadePagadora plano = null;

        for (int i = 0; i < Hospital.listaCliente.size(); i++) {
            if (Hospital.listaCliente.get(i).nome.equals(clie) && Hospital.listaCliente.get(i).login.equals(listaCli2.get(i))) {
                loginclie = Hospital.listaCliente.get(i).login;
                client = Hospital.listaCliente.get(i);
                plano = Hospital.listaCliente.get(i).planoSaude;
            }
        }

        String selecionado = lista.getSelectionModel().getSelectedItem();

        String comparar;

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico != null && Hospital.listaAtendimento.get(i).tipoAtendimento != EnumTipoAtendimento.Cirurgia && Hospital.listaAtendimento.get(i).tipoAtendimento != EnumTipoAtendimento.Internacao && Hospital.listaAtendimento.get(i).tipoAtendimento != EnumTipoAtendimento.ConsultaEmergencia) {

                if (Hospital.listaAtendimento.get(i).cliente != null) {

                    if (Hospital.listaAtendimento.get(i).minuto == 0) {

                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            comparar = ("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        } else {
                            comparar = (Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        }
                    } else {
                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            comparar = ("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        } else {
                            comparar = (Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome + " - " + Hospital.listaAtendimento.get(i).cliente.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        }
                    }
                } else {
                    if (Hospital.listaAtendimento.get(i).minuto == 0) {

                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            comparar = ("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        } else {
                            comparar = (Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":00 - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        }
                    } else {
                        if (Hospital.listaAtendimento.get(i).hora < 10) {
                            comparar = ("0" + Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        } else {
                            comparar = (Integer.toString(Hospital.listaAtendimento.get(i).hora) + ":" + Integer.toString(Hospital.listaAtendimento.get(i).minuto) + " - Sala: " + Integer.toString(Hospital.listaAtendimento.get(i).sala) + " - " + Hospital.listaAtendimento.get(i).medico.nome);
                            if (comparar.equals(selecionado)) {
                                
                                Hospital.listaConsulta.add(Hospital.listaAtendimento.get(i));
                                Hospital.listaAtendimento.get(i).tipoAtendimento = EnumTipoAtendimento.ConsultaEmergencia;
                                Hospital.listaAtendimento.get(i).horarioDisponivel = true;
                                Hospital.listaAtendimento.get(i).cliente = client;
                                
                                if(plano != null) {
                                    Hospital.listaAtendimento.get(i).valor = 80.0;
                                } else {
                                    Hospital.listaAtendimento.get(i).valor = 120.0;
                                }

                                Menu.stream.toXML(Hospital.listaConsulta, new FileOutputStream("xml/consulta.xml"));
                                Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
                            }
                        }
                    }
                }
            }
        }

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("");
        dialogoInfo.setHeaderText("Consulta de emergencia marcada!");
        dialogoInfo.setContentText("Clique em OK para voltar ao menu!");
        dialogoInfo.showAndWait();

        boolean loginAuxEnf = false;
        boolean loginEnf = false;

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

        if (loginEnf == true) {
            application.EnfLogado(application.getUsuarioLogado(), loginEnf);

        } else if (loginAuxEnf == true) {
            application.AuxEnfLogado(application.getUsuarioLogado(), loginAuxEnf);
        }
    }

    @FXML
    private void Sair(ActionEvent event) {

        boolean loginAuxEnf = false;
        boolean loginEnf = false;

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

        if (loginEnf == true) {
            application.EnfLogado(application.getUsuarioLogado(), loginEnf);

        } else if (loginAuxEnf == true) {
            application.AuxEnfLogado(application.getUsuarioLogado(), loginAuxEnf);
        }
    }
}