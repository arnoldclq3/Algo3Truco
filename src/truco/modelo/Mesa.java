package truco.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import truco.excepciones.mesa.NoSeJuegaConFlorException;


public class Mesa implements CantosEnvido , CantosFlor , CantosTruco, CantosGenerales{

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	protected Ronda ronda;

	protected Equipo nosotros;
	protected Equipo ellos;

	private boolean juegoTerminado;

	protected LinkedList<Jugador> ordenJugadores;

	private Mazo mazo;

	protected boolean seJuegaConFlor;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Equipo nosotros, Equipo ellos) {
		// Invoca el constructor correcto jugando con flor.
		this(nosotros, ellos, true);
	}
	
	public Mesa(Equipo nosotros, Equipo ellos,boolean seJuegaConFlor){
		this.configurarMesa(nosotros, ellos);
		
		this.mazo = new Mazo();
		this.repartirCartasParaLosJugadores();	
		this.seJuegaConFlor = seJuegaConFlor;	
	}
	
	private void configurarMesa(Equipo nosotros, Equipo ellos){
		this.nosotros = nosotros;
		this.ellos = ellos;
		
		this.ordenJugadores = new LinkedList<Jugador>();
		
		for ( int i = 0 ; i < this.nosotros.cantidadJugadores(); i++ ) {
			
			Jugador unJugador;
			
			unJugador = this.nosotros.siguienteJugador();
			unJugador.setMesa(this);
			
			ordenJugadores.add(unJugador);
			
			unJugador = this.ellos.siguienteJugador();
			unJugador.setMesa(this);
			
			ordenJugadores.add(unJugador);
		}
		
		this.ronda = new Ronda(nosotros, ellos,ordenJugadores);
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
	
	protected void retirarCartasDeLaRonda(){
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
	
	public ArrayList<Carta> mostrarCartasDelJugador(Jugador unJugador) {
		return this.ronda.mostrarCartasDelJugador(unJugador);
	}
	
	
	private void verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego() {
		this.verificarSiExisteUnEquipoGanador();
		if ( this.ronda.estaTerminada() )
			this.generadorDeNuevaRonda();
	}
	
	protected void verificarSiExisteUnEquipoGanador(){
		if ( this.nosotros.esGanador() || this.ellos.esGanador() )
			this.juegoTerminado = true;	
	}
	
	protected void generadorDeNuevaRonda(){
		this.retirarCartasDeLaRonda();
		Collections.rotate(this.ordenJugadores, -1);
		this.ronda = new Ronda(nosotros, ellos,ordenJugadores);
	}

	/*************************************************
	 ** 		    CANTOS GENERALES			    **
	 *************************************************/
	@Override
	public void quiero(Jugador unJugador) {
		this.ronda.quiero(unJugador);
	}
	
	@Override
	public void noQuiero(Jugador unJugador) {
		this.ronda.noQuiero(unJugador);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		this.ronda.jugarCarta(unJugador,unaCarta);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
	}
	
	// Se esta usando para hacer tests en Mesa-Test
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
	
	
	@Override
	public void sonBuenas(Jugador jugadorQueCanta) {
		this.ronda.sonBuenas(jugadorQueCanta);
	}

	/*************************************************
	 ** 			   Cantos Flor   				**
	 *************************************************/
	
	@Override
	public void flor(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.ronda.flor(jugadorQueCanta);
		
	}

	@Override
	public void contraFlor(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.ronda.contraFlor(jugadorQueCanta);
		
	}

	@Override
	public void contraFlorAResto(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.ronda.contraFlorAResto(jugadorQueCanta);
		
	}

	@Override
	public void cantarTantoDeLaFlor(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.ronda.cantarTantoDeLaFlor(jugadorQueCanta);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
	}

	private void controlarSiSeJuegaConFlor() {
		if (!this.seJuegaConFlor)
			throw new NoSeJuegaConFlorException();	
	}


	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
