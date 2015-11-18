package truco.modelo;

import java.util.LinkedList;


public class Mesa implements CantosEnvido , CantosFlor , CantosTruco{

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private Ronda ronda;

	private Equipo nosotros;
	private Equipo ellos;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Equipo nosotros, Equipo ellos) {
		this.nosotros = nosotros;
		this.ellos = ellos;
		
		LinkedList<Jugador> ordenJugadores = new LinkedList<Jugador>();
		
		for ( int i = 0 ; i < this.nosotros.cantidadJugadores(); i++ ) {
			
			Jugador unJugador;
			
			unJugador = this.nosotros.siguienteTurno();
			
			ordenJugadores.add(unJugador);
			
			unJugador = this.ellos.siguienteTurno();
			
			ordenJugadores.add(unJugador);
		}
		
		this.ronda = new Ronda(nosotros, ellos,ordenJugadores);
	}
	
	/*************************************************
	 ** 			  Metodos Publicos				**
	 *************************************************/
	
	public int cantidadDeJugadores() {
		return this.nosotros.cantidadJugadores() + this.ellos.cantidadJugadores();
	}
	
	public int mostrarPuntajeEquipoNosotros(){
		return this.nosotros.obtenerCantidadDePuntos();
	}
	
	public int mostrarPuntajeEquipoEllos(){
		return this.ellos.obtenerCantidadDePuntos();
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
				
		return ( this.ronda.mostrarUltimaCartaJugadaPor(unJugador) ); 
	}
	
	
	
	
	/*************************************************
	 ** 		    CANTOS GENERALES			    **
	 *************************************************/

	public void quiero(Jugador unJugador) {
		this.ronda.quiero(unJugador);
	}
	
	public void noQuiero(Jugador unJugador) {
		this.ronda.noQuiero(unJugador);
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		this.ronda.jugarCarta(unJugador,unaCarta);
	}
	
	public Jugador ganadorDelTantoDeLaRondaActual(){
		return this.ronda.jugadorGanadorDelTanto();
	}
	
	
	/*************************************************
	 ** 		 	   Cantos Truco				    **
	 *************************************************/
	
	@Override
	public void truco(Jugador jugadorQueCanta) {
		this.ronda.truco(jugadorQueCanta);
		
	}

	@Override
	public void retruco(Jugador jugadorQueCanta) {
		this.ronda.retruco(jugadorQueCanta);
		
	}

	@Override
	public void valeCuatro(Jugador jugadorQueCanta) {
		this.ronda.valeCuatro(jugadorQueCanta);
		
	}
	
	/*************************************************
	 ** 			   Cantos Envido				**
	 *************************************************/

	@Override
	public void envido(Jugador jugadorQueCanta) {
		this.ronda.envido(jugadorQueCanta);	
	}

	@Override
	public void realEnvido(Jugador jugadorQueCanta) {
		this.ronda.realEnvido(jugadorQueCanta);	
	}

	@Override
	public void faltaEnvido(Jugador jugadorQueCanta) {
		this.ronda.faltaEnvido(jugadorQueCanta);	
		
	}

	@Override
	public void cantarTantoDelEnvido(Jugador jugadorQueCanta) {
		this.ronda.cantarTantoDelEnvido(jugadorQueCanta);	
		
	}

	/*************************************************
	 ** 			   Cantos Flor   				**
	 *************************************************/
	
	@Override
	public void flor(Jugador jugadorQueCanta) {
		this.ronda.flor(jugadorQueCanta);
		
	}

	@Override
	public void contraFlor(Jugador jugadorQueCanta) {
		this.ronda.contraFlor(jugadorQueCanta);
		
	}

	@Override
	public void contraFlorAResto(Jugador jugadorQueCanta) {
		this.ronda.contraFlorAResto(jugadorQueCanta);
		
	}

	@Override
	public void cantarTantoDeLaFlor(Jugador jugadorQueCanta) {
		this.ronda.cantarTantoDeLaFlor(jugadorQueCanta);
		
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
