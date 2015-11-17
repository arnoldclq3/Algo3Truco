package truco.modelo;

import java.util.LinkedList;

public class Ronda {

	private LinkedList<Mano> manos;
	private Equipo equipo1;
	private Equipo equipo2;
	private int cantidadJugadores;
	
	public Ronda(Equipo equipo1, Equipo equipo2 ) {
		
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.manos = new LinkedList<Mano>();
		this.cantidadJugadores = this.equipo1.cantidadJugadores()+this.equipo2.cantidadJugadores();
		this.manos.add(new Mano(cantidadJugadores));
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( this.manos.getLast().manoTerminada() ) {
			this.manos.add(new Mano(cantidadJugadores));
		}
		
		this.manos.getLast().jugarCarta(unJugador, unaCarta);
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.manos.getLast().mostrarUltimaCartaJugadaPor(unJugador) );
	}

	public Jugador enfrentarTodasLasCartas() {
		
		return ( this.manos.getLast().enfrentarTodasLasCartas() );
	}
	
	public Equipo obtenerEquipoGanador() {
		
		int manosGanadasEquipo1 = 0;
		int manosGanadasEquipo2 = 0;
		
		if ( this.manos.size() >= 2 ) {
			
			for (Mano mano : this.manos ) {
				
				Jugador jugador = mano.obtenerGanador();
				
				if ( this.equipo1.estaJugador(jugador) ) manosGanadasEquipo1++;
				if ( this.equipo2.estaJugador(jugador) ) manosGanadasEquipo2++;
			}
		}
		
		return ( ( manosGanadasEquipo1 > manosGanadasEquipo2 )? this.equipo1 : this.equipo2 );
	}

	
}