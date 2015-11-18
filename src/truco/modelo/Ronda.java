package truco.modelo;

import java.util.Collections;
import java.util.LinkedList;

import truco.excepciones.ronda.NoEsElTurnoDeEsteJugadorException;
import truco.excepciones.cantos.RespuestaIncorrectaException;

public class Ronda implements CantosEnvido , CantosFlor , CantosTruco{

	private LinkedList<Jugador> ordenJugadores;
	private LinkedList<Mano> manos;
	private Mano manoActual;
	private Equipo equipo1;
	private Equipo equipo2;
	private Jugador jugadorQueDebeJugar;
	private int cantidadJugadores;
	
	private CantoEnProcesoParaElTanto cantoEnProcesoParaElTanto;
	private CantosEnProcesoParaElTruco cantoEnProcesoParaElTruco;
	
	public Ronda(Equipo equipo1, Equipo equipo2, LinkedList<Jugador> ordenJugadores) {
	
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.ordenJugadores = ordenJugadores;
		this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
		this.manos = new LinkedList<Mano>();
		this.cantidadJugadores = this.equipo1.cantidadJugadores()+this.equipo2.cantidadJugadores();
		this.manoActual = new Mano(this.cantidadJugadores);
		this.manos.add(this.manoActual);
		this.cantoEnProcesoParaElTanto = null;
		this.cantoEnProcesoParaElTruco = null;
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( this.manoActual.estaTerminada() ) {
			
			this.actualizarJugadorQueDebeJugar();			
			this.manoActual = new Mano(this.cantidadJugadores);
			this.manos.add(this.manoActual);
		
		}
		
		this.verificarSiEsteJugadorPuedeJugar(unJugador);
		this.manoActual.jugarCarta(unJugador, unaCarta);
		this.actualizarTurnos();
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.manos.getLast().mostrarUltimaCartaJugadaPor(unJugador) );
	}

	public Jugador enfrentarTodasLasCartas() {
		
		return ( this.manos.getLast().enfrentarTodasLasCartas() );
	}
	
	public Equipo obtenerEquipoGanador() {
		
		int manosGanadasEquipo1 = 0;
		int manosGanadasEquipo2 = 0;
		
		if ( this.manos.size() >= 2 ) {
			
			for (Mano mano : this.manos ) {
				
				Jugador jugador = mano.obtenerGanador();
				
				if ( this.equipo1.estaJugador(jugador) ) manosGanadasEquipo1++;
				if ( this.equipo2.estaJugador(jugador) ) manosGanadasEquipo2++;
			}
		} else {
			
			return null;
		}
		
		return ( ( manosGanadasEquipo1 > manosGanadasEquipo2 )? this.equipo1 : this.equipo2 );
	}
	
	private void verificarSiEsteJugadorPuedeJugar(Jugador unJugador) {
		
		if ( this.jugadorQueDebeJugar != unJugador ) throw new NoEsElTurnoDeEsteJugadorException();
	}
	

	private void actualizarTurnos() {
		
		Collections.rotate(this.ordenJugadores, -1);
		this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
	}
	
	private void actualizarJugadorQueDebeJugar() {
		
		Jugador jugador = this.manoActual.obtenerGanador();
		int posicion = this.ordenJugadores.indexOf(jugador);
		Collections.rotate(ordenJugadores, -posicion);
		this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
	}

	/*************************************************
	 ** 	   		 Cantos Generales			    **
	 *************************************************/
	
	public void quiero(Jugador jugadorQueCanta) {
		if (this.cantoEnProcesoParaElTanto != null && !this.cantoEnProcesoParaElTanto.terminoElProcesoDeCanto(this.cantidadJugadores) )
			this.cantoEnProcesoParaElTanto.quiero(jugadorQueCanta);
		else
			if (this.cantoEnProcesoParaElTruco != null)
				this.cantoEnProcesoParaElTruco.quiero(jugadorQueCanta);
			else 
				throw new RespuestaIncorrectaException();
	}
	
	public void noQuiero(Jugador jugadorQueCanta) {
		if (this.cantoEnProcesoParaElTanto != null && !this.cantoEnProcesoParaElTanto.terminoElProcesoDeCanto(this.cantidadJugadores) )
			this.cantoEnProcesoParaElTanto.noQuiero(jugadorQueCanta);
		else
			if (this.cantoEnProcesoParaElTruco != null)
				this.cantoEnProcesoParaElTruco.noQuiero(jugadorQueCanta);
			else 
				throw new RespuestaIncorrectaException();
	}
	
	
	/*************************************************
	 ** 	    Cantos Generales del Tanto	  		**
	 *************************************************/
	
	private void iniciarProcesoDeTanto(){
		if (this.cantoEnProcesoParaElTanto == null)
			this.cantoEnProcesoParaElTanto = new CantoEnProcesoParaElTanto();
	}
	
	public Jugador jugadorGanadorDelTanto(){
		if (this.cantoEnProcesoParaElTanto == null)
			return null;
		return this.cantoEnProcesoParaElTanto.jugadorGanador();	
	}
	
	private void controlarSiElCantoDelTantoFinalizo() {
		if( !this.cantoEnProcesoParaElTanto.terminoElProcesoDeCanto(this.cantidadJugadores) )
			return;
		Jugador jugadorGanador = this.cantoEnProcesoParaElTanto.jugadorGanador();
		int puntajeGanado = this.cantoEnProcesoParaElTanto.puntosParaElGanador();
		if ( this.equipo1.estaJugador(jugadorGanador) )
			this.equipo1.sumarPuntosAJugador(jugadorGanador, puntajeGanado);
		else
			this.equipo2.sumarPuntosAJugador(jugadorGanador, puntajeGanado);
	}
	
	/*************************************************
	 ** 			   Cantos Envido				**
	 *************************************************/

	@Override
	public void envido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.envido(jugadorQueCanta);
	}

	@Override
	public void realEnvido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.realEnvido(jugadorQueCanta);
	}

	@Override
	public void faltaEnvido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.faltaEnvido(jugadorQueCanta);
		
	}

	@Override
	public void cantarTantoDelEnvido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.cantarTantoDelEnvido(jugadorQueCanta);
		this.controlarSiElCantoDelTantoFinalizo();
	}

	/*************************************************
	 ** 			   Cantos Flor   				**
	 *************************************************/
	
	@Override
	public void flor(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.flor(jugadorQueCanta);		
	}

	@Override
	public void contraFlor(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.contraFlor(jugadorQueCanta);	
	}

	@Override
	public void contraFlorAResto(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.contraFlorAResto(jugadorQueCanta);	
	}

	@Override
	public void cantarTantoDeLaFlor(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeTanto();
		this.cantoEnProcesoParaElTanto.cantarTantoDeLaFlor(jugadorQueCanta);	
		this.controlarSiElCantoDelTantoFinalizo();
	}
	
	/*************************************************
	 ** 	    Cantos Generales del Tanto	  		**
	 *************************************************/

	private void iniciarProcesoDelTruco(){
		if (this.cantoEnProcesoParaElTruco == null)
			this.cantoEnProcesoParaElTruco = new CantosEnProcesoParaElTruco();
	}
	
	
	/*************************************************
	 ** 			   Cantos Truco   				**
	 *************************************************/
	
	@Override
	public void truco(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelTruco();
		this.cantoEnProcesoParaElTruco.truco(jugadorQueCanta);
		
	}

	@Override
	public void reTruco(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelTruco();
		this.cantoEnProcesoParaElTruco.reTruco(jugadorQueCanta);
		
	}

	@Override
	public void valeCuatro(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelTruco();
		this.cantoEnProcesoParaElTruco.valeCuatro(jugadorQueCanta);
		
	}
}