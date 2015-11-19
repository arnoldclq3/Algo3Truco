package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.cantos.NoSePuedeCantarTantoDosVecesEnUnaRondaException;
import truco.excepciones.cantos.RespuestaIncorrectaException;


public class MesaTest {

	private Mesa mesa;
	
	private Jugador jugadorNosotros;
	private Jugador jugadorEllos;
	
	private Equipo equipoNosotros;
	private Equipo equipoEllos;


	@Before
	public void setup(){
		this.jugadorNosotros = new Jugador("Jugador 1");
		this.jugadorEllos = new Jugador("Jugador 2");
		this.equipoNosotros = new Equipo();
		equipoNosotros.agregarJugador(this.jugadorNosotros);
		this.equipoEllos = new Equipo();
		equipoEllos.agregarJugador(this.jugadorEllos);
		
		this.mesa = new Mesa(equipoNosotros,equipoEllos);
	}
	
	
	@Test
	public void testLaMesaSeIniciaConDosJugadores() {
		assertTrue(this.mesa.cantidadDeJugadores() == 2);
	}
	
	@Test
	public void testAlInciarseUnProcesoDeEnvidoElJugadorConMayorTantoEsElGanador(){
		this.mesa.envido(jugadorNosotros);
		this.mesa.quiero(jugadorEllos);
		this.mesa.cantarTantoDelEnvido(jugadorNosotros);
		this.mesa.cantarTantoDelEnvido(jugadorEllos);
		
		Jugador jugadorConMayorTanto = this.jugadorEllos;
		if (this.jugadorNosotros.puntajeEnvido() >= this.jugadorEllos.puntajeEnvido() )
			jugadorConMayorTanto = this.jugadorNosotros;
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , jugadorConMayorTanto);
	}
	
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnEnvidoNoPuedeVolverseACantarOtroEnvido(){
		this.mesa.envido(jugadorNosotros);
		this.mesa.quiero(jugadorEllos);
		this.mesa.cantarTantoDelEnvido(jugadorNosotros);
		this.mesa.cantarTantoDelEnvido(jugadorEllos);
		
		this.mesa.envido(jugadorNosotros);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeResponderEnvidoAUnRealEnvido(){
		this.mesa.realEnvido(jugadorEllos);
		
		this.mesa.envido(jugadorNosotros);
	}
	
	@Test
	public void testSePuedeResponderRealEnvidoAUnEnvidoYGanaElJugadorQueTengaMayorTanto(){
		this.mesa.envido(jugadorEllos);
		this.mesa.realEnvido(jugadorNosotros);
		this.mesa.quiero(jugadorEllos);
		this.mesa.cantarTantoDelEnvido(jugadorNosotros);
		this.mesa.cantarTantoDelEnvido(jugadorEllos);
		
		Jugador jugadorConMayorTanto = this.jugadorNosotros;
		if (this.jugadorEllos.puntajeEnvido() > this.jugadorNosotros.puntajeEnvido() )
			jugadorConMayorTanto = this.jugadorEllos;
		Equipo equipoGanador = this.equipoEllos;
		if (this.equipoNosotros.estaJugador(jugadorConMayorTanto) )
			equipoGanador = this.equipoNosotros;
		
		assertEquals(5,equipoGanador.obtenerCantidadDePuntos() );
	}

}
