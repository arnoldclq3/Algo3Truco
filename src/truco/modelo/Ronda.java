package truco.modelo;

import java.util.Collections;
import java.util.LinkedList;

import truco.excepciones.mano.NoHayGanadorHuboEmpateException;
import truco.excepciones.ronda.NoEsElTurnoDeEsteJugadorException;
import truco.excepciones.ronda.NoSePuedeJugarMasCartasRondaTerminadaException;
import truco.excepciones.cantos.RespuestaIncorrectaException;

public class Ronda implements CantosEnvido , CantosFlor , CantosTruco{

	private LinkedList<Jugador> ordenJugadores;
	private LinkedList<Mano> manos;
	private Mano manoActual;
	private Equipo equipo1;
	private Equipo equipo2;
	private Equipo equipoGanador;
	private Jugador jugadorQueDebeJugar;
	private int cantidadJugadores;
	private boolean hayEquipoGanador;
	
	private CantoEnProcesoParaElTanto cantoEnProcesoParaElTanto;
	private CantosEnProcesoParaElTruco cantoEnProcesoParaElTruco;
	
	public Ronda(Equipo equipo1, Equipo equipo2, LinkedList<Jugador> ordenJugadores) {
	
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.equipoGanador = null;
		this.ordenJugadores = ordenJugadores;
		this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
		this.manos = new LinkedList<Mano>();
		this.cantidadJugadores = this.equipo1.cantidadJugadores()+this.equipo2.cantidadJugadores();
		this.manoActual = new Mano(this.cantidadJugadores);
		this.manos.add(this.manoActual);
		this.cantoEnProcesoParaElTanto = null;
		this.cantoEnProcesoParaElTruco = null;
		this.hayEquipoGanador = false;
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( this.hayEquipoGanador ) throw new NoSePuedeJugarMasCartasRondaTerminadaException();
		
		this.verificarSiEsteJugadorPuedeJugar(unJugador);
		this.manoActual.jugarCarta(unJugador, unaCarta);
		
		if ( this.manoActual.estaTerminada() ) {
			
			this.verificarSiHayEquipoGanador();
			
			if ( !this.hayEquipoGanador ) {
				
				this.actualizarJugadorQueDebeJugar();			
				this.manoActual = new Mano(this.cantidadJugadores);
				this.manos.add(this.manoActual);
			}
			
		} else {
			
			this.actualizarTurnos();
		}
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.manos.getLast().mostrarUltimaCartaJugadaPor(unJugador) );
	}
	
	public Equipo obtenerEquipoGanador() {
		
		if ( this.equipoGanador == null ) throw new NoHayEquipoGanadorHastaQueLaRondaTermineException();
		return this.equipoGanador;
	}
	
	private void verificarSiHayEquipoGanador() {
		
		int manosGanadasEquipo1 = 0;
		int manosGanadasEquipo2 = 0;
		Jugador ganador;
		
		if ( this.manos.size() >= 2 ) {
			
			for ( Mano unaMano : this.manos ) {
		
				try { 
					ganador = unaMano.obtenerGanador();
					if ( this.equipo1.estaJugador(ganador) ) manosGanadasEquipo1++;
					if ( this.equipo2.estaJugador(ganador) ) manosGanadasEquipo2++;
				} catch(NoHayGanadorHuboEmpateException e) { }
			}
	
			if ( manosGanadasEquipo1 > manosGanadasEquipo2 ) {
				
				this.equipoGanador = this.equipo1;
			
			} else if ( manosGanadasEquipo1 < manosGanadasEquipo2 ) {
				
				this.equipoGanador = this.equipo2;
			
			} else if ( manosGanadasEquipo1 == 1 && manosGanadasEquipo2 == 1 && this.manos.size() == 3 ) {
				
				ganador = this.manos.getFirst().obtenerGanador();
				if ( this.equipo1.estaJugador(ganador) ) this.equipoGanador = this.equipo1;
				if ( this.equipo2.estaJugador(ganador) ) this.equipoGanador = this.equipo2;
				
			} else if ( manosGanadasEquipo1 == 0 && manosGanadasEquipo2 == 0 && this.manos.size() == 3 ) {
				
				if ( this.equipo1.esMano() ) this.equipoGanador = this.equipo1;
				if ( this.equipo2.esMano() ) this.equipoGanador = this.equipo2;
			}
				
			this.hayEquipoGanador = ( this.equipoGanador != null );
		}
	}
	
	private void verificarSiEsteJugadorPuedeJugar(Jugador unJugador) {
		
		if ( this.jugadorQueDebeJugar != unJugador ) throw new NoEsElTurnoDeEsteJugadorException();
	}
	

	private void actualizarTurnos() {
		
		Collections.rotate(this.ordenJugadores, -1);
		this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
	}
	
	private void actualizarJugadorQueDebeJugar() {
		
		try {
			Jugador jugador = this.manoActual.obtenerGanador();
			int posicion = this.ordenJugadores.indexOf(jugador);
			Collections.rotate(ordenJugadores, -posicion);
			this.jugadorQueDebeJugar = this.ordenJugadores.getFirst();
		} catch(NoHayGanadorHuboEmpateException e) {
			this.actualizarTurnos();
		}
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
	public void retruco(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelTruco();
		this.cantoEnProcesoParaElTruco.retruco(jugadorQueCanta);
		
	}

	@Override
	public void valeCuatro(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelTruco();
		this.cantoEnProcesoParaElTruco.valeCuatro(jugadorQueCanta);
		
	}
}