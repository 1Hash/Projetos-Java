/*===================================================================
          Todos os direitos reservados a EL Productions
===================================================================*/

package hospital;


import hospital.Controles.VisualizarRendimentoController;
import hospital.Controles.VisualizarRendimentoTotalController;
import hospital.Controles.VisualizarGradeController;
import hospital.Controles.ValidarAtendimentosController;
import hospital.Controles.MarcarConsultaController;
import hospital.Controles.ListarTodasConsultasController;
import hospital.Controles.ListarMedicosController;
import hospital.Controles.ListarConsultasController;
import hospital.Controles.CriarRotinaTesteController;
import hospital.Controles.CadastroMedicoController;
import hospital.Controles.CadastroEnfermeiroController;
import hospital.Controles.CadastroAuxEnfController;
import hospital.Controles.CadastrarFuncionarioController;
import hospital.Controles.CadastrarConsultaEmergenciaController;
import hospital.Controles.CadastrarCirurgiaInternacaoController;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hospital.Controles.CadastroClienteController;
import hospital.Controles.MenuAuxEnfController;
import hospital.Controles.MenuClienteController;
import hospital.Controles.MenuDonoController;
import hospital.Controles.MenuEnfController;
import hospital.Controles.MenuMedicoController;
import hospital.Controles.AcessoHospital;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import hospital.Controles.LoginController;
import java.io.FileNotFoundException;

public class Menu extends Application {

    private Stage stage;
    private String usuarioLogado;
    public static String ul;
    public static XStream stream = new XStream(new DomDriver());

