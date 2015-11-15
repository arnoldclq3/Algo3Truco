package truco.modelo;

import java.util.ArrayList;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;

public class Equipo {

	private ArrayList<Jugador> jugadores;
	private Jugador jugadorTurno;

	public Equipo() {
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public Equipo(Jugador unJugador) {
		this.jugadores = new ArrayList<Jugador>();
		
		unJugador.setEsMano(true);
		unJugador.setEsPie(true);
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
				unJugador.setEsMano(true);
				unJugador.setEsPie(true);
				this.jugadores.set(0, unJugador);
				this.jugadorTurno = unJugador;
				break;
			
			case 2:
				unJugador = this.jugadores.get(0);
				unJugador.setEsMano(true);
				unJugador.setEsPie(false);
				this.jugadores.set(0, unJugador);
				this.jugadorTurno = unJugador;
				
				unJugador = this.jugadores.get(1);
				unJugador.setEsMano(false);
				unJugador.setEsPie(true);
				this.jugadores.set(1, unJugador);
				break;
				
			case 3:
				unJugador = this.jugadores.get(0);
				unJugador.setEsMano(true);
				unJugador.setEsPie(false);
				this.jugadores.set(0, unJugador);
				this.jugadorTurno = unJugador;
				
				unJugador = this.jugadores.get(1);
				unJugador.setEsMano(false);
				unJugador.setEsPie(false);
				this.jugadores.set(1, unJugador);
				
				unJugador = this.jugadores.get(2);
				unJugador.setEsMano(false);
				unJugador.setEsPie(true);
				this.jugadores.set(2, unJugador);System.out.println("das");
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
	
	
	
	//reconfigurarPosiciones
		
}
