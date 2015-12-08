package truco.controlador;

import truco.modelo.*;
import truco.interfaz.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class Integrador extends Application {

	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage escenaPrincipal) throws Exception {

    	this.iniciar();
    	
    	VentanaJugador ventanaJugador = new VentanaJugador(escenaPrincipal);
    		
    	escenaPrincipal.show(); 
    }
    
    private void iniciar() {
    		
    }
}