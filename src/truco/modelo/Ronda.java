package truco.modelo;

public class Ronda {

	private Mano miMano;
	
	public Ronda() {
		
		this.miMano = null;
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( miMano == null ) {
			miMano = new ManoPrincipal();
		} else {
			miMano = new ManoSecundaria();
		}
		
		miMano.jugarCarta(unJugador,unaCarta);
	}

}