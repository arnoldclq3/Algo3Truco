package truco.modelo;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import truco.excepciones.mesa.NoSeJuegaConFlorException;


public class Mesa extends Observable implements CantosEnvido , CantosFlor , CantosTruco, CantosGenerales, Acciones {

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private Ronda rondaActual;
	private List<Ronda> rondasAJugar;

	private Equipo nosotros;
	private Equipo ellos;
	private List<Jugador> jugadoresEnJuego;
	
	private boolean juegoTerminado;

	private Mazo mazo;
	
	private GeneradorRondas generador;

	private boolean seJuegaConFlor;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Equipo nosotros, Equipo ellos, GeneradorRondas generador) {
		// Invoca el constructor correcto jugando con flor.
		this(nosotros, ellos, generador, true);
	}
	
	public Mesa(Equipo nosotros, Equipo ellos,GeneradorRondas generador,boolean seJuegaConFlor){
		this.generador = generador;
		this.mazo = new Mazo();	
		this.seJuegaConFlor = seJuegaConFlor;	
		
		this.configurarMesa(nosotros, ellos);
	}
	
	private void configurarMesa(Equipo nosotros, Equipo ellos){
		this.nosotros = nosotros;
		this.ellos = ellos;
		
		this.jugadoresEnJuego = new LinkedList<Jugador>();
		
		for ( int i = 0 ; i < this.nosotros.cantidadJugadores(); i++ ) {
			
			Jugador unJugador;
			
			unJugador = this.nosotros.siguienteJugador();
			unJugador.setMesa(this);
			
			jugadoresEnJuego.add(unJugador);
			
			unJugador = this.ellos.siguienteJugador();
			unJugador.setMesa(this);
			
			jugadoresEnJuego.add(unJugador);
		}
		
		this.procesoDeActualizarRondaActual();
	}
	
	private void procesoDeActualizarRondaActual() {
		if (this.rondasAJugar == null || this.rondasAJugar.isEmpty() ){
			this.rondasAJugar = this.generador.generar(this.nosotros, this.ellos , jugadoresEnJuego);
			this.repartirCartasParaLosJugadores();
		}
		
		this.rondaActual = this.rondasAJugar.remove(0);
	}
	
	private void notificarObservadores(){
		this.setChanged();
		this.notifyObservers();
	}

	/*************************************************
	 ** 	    Interacciones con el Mazo	        **
	 *************************************************/
	
	private void repartirCartasParaLosJugadores() {
		for (int cantidadCartas = 1 ; cantidadCartas <= 3 ; cantidadCartas++ )
			for (Jugador unJugador : this.jugadoresEnJuego){
				Carta unaCarta = this.mazo.repartirCarta();
				unJugador.tomarCarta(unaCarta);
			}
	}
	
	protected void retirarCartasDeLaRonda(){
		for (Jugador unJugador : this.jugadoresEnJuego){
			this.mazo.devolverCartas( unJugador.devolverCartas() );
		}
		this.mazo.devolverCartas( this.rondaActual.devolverCartas() );
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
				
		return ( this.rondaActual.mostrarUltimaCartaJugadaPor(unJugador) ); 
	}
	
	public boolean juegoTerminado(){
		return this.juegoTerminado;
	}
	
	public List<Carta> mostrarCartasDelJugador(Jugador unJugador) {
		return this.rondaActual.mostrarCartasDelJugador(unJugador);
	}
	
	
	private void verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego() {
		this.verificarSiExisteUnEquipoGanador();
		if ( this.rondaActual.estaTerminada() )
			this.generadorDeNuevaRonda();
	}
	
	protected void verificarSiExisteUnEquipoGanador(){
		if ( this.nosotros.esGanador() || this.ellos.esGanador() )
			this.juegoTerminado = true;	
	}
	
	protected void generadorDeNuevaRonda(){
		this.retirarCartasDeLaRonda();
		Collections.rotate(this.jugadoresEnJuego, -1);
		this.procesoDeActualizarRondaActual();
	}

	/*************************************************
	 ** 		    CANTOS GENERALES			    **
	 *************************************************/
	@Override
	public void quiero(Jugador unJugador) {
		this.rondaActual.quiero(unJugador);
		this.notificarObservadores();
	}
	
	@Override
	public void noQuiero(Jugador unJugador) {
		this.rondaActual.noQuiero(unJugador);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
		this.notificarObservadores();
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		this.verificarSiExisteUnEquipoGanador();
		this.rondaActual.jugarCarta(unJugador,unaCarta);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
		
		this.notificarObservadores();
	}
	
	public void irseAlMazo(Jugador unJugador) {
		this.verificarSiExisteUnEquipoGanador();
		this.rondaActual.irseAlMazo(unJugador);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
		this.notificarObservadores();
	}
	
	// Se esta usando para hacer tests en Mesa-Test
	public Jugador ganadorDelTantoDeLaRondaActual(){
		return this.rondaActual.jugadorGanadorDelTanto();
	}
	
	
	/*************************************************
	 ** 		 	   Cantos Truco				    **
	 *************************************************/
	
	@Override
	public void truco(Jugador jugadorQueCanta) {
		this.rondaActual.truco(jugadorQueCanta);
		this.notificarObservadores();
		
	}

	@Override
	public void retruco(Jugador jugadorQueCanta) {
		this.rondaActual.retruco(jugadorQueCanta);
		this.notificarObservadores();
		
	}

	@Override
	public void valeCuatro(Jugador jugadorQueCanta) {
		this.rondaActual.valeCuatro(jugadorQueCanta);
		this.notificarObservadores();
	}
	
	/*************************************************
	 ** 			   Cantos Envido				**
	 *************************************************/

	@Override
	public void envido(Jugador jugadorQueCanta) {
		this.rondaActual.envido(jugadorQueCanta);	
		this.notificarObservadores();
	}

	@Override
	public void realEnvido(Jugador jugadorQueCanta) {
		this.rondaActual.realEnvido(jugadorQueCanta);
		this.notificarObservadores();
	}

	@Override
	public void faltaEnvido(Jugador jugadorQueCanta) {
		this.rondaActual.faltaEnvido(jugadorQueCanta);	
		this.notificarObservadores();
		
	}

	@Override
	public void cantarTantoDelEnvido(Jugador jugadorQueCanta) {
		this.rondaActual.cantarTantoDelEnvido(jugadorQueCanta);	
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();	
		this.notificarObservadores();
	}
	
	
	@Override
	public void sonBuenas(Jugador jugadorQueCanta) {
		this.rondaActual.sonBuenas(jugadorQueCanta);
		this.notificarObservadores();
	}

	/*************************************************
	 ** 			   Cantos Flor   				**
	 *************************************************/
	
	@Override
	public void flor(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.rondaActual.flor(jugadorQueCanta);
		this.notificarObservadores();
	}

	@Override
	public void contraFlor(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.rondaActual.contraFlor(jugadorQueCanta);
		this.notificarObservadores();
	}

	@Override
	public void contraFlorAResto(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.rondaActual.contraFlorAResto(jugadorQueCanta);
		this.notificarObservadores();
		
	}

	@Override
	public void cantarTantoDeLaFlor(Jugador jugadorQueCanta) {
		this.controlarSiSeJuegaConFlor();
		this.rondaActual.cantarTantoDeLaFlor(jugadorQueCanta);
		this.verificarLaPosibilidadDeUnaFinalizacionDeRondaODelJuego();
		this.notificarObservadores();
	}

	private void controlarSiSeJuegaConFlor() {
		if (!this.seJuegaConFlor)
			throw new NoSeJuegaConFlorException();	
	}

	
	/*************************************************
	 ** 		 	 	 GETTERS					**
	 *************************************************/
	
	public Ronda getRondaActual(){
		return this.rondaActual;
	}
	

	public boolean seJuegaConFlor(){
		return this.seJuegaConFlor;

	}

	public Equipo getEquipo1() {
		return this.ellos;
	}

	public Equipo getEquipo2() {
		return this.nosotros;
	}

	public List<Jugador> getJugadoresEnJuego() {
		return this.jugadoresEnJuego;
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
