package truco.modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Mesa {

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private ArrayList<Jugador> jugadores;
	private Ronda ronda;
	private LinkedList<Canto> listadoCantosDelTanto;
	private Jugador jugadorConMayorTanto;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Jugador primerJugador, Jugador segundoJugador) {
		this.jugadores = new ArrayList<Jugador>();
		this.jugadores.add(primerJugador);
		this.jugadores.add(segundoJugador);
		this.ronda = new Ronda();
	}
	
	/*************************************************
	 ** 			  Metodos Publicos				**
	 *************************************************/
	public int cantidadDeJugadores() {
		return this.jugadores.size();
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		this.ronda.jugarCarta(unJugador,unaCarta);
	}
	
	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.ronda.mostrarUltimaCartaJugadaPor(unJugador) ); 
	}

	public void cantarEnvido(Jugador unJugador) {
		this.listadoCantosDelTanto = new LinkedList<Canto>();
		Canto envido = new Envido();
		this.listadoCantosDelTanto.addLast(envido);
	}

	public void cantarQuiero(Jugador unJugador) {
		this.jugadorConMayorTanto = null;
	}

	public void cantarTantoDeEnvido(Jugador unJugador) {
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeEnvido() < unJugador.puntajeEnvido() )
			this.jugadorConMayorTanto = unJugador;
	}

	public Jugador ganadorDelTantoDeLaRondaActual() {
		return this.jugadorConMayorTanto;
	}

	public void cantarFlor(Jugador unJugador) {
		this.listadoCantosDelTanto = new LinkedList<Canto>();
		Canto flor = new Flor();
		this.listadoCantosDelTanto.addLast(flor);	
	}

	public void cantarTantoDeFlor(Jugador unJugador) {
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeFlor() < unJugador.puntajeFlor() )
			this.jugadorConMayorTanto = unJugador;	
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
