package truco.modelo;

public class Ronda {

	private Mano miMano;
	
	public Ronda() {
		
		this.miMano = null;
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( miMano == null ) {
			this.miMano = new ManoPrincipal();
		} else {
			this.miMano = new ManoSecundaria();
		}
		
		miMano.jugarCarta(unJugador,unaCarta);
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.miMano.mostrarUltimaCartaJugadaPor(unJugador) );
	}

}