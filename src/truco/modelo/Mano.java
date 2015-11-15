package truco.modelo;

import java.util.HashMap;


public class Mano {

	HashMap<Jugador,Carta> cartasJugadas;
	
	public Mano() {
		
		this.cartasJugadas = new HashMap<Jugador,Carta>();
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		this.cartasJugadas.put(unJugador, unaCarta);
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.cartasJugadas.get(unJugador) ); 
	}
}
