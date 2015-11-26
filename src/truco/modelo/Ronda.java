package truco.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import truco.excepciones.mano.NoHayGanadorHuboEmpateException;
import truco.excepciones.ronda.EsteJugadorSeFueAlMazoException;
import truco.excepciones.ronda.NoEsElTurnoDeEsteJugadorException;
import truco.excepciones.ronda.NoHayEquipoGanadorHastaQueLaRondaTermineException;
import truco.excepciones.ronda.NoSePuedeJugarMasCartasRondaTerminadaException;
import truco.excepciones.ronda.SoloLosJugadoresPieDeLaRondaPuedenCantarElEnvidoException;
import truco.excepciones.ronda.SoloSePuedeCantarElTantoEnLaPrimeraManoException;
import truco.excepciones.cantos.RespuestaIncorrectaException;

public class Ronda implements CantosEnvido , CantosFlor , CantosTruco, CantosGenerales{

	private LinkedList<Jugador> jugadoresEnJuego;
	private LinkedList<Jugador> jugadorQueSeFueronAlMazo;
	private Jugador jugadorQueDebeJugar;
	private LinkedList<Jugador> jugadoresQuePuedenCantarTanto;
	
	private LinkedList<Mano> manos;
	private Mano manoActual;
	
	private Equipo equipo1;
	private Equipo equipo2;
	
	private Equipo equipoGanador;
	private boolean hayEquipoGanador;
	
	private CantoEnProcesoParaElTanto cantoEnProcesoParaElTanto;
	private CantosEnProcesoParaElTruco cantoEnProcesoParaElTruco;

	public Ronda(Equipo equipo1, Equipo equipo2, LinkedList<Jugador> jugadoresEnJuego) {
	
		this.jugadoresEnJuego = new LinkedList<Jugador>();
		this.jugadorQueSeFueronAlMazo = new LinkedList<Jugador>();
		this.jugadoresEnJuego.addAll(jugadoresEnJuego);
		this.jugadorQueDebeJugar = this.jugadoresEnJuego.getFirst();
		this.iniciarJugadoresQueDebenCantarEnvido();
		
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		
		this.manos = new LinkedList<Mano>();
		this.manoActual = new Mano(this.jugadoresEnJuego.size());
		this.manos.add(this.manoActual);
		
		this.equipoGanador = null;
		this.hayEquipoGanador = false;
		
		this.cantoEnProcesoParaElTanto = null;
		this.cantoEnProcesoParaElTruco = new CantosEnProcesoParaElTruco();
	}
	
	private void iniciarJugadoresQueDebenCantarEnvido() {
		this.jugadoresQuePuedenCantarTanto = new LinkedList<Jugador>();
		this.jugadoresQuePuedenCantarTanto.add( this.jugadoresEnJuego.get(this.jugadoresEnJuego.size() - 1) );
		this.jugadoresQuePuedenCantarTanto.add( this.jugadoresEnJuego.get(this.jugadoresEnJuego.size() - 2) );
	}

	public List<Carta> devolverCartas() {
		List<Carta> listadoARetornar = new LinkedList<Carta>();
		for (Mano unaMano : this.manos)
			listadoARetornar.addAll(unaMano.devolverCartas() );
		return listadoARetornar;
	}
	
	public ArrayList<Carta> mostrarCartasDelJugador(Jugador unJugador) {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		for (Mano unaMano : this.manos){
			Carta cartaJugada = unaMano.mostrarCartaDelJugador(unJugador);
			if (cartaJugada != null)
				cartas.add( cartaJugada );
		}
		return cartas;
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		if ( this.hayEquipoGanador ) throw new NoSePuedeJugarMasCartasRondaTerminadaException();
		
		this.verificarSiEsteJugadorPuedeJugar(unJugador);
		this.manoActual.jugarCarta(unJugador, unaCarta);
		
		if ( this.manoActual.estaTerminada() ) {
			
			this.verificarSiHayEquipoGanador();
			
			if ( !this.hayEquipoGanador ) {
				
				this.actualizarJugadorQueDebeJugar();			
				this.manoActual = new Mano(this.jugadoresEnJuego.size());
				this.manos.add(this.manoActual);
			}
			else{
				this.equipoGanador.sumarPuntos(this.cantoEnProcesoParaElTruco.puntosParaElGanador() );
			}
			
		} else {
			
			this.actualizarTurnos();
		}
	}
	
