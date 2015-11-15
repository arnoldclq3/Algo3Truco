package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.mesa.NoSePuedeCantarTantoDosVecesEnUnaRonda;

public class MesaTest {

	private Mesa mesa;
	private Jugador primerJugador;
	private Jugador segundoJugador;


	@Before
	public void setup(){
		this.primerJugador = new Jugador("Jugador 1");
		this.segundoJugador = new Jugador("Jugador 2");
		this.mesa = new Mesa(primerJugador,segundoJugador);
		
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
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRonda.class)
	public void testUnaVezFinalizadoElProcesoDeUnEnvidoNoPuedeVolverseACantarOtroEnvido(){
		this.mesa.cantarEnvido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeEnvido(primerJugador);
		this.mesa.cantarTantoDeEnvido(segundoJugador);
		
		this.mesa.cantarEnvido(primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRonda.class)
	public void testUnaVezFinalizadoElProcesoDeUnaFlorNoPuedeCantarseEnvido(){
		this.mesa.cantarFlor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeFlor(primerJugador);
		this.mesa.cantarTantoDeFlor(segundoJugador);
		
		this.mesa.cantarEnvido(primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRonda.class)
	public void testUnaVezFinalizadoElProcesoDeUnaFlorNoSePuedeVolverACantarFlor(){
		this.mesa.cantarFlor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeFlor(primerJugador);
		this.mesa.cantarTantoDeFlor(segundoJugador);
		
		this.mesa.cantarFlor(primerJugador);
	}
	
}
