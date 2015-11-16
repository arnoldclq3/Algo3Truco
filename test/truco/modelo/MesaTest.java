package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.mesa.NoSePuedeCantarTantoDosVecesEnUnaRondaException;
import truco.excepciones.mesa.RespuestaInconrrectaException;

public class MesaTest {

	private Mesa mesa;
	private Jugador primerJugador;
	private Jugador segundoJugador;


	@Before
	public void setup(){
		this.primerJugador = new Jugador("Jugador 1");
		this.segundoJugador = new Jugador("Jugador 2");
		Equipo nosotros = new Equipo();
		nosotros.agregarJugador(this.primerJugador);
		Equipo ellos = new Equipo();
		ellos.agregarJugador(this.segundoJugador);
		
		this.mesa = new Mesa(nosotros,ellos);
		
		primerJugador.tomarCarta( new Carta(5,Palo.ESPADA) );
		primerJugador.tomarCarta( new Carta(3,Palo.ESPADA) );
		primerJugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		
		segundoJugador.tomarCarta( new Carta(1,Palo.BASTO) );
		segundoJugador.tomarCarta( new Carta(6,Palo.BASTO) );
		segundoJugador.tomarCarta( new Carta(7,Palo.BASTO) );
	}
	
	@Test
	public void testLaMesaSeIniciaConDosJugadores() {
		assertTrue(this.mesa.cantidadDeJugadores() == 2);
	}
	
	@Test
	public void testAlInciarseUnProcesoDeEnvidoElJugadorConMayorTantoEsElGanador(){
		this.mesa.cantarEnvido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeEnvido(primerJugador);
		this.mesa.cantarTantoDeEnvido(segundoJugador);
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , segundoJugador);
	}
	
	@Test
	public void testAlInciarseUnProcesoDeFlorElJugadorConMayorTantoEsElGanador(){
		this.mesa.cantarFlor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeFlor(primerJugador);
		this.mesa.cantarTantoDeFlor(segundoJugador);
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnEnvidoNoPuedeVolverseACantarOtroEnvido(){
		this.mesa.cantarEnvido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeEnvido(primerJugador);
		this.mesa.cantarTantoDeEnvido(segundoJugador);
		
		this.mesa.cantarEnvido(primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnaFlorNoPuedeCantarseEnvido(){
		this.mesa.cantarFlor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeFlor(primerJugador);
		this.mesa.cantarTantoDeFlor(segundoJugador);
		
		this.mesa.cantarEnvido(primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnaFlorNoSePuedeVolverACantarFlor(){
		this.mesa.cantarFlor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeFlor(primerJugador);
		this.mesa.cantarTantoDeFlor(segundoJugador);
		
		this.mesa.cantarFlor(primerJugador);
	}
	
	@Test
	public void testElPrimerCantoPuedeSerUnRealEnvidoDandoComoGanadorAlQuePoseaMejorTanto(){
		this.mesa.cantarRealEnvido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeEnvido(primerJugador);
		this.mesa.cantarTantoDeEnvido(segundoJugador);
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , segundoJugador);
	}
	
	@Test (expected = RespuestaInconrrectaException.class)
	public void testNoSePuedeResponderEnvidoAUnRealEnvido(){
		this.mesa.cantarRealEnvido(segundoJugador);
		
		this.mesa.cantarEnvido(primerJugador);
	}
	
	@Test
	public void testSePuedeResponderRealEnvidoAUnEnvidoYGanaElJugadorQueTengaMayorTanto(){
		this.mesa.cantarEnvido(segundoJugador);
		this.mesa.cantarRealEnvido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeEnvido(primerJugador);
		this.mesa.cantarTantoDeEnvido(segundoJugador);
		
		assertEquals(5,this.mesa.mostrarPuntajeEquipoEllos());
	}
	
	@Test
	public void testSeJuegaUnTrucoQuiero() {
		
		this.mesa.cantaTrucoElJugador(primerJugador);
		this.mesa.cantaQuieroElJugador(segundoJugador);
		this.mesa.jugarCarta(primerJugador, new Carta(1, Palo.ESPADA));
		this.mesa.jugarCarta(segundoJugador, new Carta(1, Palo.ORO));
		
		Jugador ganador = this.mesa.enfrentarTodasLasCartasJugadas();
		
		assertEquals(ganador,primerJugador);
	}
	
}
