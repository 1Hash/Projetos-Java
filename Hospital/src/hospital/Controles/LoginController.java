package hospital.Controles;

import hospital.Modelos.Listas.Hospital;
import hospital.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController extends AnchorPane implements Initializable {

    @FXML
    TextField login;
    @FXML
    PasswordField senha;
    @FXML
    Button entrar;
    @FXML
    Label erro;

    public Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        erro.setText("");
    }

    @FXML
    public void processEntrar(ActionEvent event) throws Exception {

        if (application != null) {

            boolean loginCli = false;
            boolean loginDono = false;
            boolean loginMed = false;
            boolean loginAuxEnf = false;
            boolean loginEnf = false;

            for (int i = 0; i < Hospital.listaCliente.size(); i++) {
                if (login.getText().equals(Hospital.listaCliente.get(i).login) && (senha.getText().equals(Hospital.listaCliente.get(i).senha))) {
                    loginCli = true;
                }
            }

            for (int i = 0; i < Hospital.listaMedico.size(); i++) {
                if (login.getText().equals(Hospital.listaMedico.get(i).login) && (senha.getText().equals(Hospital.listaMedico.get(i).senha))) {
                    loginMed = true;
                }
            }

            for (int i = 0; i < Hospital.listaAuxEnf.size(); i++) {
                if (login.getText().equals(Hospital.listaAuxEnf.get(i).login) && (senha.getText().equals(Hospital.listaAuxEnf.get(i).senha))) {
                    loginAuxEnf = true;
                }
            }

            for (int i = 0; i < Hospital.listaEnfermeiro.size(); i++) {
                if (login.getText().equals(Hospital.listaEnfermeiro.get(i).login) && (senha.getText().equals(Hospital.listaEnfermeiro.get(i).senha))) {
                    loginEnf = true;
                }
            }

            if ("admin".equals(login.getText()) && "321".equals(senha.getText())) {
                loginDono = true;
            }

            if (loginCli == true) {
                application.ClienteLogado(login.getText(), loginCli);

            } else if (loginMed == true) {
                application.MedicoLogado(login.getText(), loginMed);

            } else if (loginEnf == true) {
                application.EnfLogado(login.getText(), loginEnf);

            } else if (loginAuxEnf == true) {
                application.AuxEnfLogado(login.getText(), loginAuxEnf);

            } else if (loginDono == true) {
                application.DonoLogado(login.getText(), loginDono);
                
            } else {
                erro.setText("Usuario ou senha incorreto!");
            }
        }
    }
}
