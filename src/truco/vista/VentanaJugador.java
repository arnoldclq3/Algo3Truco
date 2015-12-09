package truco.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import truco.modelo.Jugador;

public class VentanaJugador {
	
	private GridPane vistas;
	private Scene escenaJugador;
	private Jugador miJugador;

	public VentanaJugador(Jugador jugador){
		
		this.miJugador = jugador;
		this.iniciarGridVistas();
		this.iniciarBotonera();
		this.iniciarManoJugador();
		this.iniciarMesa();
	}
	
	public void mostrar(Stage stage) {

		stage.setScene(this.escenaJugador);
	}
	
	private void iniciarGridVistas() {
		this.vistas = new GridPane();
		this.vistas.setAlignment(Pos.CENTER);
		this.vistas.setHgap(10);
		this.vistas.setVgap(10);
		this.vistas.setPadding( new Insets(25, 25, 25, 25) );
		this.escenaJugador = new Scene(vistas, 1050, 650, Color.BLACK);
	}

	private void iniciarBotonera() {
		VistaBotonera botonera = new VistaBotonera(this.miJugador);
		miJugador.addObserver(botonera);
		this.vistas.add(botonera.obtenerVista(), 1, 1);
	}
	
	private void iniciarManoJugador(){
		VistaManoJugador manoJugador = new VistaManoJugador();
		miJugador.addObserver(manoJugador);
		this.vistas.add( manoJugador.obtenerVista(), 0, 1);
	}
	
	private void iniciarMesa(){
		VistaMesa mesa = new VistaMesa();
		this.vistas.add( mesa.obtenerVista(), 0, 0);
	}
	

}
