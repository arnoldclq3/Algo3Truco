package truco.modelo;

import java.util.ArrayList;

public class Mesa {

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private ArrayList<Jugador> jugadores;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Jugador primerJugador, Jugador segundoJugador) {
		this.jugadores = new ArrayList<Jugador>();
		this.jugadores.add(primerJugador);
		this.jugadores.add(segundoJugador);
	}
	
	/*************************************************
	 ** 			  Metodos Publicos				**
	 *************************************************/
	public int cantidadDeJugadores() {
		return this.jugadores.size();
	}

	public Jugador resultadoEnvido() {
		if ( this.jugadores.get(0).puntajeEnvido() < this.jugadores.get(1).puntajeEnvido() )
			return this.jugadores.get(1);
		else
			return this.jugadores.get(0);
	}
	
	public Jugador resultadoFlor() {
		if ( this.jugadores.get(0).puntajeFlor() < this.jugadores.get(1).puntajeFlor() )
			return this.jugadores.get(1);
		else
			return this.jugadores.get(0);
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}