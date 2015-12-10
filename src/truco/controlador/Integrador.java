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
	//private Ronda ronda;
	//private Mano mano;
	/* objetos observadores */
	private HashMap<Jugador, VentanaJugador> ventanasJugadores;
	private VistaJuegoTerminado ventanaJuegoTerminado;
	/* controladores */
	
	public Integrador(Stage stage) {
	
		this.stage = stage;
		this.ventanaJuegoTerminado = new VistaJuegoTerminado();
		this.ventanasJugadores = new HashMap<Jugador,VentanaJugador>();
	}
	
	public void agregarJugadorObservado(Jugador unJugador) {
		
		VentanaJugador ventanaJugador = new VentanaJugador(unJugador);
		this.ventanasJugadores.put(unJugador, ventanaJugador);
	}
	/*
	public void agregarMesaObservada(Mesa laMesa) {
		
		this.mesa = laMesa;
		this.mesa.addObserver(this);
		
		LinkedList<VentanaJugador> ventanas = new LinkedList<VentanaJugador>();
		ventanas.addAll(this.ventanasJugadores.values());
		
		for ( VentanaJugador ventana : ventanas ) {
			ventana.iniciarMesa();
			ventana.iniciarVistaPuntaje();
		}
	}
	*/
	public void iniciar(Mesa mesaJuego) {
		this.mesa = mesaJuego;
		List<Jugador> jugadoresEnJuego = mesaJuego.getJugadoresEnJuego();
		this.mesa.addObserver(this);
		for (Jugador jugadorActual : jugadoresEnJuego){
			this.agregarJugadorObservado(jugadorActual);
		}
		
		this.ventanasJugadores.get( jugadoresEnJuego.get(0) ).mostrar(stage);
		/*
		if ( this.estadoValido() ) {
			
			Collection<VentanaJugador> valores = this.ventanasJugadores.values();
			((VentanaJugador) valores.iterator().next()).mostrar(stage);
		}
		*/
	}

	/*
	private boolean estadoValido() {
		
		return ( ventanasJugadores.size() != 0);
	}
	*/
	@Override
	public void update(Observable mesa, Object unJugador) {
		//Jugador jugadorQueDebeJugar = this.mesa.getRondaActual().getJugadorQueDebeJugar();
		
		if ( this.mesa.juegoTerminado() ) {
			Equipo equipoGanador = this.mesa.getEquipoGanador();
			List<Jugador> ganadores = equipoGanador.getJugadores();
			for ( Jugador jugador : ganadores )
				this.ventanaJuegoTerminado.agregarNombreGanador(jugador.getNombre());
			this.ventanaJuegoTerminado.mostrar(stage);
			return;
		}
		
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
