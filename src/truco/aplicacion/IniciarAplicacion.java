package truco.aplicacion;

import truco.vista.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class IniciarAplicacion extends Application {

	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
    	
    	MenuPrincipal miMenu = new MenuPrincipal(escenarioPrincipal);
    	
    	miMenu.crearMenuBienvenida();
    		
    	escenarioPrincipal.show(); 
    }
}