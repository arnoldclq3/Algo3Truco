package truco.modelo;

import java.util.LinkedList;

import truco.excepciones.mesa.NoSePuedeCantarTantoDosVecesEnUnaRondaException;
import truco.excepciones.mesa.RespuestaInconrrectaException;

public class Mesa {

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private Ronda ronda;
	//	El Listado de cantos del tanto tiene 3 estados posibles
	// 1º) Cuando es NULL jamas en la ronda se canto envido o flor
	// 2º) Cuando el listado esta con elementos, hay un envido o flor en progreso
	// 3º) Cuando hay un listado vacio, ya se canto envido y se calculo el puntaje
	private LinkedList<Canto> listadoCantosDelTanto;
	private Jugador jugadorConMayorTanto;
	private int jugadoresQueCantaronTanto;
	private Equipo nosotros;
	private Equipo ellos;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Equipo nosotros, Equipo ellos) {
		this.nosotros = nosotros;
		this.ellos = ellos;
		this.ronda = new Ronda();
		this.listadoCantosDelTanto = null;
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

	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		this.ronda.jugarCarta(unJugador,unaCarta);
	}
			
	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
				
		return ( this.ronda.mostrarUltimaCartaJugadaPor(unJugador) ); 
	}
	
	
	
	
	/*************************************************
	 ** 		    CANTOS GENERALES			    **
	 *************************************************/

	public void cantarQuiero(Jugador unJugador) {
		if (this.listadoCantosDelTanto != null)
			this.cantarQuieroAlEnvido(unJugador);
		
	}

	
	public void cantarNoQuiero(Jugador unJugador) {
		// TODO
	}
	
	/*************************************************
	 ** 		 	  ENVIDO y FLOR					**
	 *************************************************/
	
	private void cantarQuieroAlEnvido(Jugador unJugador){
		this.jugadorConMayorTanto = null;
	}
	
	
	public Jugador ganadorDelTantoDeLaRondaActual() {
		return this.jugadorConMayorTanto;
	}

	public void cantarTantoDeEnvido(Jugador unJugador) {
		this.jugadoresQueCantaronTanto += 1;
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeEnvido() < unJugador.puntajeEnvido() )
			this.jugadorConMayorTanto = unJugador;
		this.procesoDeAnalisisDelCalculoDePuntosDelTanto();
		
	}

	public void cantarTantoDeFlor(Jugador unJugador) {
		this.jugadoresQueCantaronTanto += 1;
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeFlor() < unJugador.puntajeFlor() )
			this.jugadorConMayorTanto = unJugador;	
		this.procesoDeAnalisisDelCalculoDePuntosDelTanto();
		
	}
	
	private void procesoDeAnalisisDelCalculoDePuntosDelTanto() {
		if (this.cantidadDeJugadores() != this.jugadoresQueCantaronTanto)
			return;
		int puntosGanados = 0;
		for (Canto unCanto : this.listadoCantosDelTanto)
			puntosGanados += unCanto.puntosPorGanar();
		if (nosotros.estaJugador(this.jugadorConMayorTanto))
			nosotros.sumarPuntosAJugador(this.jugadorConMayorTanto, puntosGanados);
		else
			ellos.sumarPuntosAJugador(this.jugadorConMayorTanto, puntosGanados);
		this.listadoCantosDelTanto.clear();	
	}

	public void cantarEnvido(Jugador unJugador) {
		Canto envido = new Envido();
		this.procesoDelCantoDelEnvido(envido);
	}
	
	public void cantarRealEnvido(Jugador unJugador) {
		Canto realEnvido = new RealEnvido();
		this.procesoDelCantoDelEnvido(realEnvido);

	}
	
	private void procesoDelCantoDelEnvido(Canto unCanto){
		// Si la lista ya fue creada y esta vacia implica que se canto el tanto
		if (this.listadoCantosDelTanto != null && this.listadoCantosDelTanto.isEmpty() )
			throw new NoSePuedeCantarTantoDosVecesEnUnaRondaException();
		if (this.listadoCantosDelTanto == null)
			this.listadoCantosDelTanto = new LinkedList<Canto>();
		// Si existe un canto debo verificar que tengo una respuesta valida para dicho canto
		if (!this.listadoCantosDelTanto.isEmpty() && !this.listadoCantosDelTanto.getLast().esUnaRespuestaValidaElCanto(unCanto))
			throw new RespuestaInconrrectaException();
		this.listadoCantosDelTanto.addLast(unCanto);
	}
	
	public void cantarFlor(Jugador unJugador) {
		if (this.listadoCantosDelTanto != null)
			throw new NoSePuedeCantarTantoDosVecesEnUnaRondaException();
		this.listadoCantosDelTanto = new LinkedList<Canto>();
		Canto flor = new Flor();
		this.listadoCantosDelTanto.addLast(flor);	
	}
	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
