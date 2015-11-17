package truco.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import truco.excepciones.mano.NoHayGanadorHastaQueLaManoTermineException;

public class Mano {

	private HashMap<Jugador,Carta> cartasJugadas;
	private int cantidadMaximaCartasAJugar;
	private int cantidadCartasJugadas;
	private Jugador ganador;
	
	public Mano(int cantidad) {
		
		this.cartasJugadas = new HashMap<Jugador,Carta>();
		this.cantidadMaximaCartasAJugar = cantidad;
		this.cantidadCartasJugadas = 0;
		this.ganador = null;
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		this.cartasJugadas.put(unJugador, unaCarta);
		this.cantidadCartasJugadas++;
		
		if ( this.cantidadCartasJugadas == this.cantidadMaximaCartasAJugar ) {
			ganador = this.enfrentarTodasLasCartas();
		}
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.cartasJugadas.get(unJugador) ); 
	}

	public Jugador enfrentarTodasLasCartas() {
		
		Jugador ganador = null;
		Carta cartaGanadora = null;
		
		Collection<Carta> cartas = this.cartasJugadas.values();
		Iterator<Carta> iteradorCarta = cartas.iterator();
		cartaGanadora = iteradorCarta.next();
		
		while (iteradorCarta.hasNext() ) {
			
			Carta carta = iteradorCarta.next();
			if ( cartaGanadora.compararCon(carta) == -1 ) {
				cartaGanadora = carta;
			}
		}
		
		for ( Jugador jugador : cartasJugadas.keySet() ) {
			
			Carta carta = cartasJugadas.get(jugador);
			if ( carta.equals(cartaGanadora) ) {
				ganador = jugador;
			}
		}
		
		return ganador;
	}

	public boolean manoTerminada() {
		
		return false;
	}

	public Jugador obtenerGanador() {
		
		if ( this.ganador == null ) throw new NoHayGanadorHastaQueLaManoTermineException();
		
		return this.ganador;
	}
}
