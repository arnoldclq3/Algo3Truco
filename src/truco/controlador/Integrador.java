package truco.controlador;

import truco.modelo.*;
import truco.vista.*;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;

public class Integrador implements Observer{

	private Stage stage;
	/* objetos observados */
	private Mesa mesa;
	private Jugador jugadorActual;
	/* objetos observadores */
	private HashMap<Jugador, VentanaJugador> ventanasJugadores;
	private VentanaJuegoTerminado ventanaJuegoTerminado;
	/* controladores */
	
	public Integrador(Stage stage) {
	
		this.jugadorActual = null;
		this.stage = stage;
		this.ventanaJuegoTerminado = new VentanaJuegoTerminado();
		this.ventanasJugadores = new HashMap<Jugador,VentanaJugador>();
	}
	
	public void agregarJugadorObservado(Jugador unJugador) {
		
		VentanaJugador ventanaJugador = new VentanaJugador(this.stage, unJugador);
		this.ventanasJugadores.put(unJugador, ventanaJugador);
	}

	public void iniciar(Mesa mesaJuego) {
		this.mesa = mesaJuego;
		List<Jugador> jugadoresEnJuego = mesaJuego.getJugadoresEnJuego();
		this.mesa.addObserver(this);
		for (Jugador jugadorActual : jugadoresEnJuego){
			this.agregarJugadorObservado(jugadorActual);
		}
		
		this.ventanasJugadores.get( jugadoresEnJuego.get(0) ).mostrar();
	}
	
	private boolean terminoElJuego() {
		
		if ( this.mesa.juegoTerminado() ) {
			Equipo equipoGanador = this.mesa.getEquipoGanador();
			List<Jugador> ganadores = equipoGanador.getJugadores();
			for ( Jugador jugador : ganadores )
				this.ventanaJuegoTerminado.agregarNombreGanador(jugador.getNombre());
			this.ventanaJuegoTerminado.mostrar(stage);
			return true;
		}
		
		return false;
	}

	@Override
	public void update(Observable mesa, Object unJugador) {
		
		if ( this.terminoElJuego() )
			return;
		
		Jugador jugadorQueDebeCantar = this.mesa.getRondaActual().getJugadorQueDebeCantar();
		VentanaJugador ventanaDelJugador = this.ventanasJugadores.get(jugadorQueDebeCantar);
		
		if ( this.jugadorActual != jugadorQueDebeCantar ) {
			VentanaTelon telon = new VentanaTelon(this.stage, ventanaDelJugador, jugadorQueDebeCantar.getNombre());
			telon.mostrar();
		}else{
			ventanaDelJugador.mostrar();
		}
		
		this.jugadorActual = jugadorQueDebeCantar;
	}
}
