package truco.modelo;

import java.util.ArrayList;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;
import truco.excepciones.equipo.JugadorInexistenteException;

public class Equipo {

	private ArrayList<Jugador> jugadores;
	private Jugador jugadorTurno;
	private boolean esMano = true; //para saber cual es la mano real: si este atributo es true y si el jugador es mano
	private int puntaje = 0;

	public Equipo() {
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public Equipo(Jugador unJugador) {
		this.jugadores = new ArrayList<Jugador>();
		
		this.jugadores.add(unJugador);
		
		this.jugadorTurno = unJugador;
	}

	public boolean agregarJugador(Jugador unJugador) {

		if(!this.jugadorExiste(unJugador)){
			this.jugadores.add(unJugador);
			this.reconfigurarPosiciones();
			return true;
		}
		
		throw new ExisteJugadorEnEquipoException();
		
	}
	
	private void reconfigurarPosiciones(){
		
		int cantidadJugadores = this.cantidadJugadores();
		Jugador unJugador;
		
		switch(cantidadJugadores){
			case 1:
				unJugador = this.jugadores.get(0);
				this.jugadores.set(0, unJugador);
				this.jugadorTurno = unJugador;
				break;
			
			case 2:
				unJugador = this.jugadores.get(0);
				this.jugadores.set(0, unJugador);
				this.jugadorTurno = unJugador;
				
				unJugador = this.jugadores.get(1);
				this.jugadores.set(1, unJugador);
				break;
				
			case 3:
				unJugador = this.jugadores.get(0);
				this.jugadores.set(0, unJugador);
				this.jugadorTurno = unJugador;
				
				unJugador = this.jugadores.get(1);
				this.jugadores.set(1, unJugador);
				
				unJugador = this.jugadores.get(2);
				this.jugadores.set(2, unJugador);
				break;
			default:
				//no hago nada
		}
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

	public boolean estaJugador(Jugador unJugador) {
		return this.jugadorExiste(unJugador);
	}
	
	public Jugador siguienteTurno() {
		Jugador jugadorTurno = this.jugadorTurno;
		
		for(int i=0;i<this.jugadores.size();i++){
			
			if(this.jugadores.get(i).equals(jugadorTurno)){
				
				if(i==this.jugadores.size()-1){
					this.jugadorTurno = this.jugadores.get(0);
				} else{
					this.jugadorTurno = this.jugadores.get(i+1);
				}
				
			}
			
		}
	
		return jugadorTurno;
		
	}
	
	public void setEsMano(boolean esMano){
		this.esMano = esMano;
	}

	public boolean sumarPuntosAJugador(Jugador unJugador, int puntos) {
		
		if(this.jugadorExiste(unJugador)){
			this.puntaje  += puntos;
			return true;
		} else{
			throw new JugadorInexistenteException();
		}
		
	}

	public int obtenerCantidadDePuntos() {
		return this.puntaje;
	}

	public boolean esMano() {
		return this.esMano;
	}

	public void sumarPuntos(int puntosGanados) {
		this.puntaje += puntosGanados;
		
	}

		
}
