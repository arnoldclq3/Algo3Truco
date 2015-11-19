package truco.modelo;

import java.util.Collections;
import java.util.LinkedList;


public class Mesa implements CantosEnvido , CantosFlor , CantosTruco{

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private Ronda ronda;

	private Equipo nosotros;
	private Equipo ellos;

	private boolean juegoTerminado;

	private LinkedList<Jugador> ordenJugadores;

	private Mazo mazo;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Equipo nosotros, Equipo ellos) {
		this.nosotros = nosotros;
		this.ellos = ellos;
		
		this.ordenJugadores = new LinkedList<Jugador>();
		
		for ( int i = 0 ; i < this.nosotros.cantidadJugadores(); i++ ) {
			
			Jugador unJugador;
			
			unJugador = this.nosotros.siguienteTurno();
			
			ordenJugadores.add(unJugador);
			
			unJugador = this.ellos.siguienteTurno();
			
			ordenJugadores.add(unJugador);
		}
		
		this.ronda = new Ronda(nosotros, ellos,ordenJugadores);
		this.mazo = new Mazo();
		this.repartirCartasParaLosJugadores();	
	}
	
	/*************************************************
	 ** 	    Interacciones con el Mazo	        **
	 *************************************************/
	
	private void repartirCartasParaLosJugadores() {
		for (int cantidadCartas = 1 ; cantidadCartas <= 3 ; cantidadCartas++ )
			for (Jugador unJugador : this.ordenJugadores){
				Carta unaCarta = this.mazo.repartirCarta();
				unJugador.tomarCarta(unaCarta);
			}
	}
	
	private void retirarCartasDeLaRonda(){
		for (Jugador unJugador : this.ordenJugadores){
			this.mazo.devolverCartas( unJugador.devolverCartas() );
		}
		this.mazo.devolverCartas( this.ronda.devolverCartas() );
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
	
	public boolean juegoTerminado(){
		return this.juegoTerminado;
	}
	
	
	
	private void verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego() {
		this.verificarSiExisteUnEquipoGanador();
		if ( this.ronda.estaTerminada() )
			this.generadorDeNuevaRonda();
	}
	
	private void verificarSiExisteUnEquipoGanador(){
		if ( this.nosotros.esGanador() || this.ellos.esGanador() )
			this.juegoTerminado = true;	
	}
	
	private void generadorDeNuevaRonda() {
		this.retirarCartasDeLaRonda();
		Collections.rotate(this.ordenJugadores, -1);
		this.ronda = new Ronda(nosotros, ellos,ordenJugadores);
	}

	/*************************************************
	 ** 		    CANTOS GENERALES			    **
	 *************************************************/

	public void quiero(Jugador unJugador) {
		this.ronda.quiero(unJugador);
	}
	
	public void noQuiero(Jugador unJugador) {
		this.ronda.noQuiero(unJugador);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		this.ronda.jugarCarta(unJugador,unaCarta);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
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
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();	
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
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
