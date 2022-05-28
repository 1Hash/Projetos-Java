package hospital.Controles;

import hospital.Controles.CadastroMedicoController;
import hospital.Controles.AcessoHospital;
import hospital.Menu;
import hospital.Modelos.Listas.Hospital;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class CriarRotinaTesteController implements Initializable {

    private Menu application;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void criarRotina(ActionEvent event) throws FileNotFoundException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Atenção!!!");
        alert.setContentText("Tem certeza que deseja criar uma nova rotina de teste?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            
            Hospital.listaCliente.clear();
            Hospital.listaAtendimento.clear();
            Hospital.listaFuncionario.clear();
            Hospital.listaPlanoSaude.clear();
            Hospital.listaMedico.clear();
            Hospital.listaEnfermeiro.clear();
            Hospital.listaAuxEnf.clear();
            Hospital.listaConsulta.clear();
            Hospital.listaAtendimentosValidados.clear();

            CadastroMedicoController.cont1 = 0;
            CadastroMedicoController.cont2 = 0;
            CadastroMedicoController.cont3 = 0;
            
            AcessoHospital.criarRotinaDeTeste();
            
            AcessoHospital.criarArquivoXML();
            
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("");
            dialogoInfo.setHeaderText("Nova rotina de teste criada com sucesso!");
            dialogoInfo.setContentText("Clique em OK para voltar ao menu!");
            dialogoInfo.showAndWait();
            
            application.DonoLogado(application.getUsuarioLogado(), true); 

        } else {

        }
    }

    @FXML
    private void Sair(ActionEvent event) {
        application.DonoLogado(application.getUsuarioLogado(), true);
    }

}
