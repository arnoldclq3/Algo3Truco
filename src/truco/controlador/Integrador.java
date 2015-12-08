package truco.controlador;

import truco.modelo.*;
import truco.vista.*;

import java.util.LinkedList;
import java.util.List;

import javafx.stage.Stage;

public class Integrador {

	private Stage stage;
	/* objetos observados */
	private List<Jugador> jugadores;
	private Mesa mesa;
	private Ronda ronda;
	private Mano mano;
	/* objetos observadores */
	private List<VentanaJugador> ventanasJugadores;
	/* controladores */
	
	public Integrador(Stage stage) {
	
		this.stage = stage;
		this.jugadores = new LinkedList<Jugador>();
		this.ventanasJugadores = new LinkedList<VentanaJugador>();
	}
	
	public void agregarJugadorObservado(Jugador unJugador) {
		
		this.jugadores.add(unJugador);
		VentanaJugador ventanaJugador = new VentanaJugador(unJugador);
		this.ventanasJugadores.add(ventanaJugador);
	}
	
	public void agregarMesaObservada(Mesa unaMesa) {
		
		this.mesa = unaMesa;
	}
	
	public void iniciar() {
		
		if ( this.estadoValido() ) {
			this.ventanasJugadores.get(0).mostrar(stage);
		}
	}

	private boolean estadoValido() {
		
		return ( this.jugadores.size() != 0 && ventanasJugadores.size() != 0);
	}
	
	
}
