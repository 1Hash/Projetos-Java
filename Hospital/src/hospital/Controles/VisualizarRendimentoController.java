package hospital.Controles;

import hospital.*;
import hospital.Menu;
import hospital.Menu;
import hospital.Menu;
import hospital.Modelos.Listas.Hospital;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class VisualizarRendimentoController implements Initializable {

    private Menu application;
    private Label consultasAtendidas;
    private Label valorConsultas;
    @FXML
    private Label totalConsultas2;
    @FXML
    private Label valorConsultasParticular;
    @FXML
    private Label valorConsultaPlano;
    @FXML
    private Label valorInternacao;
    @FXML
    private Label valorCirurgia;
    @FXML
    private Label valorTotal;
    @FXML
    private Label valorTotalConsultas;

    public void setApp(Menu application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        double valorRendimentoParticular = 0.0;
        double valorRendimentoPlano = 0.0;
        double totalInternacao = 0.0;
        double totalCirurgia = 0.0;
        int totalConsultas = 0;

        for (int i = 0; i < Hospital.listaAtendimento.size(); i++) {
            if (Hospital.listaAtendimento.get(i).medico != null && Hospital.listaAtendimento.get(i).cliente != null) {
                if (Hospital.listaAtendimento.get(i).medico.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).medico.login.equals(Menu.ul)) {
                  
                    //Plano de Saúde
                    if(Hospital.listaAtendimento.get(i).valor == 80.0) { //Consulta
                        
                        valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalConsultas++;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 100.0){ //Internacao
                        
                        //valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalInternacao = totalInternacao + Hospital.listaAtendimento.get(i).valor;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 230.0) { //Cirurgia
                        
                        //valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalCirurgia = totalCirurgia + Hospital.listaAtendimento.get(i).valor;
                        
                    }
                    
                    //Particular
                    if(Hospital.listaAtendimento.get(i).valor == 120.0) { //Consulta
                        
                        valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalConsultas++;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 130.0){ //Internacao
                        
                        //valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalInternacao = totalInternacao + Hospital.listaAtendimento.get(i).valor;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 280.0) { //Cirurgia
                        
                        //valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalCirurgia = totalCirurgia + Hospital.listaAtendimento.get(i).valor;
                        
                    }
                    
                }
            } else if (Hospital.listaAtendimento.get(i).auxiliarEnfermagem != null && Hospital.listaAtendimento.get(i).cliente != null) {
                if (Hospital.listaAtendimento.get(i).auxiliarEnfermagem.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).auxiliarEnfermagem.login.equals(Menu.ul)) {
                    
                    //Plano de Saúde
                    if(Hospital.listaAtendimento.get(i).valor == 80.0) { //Consulta
                        
                        valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalConsultas++;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 100.0){ //Internacao
                        
                        //valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalInternacao = totalInternacao + Hospital.listaAtendimento.get(i).valor;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 230.0) { //Cirurgia
                        
                        //valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalCirurgia = totalCirurgia + Hospital.listaAtendimento.get(i).valor;
                        
                    }
                    
                    //Particular
                    if(Hospital.listaAtendimento.get(i).valor == 120.0) { //Consulta
                        
                        valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalConsultas++;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 130.0){ //Internacao
                        
                        //valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalInternacao = totalInternacao + Hospital.listaAtendimento.get(i).valor;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 280.0) { //Cirurgia
                        
                        //valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalCirurgia = totalCirurgia + Hospital.listaAtendimento.get(i).valor;
                        
                    }
                }
            } else if (Hospital.listaAtendimento.get(i).enfermeiro != null && Hospital.listaAtendimento.get(i).cliente != null) {
                if (Hospital.listaAtendimento.get(i).enfermeiro.login == null ? Menu.ul == null : Hospital.listaAtendimento.get(i).enfermeiro.login.equals(Menu.ul)) {
                    
                    //Plano de Saúde
                    if(Hospital.listaAtendimento.get(i).valor == 80.0) { //Consulta
                        
                        valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalConsultas++;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 100.0){ //Internacao
                        
                        //valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalInternacao = totalInternacao + Hospital.listaAtendimento.get(i).valor;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 230.0) { //Cirurgia
                        
                        //valorRendimentoPlano = valorRendimentoPlano + Hospital.listaAtendimento.get(i).valor;
                        totalCirurgia = totalCirurgia + Hospital.listaAtendimento.get(i).valor;
                        
                    }
                    
                    //Particular
                    if(Hospital.listaAtendimento.get(i).valor == 120.0) { //Consulta
                        
                        valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalConsultas++;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 130.0){ //Internacao
                        
                        //valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalInternacao = totalInternacao + Hospital.listaAtendimento.get(i).valor;
                        
                    } else if(Hospital.listaAtendimento.get(i).valor == 280.0) { //Cirurgia
                        
                        //valorRendimentoParticular = valorRendimentoParticular + Hospital.listaAtendimento.get(i).valor;
                        totalCirurgia = totalCirurgia + Hospital.listaAtendimento.get(i).valor;
                        
                    }
                }
            }
        }
        
        double valorTotalCons = (valorRendimentoParticular + valorRendimentoPlano);
        double total = (valorTotalCons + totalInternacao + totalCirurgia);

        totalConsultas2.setText("Total de consultas atendidas: " + totalConsultas);
        valorConsultasParticular.setText("Valor das consultas particulares atendidas: " + valorRendimentoParticular);
        valorConsultaPlano.setText("Valor das consultas com plano de saúde atendidas: " + valorRendimentoPlano);
        valorInternacao.setText("Valor das internações: " + totalInternacao);
        valorCirurgia.setText("Valor das cirurgias: " + totalCirurgia);
        valorTotalConsultas.setText("Valor total das consultas: " + valorTotalCons);
        valorTotal.setText("Valor total do seu rendimento: " + total);
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
