package truco.modelo;

import java.util.ArrayList;
import java.util.LinkedList;

import truco.excepciones.mesa.NoSePuedeCantarTantoDosVecesEnUnaRonda;
import truco.excepciones.mesa.RespuestaInconrrecta;

public class Mesa {

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private ArrayList<Jugador> jugadores;
	private Ronda ronda;
	//	El Listado de cantos del tanto tiene 3 estados posibles
	// 1º) Cuando es NULL jamas en la ronda se canto envido o flor
	// 2º) Cuando el listado esta con elementos, hay un envido o flor en progreso
	// 3º) Cuando hay un listado vacio, ya se canto envido y se calculo el puntaje
	private LinkedList<Canto> listadoCantosDelTanto;
	private LinkedList<Canto> cantosTruco;
	private Jugador jugadorConMayorTanto;
	private Equipo equipoNosotros;
	private Equipo equipoEllos;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Jugador primerJugador, Jugador segundoJugador) {
		this.jugadores = new ArrayList<Jugador>();
		this.jugadores.add(primerJugador);
		this.jugadores.add(segundoJugador);
		this.ronda = new Ronda();
		this.listadoCantosDelTanto = null;
		this.cantosTruco = new LinkedList<Canto>();
	}
	
	/*************************************************
	 ** 			  Metodos Publicos				**
	 *************************************************/
	
	public int cantidadDeJugadores() {
		return this.jugadores.size();
	}
	
	public int mostrarPuntajeEquipoNosotros(){
		return this.equipoNosotros.obtenerCantidadDePuntos();
	}
	
	public int mostrarPuntajeEquipoEllos(){
		return this.equipoEllos.obtenerCantidadDePuntos();
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
	
	public void cantarTantoDeEnvido(Jugador unJugador) {
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeEnvido() < unJugador.puntajeEnvido() )
			this.jugadorConMayorTanto = unJugador;
		// Por ahora vacio la lista, pero deberia hacer el caculo cuando cantaron todos los jugadores
		this.listadoCantosDelTanto.clear();
	}

	public Jugador ganadorDelTantoDeLaRondaActual() {
		return this.jugadorConMayorTanto;
	}


	public void cantarTantoDeFlor(Jugador unJugador) {
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeFlor() < unJugador.puntajeFlor() )
			this.jugadorConMayorTanto = unJugador;	
		// Por ahora vacio la lista, pero deberia hacer el caculo cuando cantaron todos los jugadores
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
			throw new NoSePuedeCantarTantoDosVecesEnUnaRonda();
		if (this.listadoCantosDelTanto == null)
			this.listadoCantosDelTanto = new LinkedList<Canto>();
		// Si existe un canto debo verificar que tengo una respuesta valida para dicho canto
		if (!this.listadoCantosDelTanto.isEmpty() && !this.listadoCantosDelTanto.getLast().esUnaRespuestaValidaElCanto(unCanto))
			throw new RespuestaInconrrecta();
		this.listadoCantosDelTanto.addLast(unCanto);
	}
	
	public void cantarFlor(Jugador unJugador) {
		if (this.listadoCantosDelTanto != null)
			throw new NoSePuedeCantarTantoDosVecesEnUnaRonda();
		this.listadoCantosDelTanto = new LinkedList<Canto>();
		Canto flor = new Flor();
		this.listadoCantosDelTanto.addLast(flor);	
	}
	
	/*************************************************
	 ** 		 	     TRUCO					    **
	 *************************************************/
	
	public void cantaTrucoElJugador(Jugador unJugador) {
		
		this.verificarSiPuedeJugarElJugador(unJugador);
		
		if ( this.cantosTruco.isEmpty() ) {
			Truco unTruco = new Truco();
			this.cantosTruco.add(unTruco);
		}
	}
		
	
	public void cantaReTrucoElJugador(Jugador unJugador) {
		
		this.verificarSiPuedeJugarElJugador(unJugador);
		
		if ( !this.cantosTruco.isEmpty() ) {
			Canto unReTruco = new ReTruco();
			Canto ultimoCanto = this.cantosTruco.getLast();
			
			if ( ultimoCanto.esUnaRespuestaValidaElCanto(unReTruco) ) {
				this.cantosTruco.add(unReTruco);
			}
		}
	}
	
	public void cantaValeCuatroElJugador(Jugador unJugador) {
		
		this.verificarSiPuedeJugarElJugador(unJugador);
		
		if ( !this.cantosTruco.isEmpty() ) {
			Canto unValeCuatro = new ValeCuatro();
			Canto ultimoCanto = this.cantosTruco.getLast();
			
			if ( ultimoCanto.esUnaRespuestaValidaElCanto(unValeCuatro) ) {
				this.cantosTruco.add(unValeCuatro);
			}
		}
	}
	
	public void cantaQuieroElJugador(Jugador unJugador) {
		
		this.verificarSiPuedeJugarElJugador(unJugador);
		
		if ( !this.cantosTruco.isEmpty() ) {
			this.cantosTruco.clear();
		}
	}
	
	public Jugador enfrentarTodasLasCartasJugadas() {
		
		return this.ronda.enfrentarTodasLasCartas();
	}
	
	private void verificarSiPuedeJugarElJugador(Jugador unJugador) {
		
		/* metodo en construccion !!! */
	}
	
	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
