package truco.modelo;

import java.util.ArrayList;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;
import truco.excepciones.jugador.CartaEnManoInexistente;

public class Equipo {

	private ArrayList<Jugador> jugadores;

	public Equipo() {
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public Equipo(Jugador unJugador) {
		this.jugadores = new ArrayList<Jugador>();
		this.jugadores.add(unJugador);
	}

	public boolean agregarJugador(Jugador unJugador) {

		if(!this.jugadorExiste(unJugador)){
			this.jugadores.add(unJugador);
			return true;
		}
		
		throw new ExisteJugadorEnEquipoException();
		
	}

	private boolean jugadorExiste(Jugador unJugador) {
		
		for (Jugador otroJugador : this.jugadores){
			if(otroJugador.equals(unJugador)){
				return true;
			}
			
		}
		return false;
	}

	public int cantidadJugadores() {
		return this.jugadores.size();
	}
	
	
	
	
		
}
