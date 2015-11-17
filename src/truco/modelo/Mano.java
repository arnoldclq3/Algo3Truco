package truco.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Mano {

	private HashMap<Jugador,Carta> cartasJugadas;
	
	public Mano() {
		
		this.cartasJugadas = new HashMap<Jugador,Carta>();
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		this.cartasJugadas.put(unJugador, unaCarta);
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
			if ( cartaGanadora.compararCon(carta) == -1) {
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
}
