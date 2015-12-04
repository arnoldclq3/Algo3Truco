package truco.app;

import truco.modelo.Equipo;
import truco.modelo.GeneradorRondasNormales;
import truco.modelo.Jugador;
import truco.modelo.Mazo;
import truco.modelo.Mesa;
import truco.controlador.ControladorBotonDevolverCartas;
import truco.controlador.ControladorBotonRepartirCartas;
import truco.controlador.ControladorJugadorTirarCarta;
import truco.vista.VentanaPrincipal;
import truco.vista.VentanaJugador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Aplicacion extends Application {
	
	private Jugador miJugador;
	private Mazo miMazo;
	private Jugador jugador1;
	private Jugador jugador2;
	private Equipo equipo1;
	private Equipo equipo2;
	private Mesa mesa;
	
	private static VentanaPrincipal miVentanaPrincipal;
	private static VentanaJugador ventanaJugador1;
	private static VentanaJugador ventanaJugador2;
	
	private static Stage jugador1Stage;
	private static Stage jugador2Stage;
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
    public void start(Stage miStage) throws Exception {

		this.jugador1Stage = new Stage();
		this.jugador2Stage = new Stage();
    	this.iniciar();
        
    	this.miVentanaPrincipal.crearVentana(miStage);
    	miStage.setTitle("Mesa de Truco 2 vs 2");
    	miStage.show();
    	
    	this.ventanaJugador1.crearVentana(jugador1Stage,this.jugador1);
    	this.jugador1Stage.setTitle("Jugador 1");
    	this.jugador1Stage.show();
        
        this.ventanaJugador2.crearVentana(jugador2Stage,this.jugador1);
        this.jugador2Stage.setTitle("Jugador 2");
        this.jugador2Stage.show();
    }
    
    private void iniciar() {
    	
    	// Vistas
    	this.miVentanaPrincipal = new VentanaPrincipal();	
    	this.ventanaJugador1 = new VentanaJugador(this.jugador1);
    	this.ventanaJugador2 = new VentanaJugador(this.jugador2);
    	
    	/* Creacion de las clases Principales */
    	// Modelos
    	this.jugador1 = new Jugador("Jugador 1");
		this.jugador2 = new Jugador("Jugador 2");
		
		this.jugador1.addObserver(this.ventanaJugador1);
		this.jugador1.addObserver(this.miVentanaPrincipal);
		
    	this.jugador2.addObserver(this.ventanaJugador2);
    	this.jugador2.addObserver(this.miVentanaPrincipal);
    	
		this.equipo1 = new Equipo();
		equipo1.agregarJugador(this.jugador1);
		this.equipo2 = new Equipo();
		equipo2.agregarJugador(this.jugador2);
		this.mesa = new Mesa(equipo1,equipo2, new GeneradorRondasNormales() );
    	
    	/* Creacion de los Controladores y Conexion */
    	
    	//ControladorBotonRepartirCartas miControlador2 = new ControladorBotonRepartirCartas(miJugador, miMazo);	// Controlador
    	//this.miVentanaPrincipal.setBotonDevolverCartas(miControlador1);
    	//this.miVentanaPrincipal.setBotonRepartirCartas(miControlador2);
    	
    	
    	
    }


}