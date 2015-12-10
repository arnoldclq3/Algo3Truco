package truco.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;
import truco.excepciones.equipo.JugadorInexistenteException;

public class Equipo {

	private ArrayList<Jugador> jugadores;
	private boolean esMano = true; //para saber cual es la mano real: si este atributo es true y si el jugador es mano
	private int puntaje = 0;
	private int puntosParaGanar = 30;
	private Iterator<Jugador> iteradorJugador;

	public Equipo() {
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public void agregarJugador(Jugador unJugador){
		if ( this.jugadorExiste(unJugador) )
			throw new ExisteJugadorEnEquipoException();
		this.jugadores.add(unJugador);
	}
	
	public Jugador siguienteJugador(){
		if (this.iteradorJugador == null || !this.iteradorJugador.hasNext() )
			this.iteradorJugador = this.jugadores.iterator();
		return this.iteradorJugador.next();	
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
	
	

	public boolean esMano() {
		return this.esMano;
	}

	public void setEsMano(boolean esMano){
		this.esMano = esMano;
	}

	public void sumarPuntos(int puntosGanados) {
		
		this.puntaje += puntosGanados;
		
		if ( this.puntaje > this.puntosParaGanar ) this.puntaje = this.puntosParaGanar;
		
	}
	
	public void sumarPuntosAJugador(Jugador unJugador, int puntos) {
		
		if(this.jugadorExiste(unJugador))
			this.sumarPuntos(puntos);
		else
			throw new JugadorInexistenteException();
	}
	

	public int obtenerCantidadDePuntos() {
		return this.puntaje;
	}

	public int obtenerPuntosFaltantesParaGanar() {
		return (this.puntosParaGanar - this.puntaje);
	}

	public boolean esGanador() {
		return (this.puntaje >= this.puntosParaGanar);
	}

	public List<Jugador> getJugadores() {
		
		return this.jugadores;
	}

	

		
}
