package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.cantos.NoSePuedeCantarTantoDosVecesEnUnaRondaException;
import truco.excepciones.cantos.RespuestaIncorrectaException;


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
		this.mesa.envido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDelEnvido(primerJugador);
		this.mesa.cantarTantoDelEnvido(segundoJugador);
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , segundoJugador);
	}
	
	@Test
	public void testAlInciarseUnProcesoDeFlorElJugadorConMayorTantoEsElGanador(){
		this.mesa.flor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeLaFlor(primerJugador);
		this.mesa.cantarTantoDeLaFlor(segundoJugador);
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnEnvidoNoPuedeVolverseACantarOtroEnvido(){
		this.mesa.envido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDelEnvido(primerJugador);
		this.mesa.cantarTantoDelEnvido(segundoJugador);
		
		this.mesa.envido(primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnaFlorNoPuedeCantarseEnvido(){
		this.mesa.flor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeLaFlor(primerJugador);
		this.mesa.cantarTantoDeLaFlor(segundoJugador);
		
		this.mesa.envido(primerJugador);
	}
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnaFlorNoSePuedeVolverACantarFlor(){
		this.mesa.flor(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDeLaFlor(primerJugador);
		this.mesa.cantarTantoDeLaFlor(segundoJugador);
		
		this.mesa.flor(primerJugador);
	}
	
	@Test
	public void testElPrimerCantoPuedeSerUnRealEnvidoDandoComoGanadorAlQuePoseaMejorTanto(){
		this.mesa.realEnvido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDelEnvido(primerJugador);
		this.mesa.cantarTantoDelEnvido(segundoJugador);
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , segundoJugador);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeResponderEnvidoAUnRealEnvido(){
		this.mesa.realEnvido(segundoJugador);
		
		this.mesa.envido(primerJugador);
	}
	
	@Test
	public void testSePuedeResponderRealEnvidoAUnEnvidoYGanaElJugadorQueTengaMayorTanto(){
		this.mesa.envido(segundoJugador);
		this.mesa.realEnvido(primerJugador);
		this.mesa.cantarQuiero(segundoJugador);
		this.mesa.cantarTantoDelEnvido(primerJugador);
		this.mesa.cantarTantoDelEnvido(segundoJugador);
		
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
