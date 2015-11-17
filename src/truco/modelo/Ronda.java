package truco.modelo;

public class Ronda { 
	private Mano miMano;
	private boolean manoTerminada;
	
	public Ronda() {
		this.miMano = new ManoPrincipal();
		this.manoTerminada = false;
	}
	
	public Ronda(Equipo equipo, Equipo equipo2) {
		this.miMano = new ManoPrincipal();
		this.manoTerminada = false;
	}
	
	public Ronda(Equipo equipo, Equipo equipo2, Equipo equipo3) {
		this.miMano = new ManoPrincipal();
		this.manoTerminada = false;
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		if ( ! this.manoTerminada ) {
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

	public Equipo equipoEnTurno() {
		Equipo unEquipo = new Equipo();
		return unEquipo;
	}
	
	public Equipo equipoGanadorDeLaRonda() {
		Equipo unEquipo = new Equipo();
		return unEquipo;
	}
	
}