    public static void main(String[] args) throws FileNotFoundException {
        AcessoHospital.lerXML();
        Application.launch(Menu.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primeiroStage) {
        try {
            stage = primeiroStage;
            stage.setMinWidth(618.0);
            stage.setMinHeight(440.0);
            IrParaLogin();
            primeiroStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUsuarioLogado() {
        return usuarioLogado;
    }
        
    public boolean ClienteLogado(String usuario, boolean cond){
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaMenuCliente();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean MedicoLogado(String usuario, boolean cond){
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaMenuMedico();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean AuxEnfLogado(String usuario, boolean cond){
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaMenuAuxEnf();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean EnfLogado(String usuario, boolean cond){
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaMenuEnf();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean DonoLogado(String usuario, boolean cond){
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaMenuDono();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CadastroCliente(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCadastroCliente();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean MarcarConsulta(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaMarcarConsulta();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ListarConsultas(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaListarConsultas();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean VisualizarGrade(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaVisualizarGrade();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean VisualizarRendimento(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaVisualizarRendimento();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ValidarAtendimento(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaValidarAtendimento();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CadastrarConsultaEm(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCadastrarConsultaEm();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CadastrarCirurgiaInternacao(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCadastrarCirurgiaInternacao();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CadastrarFuncionario(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCadastrarFuncionario();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CadastrarMedico(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCadastrarMedico();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CadastrarEnfermeiro(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCadastrarEnfermeiro();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CadastrarAuxEnf(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCadastrarAuxEnf();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean VisualizarRendimentoTotal(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaVisualizarRendimentoTotal();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ListarMedicos(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaListarMedicos();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ListarTodasConsultas(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaListarTodasConsultas();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CriarRotinaTeste(String usuario, boolean cond) {
        if (cond) {
            usuarioLogado = usuario;
            ul = usuario;
            IrParaCriarRotinaTeste();
            return true;
        } else {
            return false;
        }
    }
    
    public void Deslogar(){
        usuarioLogado = null;
        ul = null;
        IrParaLogin();
    }
    
    private void IrParaMenuCliente() {
        try {
            MenuClienteController menucliente = (MenuClienteController) SubstituirConteudoCena("Visoes/MenuCliente.fxml");
            menucliente.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCriarRotinaTeste() {
        try {
            CriarRotinaTesteController criarrotina = (CriarRotinaTesteController) SubstituirConteudoCena("Visoes/CriarRotinaTeste.fxml");
            criarrotina.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaListarMedicos() {
        try {
            ListarMedicosController listarmedicos = (ListarMedicosController) SubstituirConteudoCena("Visoes/ListarMedicos.fxml");
            listarmedicos.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaListarTodasConsultas() {
        try {
            ListarTodasConsultasController listartodascons = (ListarTodasConsultasController) SubstituirConteudoCena("Visoes/ListarTodasConsultas.fxml");
            listartodascons.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaListarConsultas() {
        try {
            ListarConsultasController listarconsultas = (ListarConsultasController) SubstituirConteudoCena("Visoes/ListarConsultas.fxml");
            listarconsultas.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCadastrarCirurgiaInternacao() {
        try {
            CadastrarCirurgiaInternacaoController cadastrarci = (CadastrarCirurgiaInternacaoController) SubstituirConteudoCena("Visoes/CadastrarCirurgiaInternacao.fxml");
            cadastrarci.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCadastrarFuncionario() {
        try {
            CadastrarFuncionarioController cadastrarfun = (CadastrarFuncionarioController) SubstituirConteudoCena("Visoes/CadastrarFuncionario.fxml");
            cadastrarfun.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCadastrarMedico() {
        try {
            CadastroMedicoController cadastrarmed = (CadastroMedicoController) SubstituirConteudoCena("Visoes/CadastroMedico.fxml");
            cadastrarmed.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCadastrarEnfermeiro() {
        try {
            CadastroEnfermeiroController cadastrarenf = (CadastroEnfermeiroController) SubstituirConteudoCena("Visoes/CadastroEnfermeiro.fxml");
            cadastrarenf.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCadastrarAuxEnf() {
        try {
            CadastroAuxEnfController cadastrarauxenf = (CadastroAuxEnfController) SubstituirConteudoCena("Visoes/CadastroAuxEnf.fxml");
            cadastrarauxenf.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCadastrarConsultaEm() {
        try {
            CadastrarConsultaEmergenciaController cadastrarconsultasem = (CadastrarConsultaEmergenciaController) SubstituirConteudoCena("Visoes/CadastrarConsultaEmergencia.fxml");
            cadastrarconsultasem.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaValidarAtendimento() {
        try {
            ValidarAtendimentosController validaratendimentos = (ValidarAtendimentosController) SubstituirConteudoCena("Visoes/ValidarAtendimentos.fxml");
            validaratendimentos.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaVisualizarGrade() {
        try {
            VisualizarGradeController visualizargrade = (VisualizarGradeController) SubstituirConteudoCena("Visoes/VisualizarGrade.fxml");
            visualizargrade.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaVisualizarRendimento() {
        try {
            VisualizarRendimentoController visualizarrendimento = (VisualizarRendimentoController) SubstituirConteudoCena("Visoes/VisualizarRendimento.fxml");
            visualizarrendimento.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaVisualizarRendimentoTotal() {
        try {
            VisualizarRendimentoTotalController visualizarrendimentototal = (VisualizarRendimentoTotalController) SubstituirConteudoCena("Visoes/VisualizarRendimentoTotal.fxml");
            visualizarrendimentototal.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaMarcarConsulta() {
        try {
            MarcarConsultaController marcarconsulta = (MarcarConsultaController) SubstituirConteudoCena("Visoes/MarcarConsulta.fxml");
            marcarconsulta.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void IrParaCadastroCliente() {
        try {
            CadastroClienteController cadastrocliente = (CadastroClienteController) SubstituirConteudoCena("Visoes/CadastroCliente.fxml");
            cadastrocliente.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void IrParaMenuMedico() {
        try {
            MenuMedicoController menumedico = (MenuMedicoController) SubstituirConteudoCena("Visoes/MenuMedico.fxml");
            menumedico.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     private void IrParaMenuAuxEnf() {
        try {
            MenuAuxEnfController menuauxenf = (MenuAuxEnfController) SubstituirConteudoCena("Visoes/MenuAuxEnf.fxml");
            menuauxenf.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     private void IrParaMenuEnf() {
        try {
            MenuEnfController menuenf = (MenuEnfController) SubstituirConteudoCena("Visoes/MenuEnf.fxml");
            menuenf.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private void IrParaMenuDono() {
        try {
            MenuDonoController menudono = (MenuDonoController) SubstituirConteudoCena("Visoes/MenuDono.fxml");
            menudono.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void IrParaLogin() {
        try {
            LoginController login = (LoginController) SubstituirConteudoCena("Visoes/Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable SubstituirConteudoCena(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Menu.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Menu.class.getResource(fxml));
        AnchorPane pagina;
        try {
            pagina = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        
        double stageLargura = stage.getWidth();
        if (!Double.isNaN(stageLargura)) {
            stageLargura -= (stage.getWidth() - stage.getScene().getWidth());
        }
        
        double stageAltura = stage.getHeight();
        if (!Double.isNaN(stageAltura)) {
            stageAltura -= (stage.getHeight() - stage.getScene().getHeight());
        }
        
        Scene cena = new Scene(pagina);
        if (!Double.isNaN(stageLargura)) {
            pagina.setPrefWidth(stageLargura);
        }
        if (!Double.isNaN(stageAltura)) {
            pagina.setPrefHeight(stageAltura);
        }
        
        stage.setScene(cena);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
    public static void limpar() {
        
    }
    
    public static void mostrarHorariosDisponiveisTESTE() {

    }
}