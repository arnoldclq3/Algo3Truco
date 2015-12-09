package truco.controlador;

import truco.modelo.*;
import truco.vista.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;

public class Integrador implements Observer{

	private Stage stage;
	/* objetos observados */
	private Mesa mesa;
	private Ronda ronda;
	private Mano mano;
	/* objetos observadores */
	private HashMap<Jugador, VentanaJugador> ventanasJugadores;
	/* controladores */
	
	public Integrador(Stage stage) {
	
		this.stage = stage;
		this.ventanasJugadores = new HashMap<Jugador,VentanaJugador>();
	}
	
	public void agregarJugadorObservado(Jugador unJugador) {
		
		VentanaJugador ventanaJugador = new VentanaJugador(unJugador);
		this.ventanasJugadores.put(unJugador, ventanaJugador);
	}
	
	public void agregarMesaObservada(Mesa unaMesa) {
		
		this.mesa = unaMesa;
		this.mesa.addObserver(this);
		
	}
	
	public void iniciar() {
		
		if ( this.estadoValido() ) {
			Collection<VentanaJugador> valores = this.ventanasJugadores.values();
			((VentanaJugador) valores.iterator().next()).mostrar(stage);
		}
	}

	private boolean estadoValido() {
		
		return ( ventanasJugadores.size() != 0);
	}

	@Override
	public void update(Observable mesa, Object unJugador) {
		//Jugador jugadorQueDebeJugar = this.mesa.getRondaActual().getJugadorQueDebeJugar();
		Jugador jugadorQueDebeCantar = this.mesa.getRondaActual().getJugadorQueDebeCantar();
		
		/*
		System.out.print("Jugador que debe Jugar: ");
		System.out.println( jugadorQueDebeJugar.getNombre() );
		System.out.print("Jugador que debe Cantar: ");
		System.out.println( jugadorQueDebeCantar.getNombre() );
		*/
		
		VentanaJugador ventanaAMostrar = this.ventanasJugadores.get(jugadorQueDebeCantar);
		ventanaAMostrar.mostrar(this.stage);
	}

	
}
