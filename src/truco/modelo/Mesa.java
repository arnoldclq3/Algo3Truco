package truco.modelo;

import java.util.LinkedList;


public class Mesa implements CantosEnvido , CantosFlor{

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private Ronda ronda;

	private LinkedList<Canto> cantosTruco;

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
		this.cantosTruco = new LinkedList<Canto>();
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
		this.ronda.quiero(unJugador);
	}
	
	public void cantarNoQuiero(Jugador unJugador) {
		this.ronda.noQuiero(unJugador);
	}
	
	public Jugador ganadorDelTantoDeLaRondaActual(){
		return this.ronda.jugadorGanadorDelTanto();
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
			Canto unReTruco = new Retruco();
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
