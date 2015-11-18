package truco.modelo;

import java.util.Collections;
import java.util.LinkedList;

import truco.excepciones.ronda.NoEsElTurnoDeEsteJugadorException;

public class Ronda {

	private LinkedList<Jugador> ordenJugadores;
	private LinkedList<Mano> manos;
	private Mano manoActual;
	private Equipo equipo1;
	private Equipo equipo2;
	private Jugador jugadorQueDebeJugar;
	private int cantidadJugadores;
	 
	public Ronda(Equipo equipo1, Equipo equipo2, LinkedList<Jugador> ordenJugadores) {
	
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.ordenJugadores = ordenJugadores;
		this.jugadorQueDebeJugar = ordenJugadores.getFirst();
		this.manos = new LinkedList<Mano>();
		this.cantidadJugadores = this.equipo1.cantidadJugadores()+this.equipo2.cantidadJugadores();
		this.manoActual = new Mano(this.cantidadJugadores);
		this.manos.add(this.manoActual);
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( this.manoActual.estaTerminada() ) {
			
			this.actualizarJugadorQueDebeJugar();			
			this.manoActual = new Mano(this.cantidadJugadores);
			this.manos.add(this.manoActual);
		
		}
		
		this.verificarSiEsteJugadorPuedeJugar(unJugador);
		this.manoActual.jugarCarta(unJugador, unaCarta);
		this.actualizarTurnos();
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
		} else {
			
			return null;
		}
		
		return ( ( manosGanadasEquipo1 > manosGanadasEquipo2 )? this.equipo1 : this.equipo2 );
	}
	
	private void verificarSiEsteJugadorPuedeJugar(Jugador unJugador) {
		
		if ( this.jugadorQueDebeJugar != unJugador ) throw new NoEsElTurnoDeEsteJugadorException();
	}
	

	private void actualizarTurnos() {
		
		Collections.rotate(this.ordenJugadores, -1);
		this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
	}
	
	private void actualizarJugadorQueDebeJugar() {
		
		Jugador jugador = this.manoActual.obtenerGanador();
		int posicion = this.ordenJugadores.indexOf(jugador);
		Collections.rotate(ordenJugadores, -posicion);
		this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
	}

	
	
}