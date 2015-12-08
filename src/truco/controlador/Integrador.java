package truco.controlador;

import truco.modelo.*;
import truco.vista.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class Integrador extends Application {

	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

    	this.iniciar();
    	
    	MenuPrincipal miMenu = new MenuPrincipal(escenarioPrincipal);
    	miMenu.crearMenuBienvenida();
    		
    	escenarioPrincipal.show(); 
    }
    
    private void iniciar() {
    		
    }
}