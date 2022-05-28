package hospital.Controles;

import hospital.Controles.Cliente;
import hospital.Menu;
import hospital.Modelos.AuxiliarEnfermagem;
import hospital.Modelos.Enfermeiro;
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
import javafx.scene.control.Label;

public class CadastrarCirurgiaInternacaoController implements Initializable {

    @FXML
    private Label cadastrado;
    @FXML
    private ComboBox<EnumTipoAtendimento> tipoAtendimento;
    @FXML
    private ComboBox<String> enfermeiro;
    @FXML
    private ComboBox<String> auxEnf;
    @FXML
    private ComboBox<String> dia;
    @FXML
    private ComboBox<String> paciente;

    List<EnumTipoAtendimento> listaTpAtend = new ArrayList<>();
    List<String> listaEnf = new ArrayList<>();
    List<String> listaCli = new ArrayList<>();
    List<String> listaCli2 = new ArrayList<>();
    List<String> listaAuxEnf = new ArrayList<>();
    List<String> listaEnf2 = new ArrayList<>();
    List<String> listaAuxEnf2 = new ArrayList<>();
    List<String> listaDia = new ArrayList<>();

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listaTpAtend.add(EnumTipoAtendimento.Internacao);
        listaTpAtend.add(EnumTipoAtendimento.Cirurgia);

        listaDia.add("Dia 1");
        listaDia.add("Dia 2");

        for (int i = 0; i < Hospital.listaEnfermeiro.size(); i++) {
            listaEnf.add(Hospital.listaEnfermeiro.get(i).nome);
            listaEnf2.add(Hospital.listaEnfermeiro.get(i).login);
        }

        for (int i = 0; i < Hospital.listaCliente.size(); i++) {
            listaCli.add(Hospital.listaCliente.get(i).nome);
            listaCli2.add(Hospital.listaCliente.get(i).login);
        }

        for (int i = 0; i < Hospital.listaAuxEnf.size(); i++) {
            listaAuxEnf.add(Hospital.listaAuxEnf.get(i).nome);
            listaAuxEnf2.add(Hospital.listaAuxEnf.get(i).login);
        }

        ObservableList<String> enf = FXCollections.observableArrayList(listaEnf);
        enfermeiro.setItems(enf);

        ObservableList<String> auxenf = FXCollections.observableArrayList(listaAuxEnf);
        auxEnf.setItems(auxenf);

        ObservableList<String> cli = FXCollections.observableArrayList(listaCli);
        paciente.setItems(cli);

        ObservableList<EnumTipoAtendimento> at = FXCollections.observableArrayList(listaTpAtend);
        tipoAtendimento.setItems(at);

        ObservableList<String> dia2 = FXCollections.observableArrayList(listaDia);
        dia.setItems(dia2);
    }

    @FXML
    private void botaoCadastrar(ActionEvent event) throws FileNotFoundException {

        EnumTipoAtendimento tp = tipoAtendimento.getSelectionModel().getSelectedItem();

        String enf = enfermeiro.getSelectionModel().getSelectedItem();
        String loginEnf;
        Enfermeiro enfer = null;

        for (int i = 0; i < Hospital.listaEnfermeiro.size(); i++) {
            if (Hospital.listaEnfermeiro.get(i).nome.equals(enf) && Hospital.listaEnfermeiro.get(i).login.equals(listaEnf2.get(i))) {
                loginEnf = Hospital.listaEnfermeiro.get(i).login;
                enfer = Hospital.listaEnfermeiro.get(i);
            }
        }

        String auxenf = auxEnf.getSelectionModel().getSelectedItem();
        String loginAuxEnf;
        AuxiliarEnfermagem auxEnfer = null;

        for (int i = 0; i < Hospital.listaAuxEnf.size(); i++) {
            if (Hospital.listaAuxEnf.get(i).nome.equals(auxenf) && Hospital.listaAuxEnf.get(i).login.equals(listaAuxEnf2.get(i))) {
                loginAuxEnf = Hospital.listaAuxEnf.get(i).login;
                auxEnfer = Hospital.listaAuxEnf.get(i);
            }
        }

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

        String d = dia.getSelectionModel().getSelectedItem();

        boolean cond = true;

        for (int i = 0, cont = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico != null) {
                if (Hospital.listaAtendimento.get(i).medico.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).medico.login.equals(Menu.ul)) {
                    
                    if ("Dia 1".equals(d)) {
                        cont++;
                        Hospital.listaAtendimento.get(i).tipoAtendimento = tp;
                        Hospital.listaAtendimento.get(i).enfermeiro = enfer;
                        Hospital.listaAtendimento.get(i).auxiliarEnfermagem = auxEnfer;
                        Hospital.listaAtendimento.get(i).cliente = client;

                        if (tp == EnumTipoAtendimento.Cirurgia) {
                            if (plano != null) {
                                Hospital.listaAtendimento.get(i).valor = 230.0;
                            } else {
                                Hospital.listaAtendimento.get(i).valor = 280.0;
                            }
                        } else {
                            if (plano != null) {
                                Hospital.listaAtendimento.get(i).valor = 100.0;
                            } else {
                                Hospital.listaAtendimento.get(i).valor = 130.0;
                            }
                        }

                        if (cont >= 16) {
                            break;
                        }

                    } else if ("Dia 2".equals(d)) {
                        if (cond == true) {
                            i += 48;
                            cond = false;
                        }

                        Hospital.listaAtendimento.get(i).tipoAtendimento = tp;
                        Hospital.listaAtendimento.get(i).enfermeiro = enfer;
                        Hospital.listaAtendimento.get(i).auxiliarEnfermagem = auxEnfer;
                        Hospital.listaAtendimento.get(i).cliente = client;
                        
                        if (tp == EnumTipoAtendimento.Cirurgia) {
                            if (plano != null) {
                                Hospital.listaAtendimento.get(i).valor = 230.0;
                            } else {
                                Hospital.listaAtendimento.get(i).valor = 280.0;
                            }
                        } else {
                            if (plano != null) {
                                Hospital.listaAtendimento.get(i).valor = 100.0;
                            } else {
                                Hospital.listaAtendimento.get(i).valor = 130.0;
                            }
                        }
                    }
                }
            }
        }

        Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("");
        if (tp == EnumTipoAtendimento.Cirurgia) {
            dialogoInfo.setHeaderText("Cirurgia cadastrada com sucesso!");
        } else {
            dialogoInfo.setHeaderText("Internação cadastrada com sucesso!");
        }
        dialogoInfo.setContentText("Clique em OK para voltar ao menu!");
        dialogoInfo.showAndWait();

        application.MedicoLogado(application.getUsuarioLogado(), true);
    }

    @FXML
    private void botaoVoltar(ActionEvent event) {
        application.MedicoLogado(application.getUsuarioLogado(), true);
    }

}
