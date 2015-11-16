package truco.modelo;

public class Ronda {

	private Mano miMano;
	private boolean manoTerminada;
	
	public Ronda() {
		
		this.miMano = new ManoPrincipal();
		this.manoTerminada = false;
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( this.manoTerminada ) {
			this.miMano = new ManoSecundaria();
		}
		
		this.miMano.jugarCarta(unJugador,unaCarta);
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.miMano.mostrarUltimaCartaJugadaPor(unJugador) );
	}

	public Jugador enfrentarTodasLasCartas() {
		
		return this.miMano.enfrentarTodasLasCartas();
		
	}

}