	public void jugarCartaTapada(Jugador unJugador, Carta unaCarta) {
		
		if ( this.hayEquipoGanador ) throw new NoSePuedeJugarMasCartasRondaTerminadaException();
		
		this.verificarSiEsteJugadorPuedeJugar(unJugador);
		this.manoActual.jugarCartaTapada(unaCarta);
		
		if ( this.manoActual.estaTerminada() ) {
			
			this.verificarSiHayEquipoGanador();
			
			if ( !this.hayEquipoGanador ) {
				
				this.actualizarJugadorQueDebeJugar();			
				this.manoActual = new Mano(this.jugadoresEnJuego.size());
				this.manos.add(this.manoActual);
			}
			else{
				this.equipoGanador.sumarPuntos(this.cantoEnProcesoParaElTruco.puntosParaElGanador() );
			}
			
		} else {
			
			this.actualizarTurnos();
		}
		
	}
	
	public void irseAlMazo(Jugador unJugador) {
		
		if ( this.hayEquipoGanador ) throw new NoSePuedeJugarMasCartasRondaTerminadaException();
		
		this.verificarSiEsteJugadorPuedeJugar(unJugador);
		
		this.jugadoresEnJuego.remove(unJugador);
		this.jugadorQueSeFueronAlMazo.add(unJugador);
		
		this.verificarSiSigueEnJuegoElEquipoDelJugador(unJugador);
		
		this.jugadorQueDebeJugar = this.jugadoresEnJuego.getFirst();
		this.manoActual.unJugadorSeFueAlMazo();
		
		if ( this.manoActual.estaTerminada() && !this.hayEquipoGanador ) {
			
			this.verificarSiHayEquipoGanador();
			
			if ( !this.hayEquipoGanador ) {
				
				this.actualizarJugadorQueDebeJugar();			
				this.manoActual = new Mano(this.jugadoresEnJuego.size());
				this.manos.add(this.manoActual);
			}
			else{
				this.equipoGanador.sumarPuntos(this.cantoEnProcesoParaElTruco.puntosParaElGanador() );
			}	
		}
		else if (this.hayEquipoGanador){
			this.equipoGanador.sumarPuntos(this.cantoEnProcesoParaElTruco.puntosParaElGanador() );
			if ( this.manos.size() == 1)
				if (this.cantoEnProcesoParaElTanto == null )
					this.equipoGanador.sumarPuntos( 1 );
				/* 	En este lugar podria agregase la condicion de que se puedan ir al mazo 
			 		sin responder un envido por quiero o no quiero */
		}
	}

	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.manos.getLast().mostrarUltimaCartaJugadaPor(unJugador) );
	}
	
	public Equipo obtenerEquipoGanador() {
		
		if ( !this.hayEquipoGanador ) throw new NoHayEquipoGanadorHastaQueLaRondaTermineException();
		return this.equipoGanador;
	}
	
	public boolean estaTerminada(){
		return this.hayEquipoGanador;
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
		
		if ( this.jugadorQueSeFueronAlMazo.contains(unJugador) ) throw new EsteJugadorSeFueAlMazoException();
		if ( this.jugadorQueDebeJugar != unJugador ) throw new NoEsElTurnoDeEsteJugadorException();
	}
	

	private void actualizarTurnos() {
		
		Collections.rotate(this.jugadoresEnJuego, -1);
		this.jugadorQueDebeJugar = this.jugadoresEnJuego.getFirst();
	}
	
	private void actualizarJugadorQueDebeJugar() {
		
		try {
			Jugador jugador = this.manoActual.obtenerGanador();
			int posicion = this.jugadoresEnJuego.indexOf(jugador);
			Collections.rotate(jugadoresEnJuego, -posicion);
			this.jugadorQueDebeJugar = this.jugadoresEnJuego.getFirst();
		} catch(NoHayGanadorHuboEmpateException e) {
			this.actualizarTurnos();
		}
	}
	
	private void verificarSiSigueEnJuegoElEquipoDelJugador(Jugador unJugador) {
		
		int cantidadJugadoresDelEquipo1QueSeFueronAlMazo = 0;
		int cantidadJugadoresDelEquipo2QueSeFueronAlMazo = 0;
		
		for ( Jugador otroJugador : this.jugadorQueSeFueronAlMazo ) {
			
			if ( this.equipo1.estaJugador(otroJugador) ) {
				cantidadJugadoresDelEquipo1QueSeFueronAlMazo++;
			}
			
			if ( this.equipo2.estaJugador(otroJugador) ) {
				cantidadJugadoresDelEquipo2QueSeFueronAlMazo++;
			}
		}
		
		if ( cantidadJugadoresDelEquipo1QueSeFueronAlMazo == this.equipo1.cantidadJugadores() ) {
			this.equipoGanador = this.equipo2;
		}
		
		if ( cantidadJugadoresDelEquipo2QueSeFueronAlMazo == this.equipo2.cantidadJugadores() ) {
			this.equipoGanador = this.equipo1;
		}
		this.hayEquipoGanador = ( this.equipoGanador != null );
	}

	/*************************************************
	 ** 	   		 Cantos Generales			    **
	 *************************************************/
	
	@Override
	public void quiero(Jugador jugadorQueCanta) {
		if (this.cantoEnProcesoParaElTanto != null && !this.cantoEnProcesoParaElTanto.terminoElProcesoDeCanto(this.jugadoresEnJuego.size()) )
			this.cantoEnProcesoParaElTanto.quiero(jugadorQueCanta);
		else
			if (this.cantoEnProcesoParaElTruco != null)
				this.cantoEnProcesoParaElTruco.quiero(jugadorQueCanta);
			else 
				throw new RespuestaIncorrectaException();
	}
	
	@Override
	public void noQuiero(Jugador jugadorQueCanta) {
		if (this.cantoEnProcesoParaElTanto != null && !this.cantoEnProcesoParaElTanto.terminoElProcesoDeCanto(this.jugadoresEnJuego.size()) ){
			// El "no quiero" es para un tanto (envido/flor)
			this.cantoEnProcesoParaElTanto.noQuiero(jugadorQueCanta);
			this.sumarPuntosPorTantoFinalizado();
		}
		else
			if (this.cantoEnProcesoParaElTruco != null){
				// El "no quiero" es para un truco
				this.cantoEnProcesoParaElTruco.noQuiero(jugadorQueCanta);
				this.sumarPuntosPorTrucoFinalizado();
			}
			else 
				throw new RespuestaIncorrectaException();
	}
	
	
	private void sumarPuntosPorTrucoFinalizado() {
		Jugador jugadorGanadorDelTruco = this.cantoEnProcesoParaElTruco.jugadorGanador();
		int puntosGanados = this.cantoEnProcesoParaElTruco.puntosParaElGanador();
		this.hayEquipoGanador = true;
		
		if ( this.equipo1.estaJugador(jugadorGanadorDelTruco) ) {
			this.equipoGanador = this.equipo1;
		}
		if ( this.equipo2.estaJugador(jugadorGanadorDelTruco) ) {
			this.equipoGanador = this.equipo2;
		}
		
		this.sumarPuntos(jugadorGanadorDelTruco, puntosGanados);
	}

	private void sumarPuntosPorTantoFinalizado() {
		Jugador jugadorGanadorDelTanto = this.cantoEnProcesoParaElTanto.jugadorGanador();
		int puntosGanados = this.cantoEnProcesoParaElTanto.puntosParaElGanador();
		
		this.sumarPuntos(jugadorGanadorDelTanto, puntosGanados);
		this.sumarPuntosParaPerdedorPorTanto(jugadorGanadorDelTanto);
	}

	private void sumarPuntos(Jugador unJugador,int puntosGanados){
		if ( this.equipo1.estaJugador(unJugador) )
			this.equipo1.sumarPuntosAJugador(unJugador, puntosGanados);
		else
			this.equipo2.sumarPuntosAJugador(unJugador, puntosGanados);
		
		if ( equipo1.obtenerPuntosFaltantesParaGanar() == 0 ) {
			this.equipoGanador = this.equipo1;
			this.hayEquipoGanador = true;
		}
		if ( equipo2.obtenerPuntosFaltantesParaGanar() == 0 ) {
			this.equipoGanador = this.equipo2;
			this.hayEquipoGanador = true;
		}
	}
		
	private void sumarPuntosParaPerdedorPorTanto(Jugador jugadorGanadorDelTanto) {
		if ( this.equipo1.estaJugador(jugadorGanadorDelTanto) )
			this.equipo2.sumarPuntos(this.cantoEnProcesoParaElTanto.puntosParaElPerdedor() );
		else
			this.equipo1.sumarPuntos(this.cantoEnProcesoParaElTanto.puntosParaElPerdedor() );	
	}
	
	private void controlarSiElCantoDelTantoFinalizo(){
		boolean cantoFinalizadoDeFlor = false;
		if ( this.cantoEnProcesoParaElTanto.seCantoFlor() )
			cantoFinalizadoDeFlor = this.controlarSiElCantoDeFlorFinalizo();
		
		if (this.cantoEnProcesoParaElTanto.terminoElProcesoDeCanto(this.jugadoresEnJuego.size()) || cantoFinalizadoDeFlor)
			this.sumarPuntosPorTantoFinalizado();
	}
	

	private boolean controlarSiElCantoDeFlorFinalizo() {
		int jugadoresConFlor = 0;
		for (Jugador jugador : this.jugadoresEnJuego )
			if ( jugador.tieneFlor() )
				jugadoresConFlor++;
		return (this.cantoEnProcesoParaElTanto.terminoElProcesoDeCanto( jugadoresConFlor ) );
	}

	/*************************************************
	 ** 	    Cantos Generales del Tanto	  		
	 *************************************************/
	
	private void iniciarProcesoDelEnvido(Jugador jugadorQueCanta){
		boolean procesoIniciado = this.iniciarProcesoDelTanto();
		
		if (procesoIniciado){
			if ( this.manos.size() != 1)
				throw new SoloSePuedeCantarElTantoEnLaPrimeraManoException();
			if ( !this.jugadoresQuePuedenCantarTanto.contains(jugadorQueCanta) )
				throw new SoloLosJugadoresPieDeLaRondaPuedenCantarElEnvidoException();
		}	
		
	}
	
	private void iniciarProcesoDeLaFlor(Jugador jugadorQueCanta){
		this.iniciarProcesoDelTanto();
	}
	
	private boolean iniciarProcesoDelTanto(){
		if (this.cantoEnProcesoParaElTanto == null){
			this.cantoEnProcesoParaElTanto = new CantoEnProcesoParaElTanto(this.equipo1,this.equipo2);
			return true;
		}
		return false;
	}
	
	// Se esta usando para que Mesa pueda realizar Tests
	public Jugador jugadorGanadorDelTanto(){
		if (this.cantoEnProcesoParaElTanto == null)
			return null;
		return this.cantoEnProcesoParaElTanto.jugadorGanador();	
	}
	
	/*************************************************
	 ** 			   Cantos Envido				**
	 *************************************************/

	@Override
	public void envido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelEnvido(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.envido(jugadorQueCanta);
	}

	@Override
	public void realEnvido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelEnvido(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.realEnvido(jugadorQueCanta);
	}

	@Override
	public void faltaEnvido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelEnvido(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.faltaEnvido(jugadorQueCanta);
		
	}

	@Override
	public void cantarTantoDelEnvido(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelEnvido(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.cantarTantoDelEnvido(jugadorQueCanta);
		this.controlarSiElCantoDelTantoFinalizo();
	}
	
	@Override
	public void sonBuenas(Jugador jugadorQueCanta) {
		this.iniciarProcesoDelTanto();
		this.cantoEnProcesoParaElTanto.sonBuenas(jugadorQueCanta);
		this.controlarSiElCantoDelTantoFinalizo();
	}

	/*************************************************
	 ** 			   Cantos Flor   				**
	 *************************************************/
	
	@Override
	public void flor(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeLaFlor(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.flor(jugadorQueCanta);		
	}

	@Override
	public void contraFlor(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeLaFlor(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.contraFlor(jugadorQueCanta);	
	}

	@Override
	public void contraFlorAResto(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeLaFlor(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.contraFlorAResto(jugadorQueCanta);	
	}

	@Override
	public void cantarTantoDeLaFlor(Jugador jugadorQueCanta) {
		this.iniciarProcesoDeLaFlor(jugadorQueCanta);
		this.cantoEnProcesoParaElTanto.cantarTantoDeLaFlor(jugadorQueCanta);
		this.controlarSiElCantoDelTantoFinalizo();
	}
	
	
	/*************************************************
	 ** 			   Cantos Truco   				**
	 *************************************************/
	
	@Override
	public void truco(Jugador jugadorQueCanta) {
		this.cantoEnProcesoParaElTruco.truco(jugadorQueCanta);
		
	}

	@Override
	public void retruco(Jugador jugadorQueCanta) {
		this.cantoEnProcesoParaElTruco.retruco(jugadorQueCanta);
		
	}

	@Override
	public void valeCuatro(Jugador jugadorQueCanta) {
		this.cantoEnProcesoParaElTruco.valeCuatro(jugadorQueCanta);
		
	}

}