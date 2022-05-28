package hospital.Controles;

import hospital.Controles.AcessoHospital;
import hospital.Menu;
import hospital.Modelos.AuxiliarEnfermagem;
import hospital.Modelos.Data;
import hospital.Modelos.Listas.Hospital;
import java.io.FileNotFoundException;
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

public class CadastroAuxEnfController implements Initializable {

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
    private ComboBox<String> periodo;
    
    List<String> listaPeriodo = new ArrayList<>();
    
    private Menu application;
    
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

        for (int i = 0; i < Hospital.listaAuxEnf.size(); i++) {
            if (login.getText().equals(Hospital.listaAuxEnf.get(i).login)) {
                condCadastro = false;
            }
        }

        if (condCadastro) {

            int diaint = Integer.parseInt(dia.getText());
            int mesint = Integer.parseInt(mes.getText());
            int anoint = Integer.parseInt(ano.getText());
            
            AuxiliarEnfermagem auxenf = new AuxiliarEnfermagem(nome.getText(), endereco.getText(), cpf.getText(), new Data(diaint, mesint, anoint), telefone.getText(), email.getText(), login.getText(), senha.getText(), perio);
            
            AcessoHospital.cadastrarFuncionario(auxenf, null, null, auxenf);
            

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("");
            dialogoInfo.setHeaderText("O auxiliar de enfermagem " + nome.getText() + " foi cadastrado com sucesso!");
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
    
}
