package hospital.Controles;

import hospital.Controles.AcessoHospital;
import hospital.Menu;
import hospital.Modelos.Data;
import hospital.Modelos.Listas.Hospital;
import hospital.Modelos.Medico;
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
import javafx.scene.control.TextField;

public class CadastroMedicoController implements Initializable {
    
    public static int cont1 = 0, cont2 = 0, cont3 = 0;

    @FXML
    private TextField nome;
    @FXML
    private TextField endereco;
    @FXML
    private TextField cpf;
    @FXML
    private TextField telefone;
    @FXML
    private TextField email;
    @FXML
    private TextField login;
    @FXML
    private TextField senha;
    @FXML
    private TextField dia;
    @FXML
    private TextField mes;
    @FXML
    private TextField ano;
    @FXML
    private Label cadastrado;
    @FXML
    private TextField crm;
    
    private Menu application;
    @FXML
    private ComboBox<String> periodo;
    
    List<String> listaPeriodo = new ArrayList<>();
    
    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listaPeriodo.add("Primeiro período: de 00:00 as 07:30");
        listaPeriodo.add("Segundo período: de 08:00 as 15:30");
        listaPeriodo.add("Terceiro período: de 16:00 as 23:30");

        ObservableList<String> periodos = FXCollections.observableArrayList(listaPeriodo);
        periodo.setItems(periodos);
    }     

    @FXML
    private void botaoCadastrar(ActionEvent event) throws FileNotFoundException {
        
        boolean condCadastro = true;
        boolean condPeriodo = true;
        
        String per = periodo.getSelectionModel().getSelectedItem();
        int perio = 0;
        
        if("Primeiro período: de 00:00 as 07:30".equals(per)){
            perio = 1;
        } else if("Segundo período: de 08:00 as 15:30".equals(per)) {
            perio = 2;
        } else if("Terceiro período: de 16:00 as 23:30".equals(per)) {
            perio = 3;
        }
        
        if(perio == 0) {
            condPeriodo = false;
        }

        for (int i = 0; i < Hospital.listaMedico.size(); i++) {
            if (login.getText().equals(Hospital.listaMedico.get(i).login)) {
                condCadastro = false;
            }
        }

        if (condCadastro) {

            int diaint = Integer.parseInt(dia.getText());
            int mesint = Integer.parseInt(mes.getText());
            int anoint = Integer.parseInt(ano.getText());
            
            Medico medico = new Medico(crm.getText(), nome.getText(), endereco.getText(), cpf.getText(), new Data(diaint, mesint, anoint), telefone.getText(), email.getText(), login.getText(), senha.getText(), perio);
            
            AcessoHospital.cadastrarFuncionario(medico, medico, null, null);
            registrarPeriodo(medico, perio);
            

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("");
            dialogoInfo.setHeaderText("O médico " + nome.getText() + " foi cadastrado com sucesso!");
            dialogoInfo.setContentText("Clique em OK para voltar ao menu!");
            dialogoInfo.showAndWait();

            application.CadastrarFuncionario(application.getUsuarioLogado(), true);

        } else if(!condPeriodo) {
            cadastrado.setText("Escolha um período!");
        } else {
            cadastrado.setText("Usúario " + login.getText() + " já existe, escolha outro!");
        }      
    }

    @FXML
    private void botaoVoltar(ActionEvent event) {
        application.CadastrarFuncionario(application.getUsuarioLogado(), true);
    }
    
    //Padrão: cada período tem 8 horas ou seja, 1 dia tem 3 períodos
    public static void registrarPeriodo(Medico medico, int periodo) throws FileNotFoundException {
        boolean cond = true;
        int reg = 0;

        OUTER:
        for (int i = 0, aux1 = 0, aux2 = 16, aux3 = 32; i < Hospital.listaAtendimento.size(); i++) {
            switch (periodo) {
                case 1:
                    if (Hospital.listaAtendimento.get(aux1).medico == null) {

                        //Registra o médico na sala 1,2,3 no periodo 1
                        cont1++;
                        for (int j = 0; j <= 15; j++) {
                            reg = aux1 + j;

                            Hospital.listaAtendimento.get(reg).medico = medico;
                        }

                        if (cond == true) {
                            aux1 += 48;
                            cond = false;
                        } else {
                            break OUTER;
                        }
                    } else {
                        aux1 += 96;

                    }
                    break;
                case 2:
                    if (Hospital.listaAtendimento.get(aux2).medico == null) {

                        //Registra o médico na sala 1,2,3 no periodo 2
                        cont2++;
                        for (int j = 0; j <= 15; j++) {
                            reg = aux2 + j;

                            Hospital.listaAtendimento.get(reg).medico = medico;
                        }
                        if (cond == true) {
                            aux2 += 48;
                            cond = false;
                        } else {
                            break OUTER;
                        }
                    } else {
                        aux2 += 96;

                    }
                    break;
                case 3:
                    if (Hospital.listaAtendimento.get(aux3).medico == null) {

                        //Registra o médico na sala 1,2,3 no periodo 3
                        cont3++;
                        for (int j = 0; j <= 15; j++) {
                            reg = aux3 + j;

                            Hospital.listaAtendimento.get(reg).medico = medico;
                        }
                        if (cond == true) {
                            aux3 += 48;
                            cond = false;
                        } else {
                            break OUTER;
                        }
                    } else {
                        aux3 += 96;

                    }
                    break;
                default:
                    break;
            }
        }
        
        Menu.stream.toXML(cont1, new FileOutputStream("xml/cont1.xml"));
        Menu.stream.toXML(cont2, new FileOutputStream("xml/cont2.xml"));
        Menu.stream.toXML(cont3, new FileOutputStream("xml/cont3.xml"));
        Menu.stream.toXML(Hospital.listaAtendimento, new FileOutputStream("xml/atendimento.xml"));
        
    }
}