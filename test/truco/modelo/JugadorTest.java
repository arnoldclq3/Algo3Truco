package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.jugador.CartaEnManoInexistenteException;
import truco.excepciones.jugador.ElJugadorNoTieneFlorException;
import truco.excepciones.jugador.JugadorNoPuedeTenerMasDeTresCartasEnManoException;

public class JugadorTest {
	
	private Jugador jugador;

	@Before
	public void setup(){
		this.jugador = new Jugador("Jugador 1");
	}

	@Test
	public void testPuntajeEnvidoDevuelveCorretamenteElEnvidoTeniendoDosCartasDelMismoPalo() {
		int envidoEsperado = 28;
		this.jugador.tomarCarta( new Carta(1,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(3,Palo.ORO) );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveCeroCuandoNoTieneCartasDelMismoPalo() {
		int envidoEsperado = 0;
		this.jugador.tomarCarta( new Carta(1,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(2,Palo.COPA) );
		this.jugador.tomarCarta( new Carta(3,Palo.ORO) );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveElValorCorrectoCuandoTieneDosFiguras() {
		// Se le pasa el 10 de Basto , el 12 de Basto y el 3 de Oro. Envido = 20.
		int envidoEsperado = 20;
		this.jugador.tomarCarta( new Carta(10,Palo.BASTO) );
		this.jugador.tomarCarta( new Carta(12,Palo.BASTO) );
		this.jugador.tomarCarta( new Carta(3,Palo.ORO) );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveElValorCorrectoCuandoTieneSoloUnaFigura() {
		// Se le pasa el 10 de Basto , el 4 de Basto y el 3 de Oro. Envido = 24.
		int envidoEsperado = 24;
		this.jugador.tomarCarta( new Carta(10,Palo.BASTO) );
		this.jugador.tomarCarta( new Carta(4,Palo.BASTO) );
		this.jugador.tomarCarta( new Carta(3,Palo.ORO) );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveElMaximoEnvidoPosibleCuandoTieneTresCartasDelMismoPalo() {
		// Se le pasa el 7 de Copa , el 12 de Copa y el 6 de Copa. Envido = 33.
		int envidoEsperado = 33;
		this.jugador.tomarCarta( new Carta(7,Palo.COPA) );
		this.jugador.tomarCarta( new Carta(12,Palo.COPA) );
		this.jugador.tomarCarta( new Carta(6,Palo.COPA) );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}

	
	@Test
	public void testPuntajeFlorDevuelveCorretamenteElValorTeniendoTresCartasDelMismoPalo() {
		// Se le pasa el 1 de Espada, el 7 de Espada y el 6 de Esapda. Envido = 34.
		int valorFlorEsperado = 34;
		this.jugador.tomarCarta( new Carta(1,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(6,Palo.ESPADA) );
		
		int valorDeLaFlor = this.jugador.puntajeFlor();
		
		assertEquals(valorFlorEsperado,valorDeLaFlor);
	}
	
	@Test
	public void testPuntajeFlorDevuelveCorretamenteElValorTeniendoTresFigurasDelMismoPalo() {
		// Se le pasa el 10 de Copa, el 11 de Copa y el 12 de Copa. Envido = 20.
		int valorFlorEsperado = 20;
		this.jugador.tomarCarta( new Carta(10,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(11,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(12,Palo.ESPADA) );
		
		int valorDeLaFlor = this.jugador.puntajeFlor();
		
		assertEquals(valorFlorEsperado,valorDeLaFlor);
	}
	
	@Test (expected = ElJugadorNoTieneFlorException.class)
	public void testUnJugadorSinFlorDevuelveUnaExcepcionCuandoSeLePideSuPuntajeFlor() {
		// Se le pasa el 1 de Espada, el 7 de Esapda y el 6 de Oro. NO TIENE FLOR
		this.jugador.tomarCarta( new Carta(1,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(6,Palo.ORO) );
		
		this.jugador.puntajeFlor();
	}
	
	@Test (expected = CartaEnManoInexistenteException.class)
	public void testUnJugadorNoPuedeTirarDosVecesLaMismaCarta() {

		this.jugador.tomarCarta( new Carta(10,Palo.COPA) );
		this.jugador.tomarCarta( new Carta(11,Palo.COPA) );
		this.jugador.tomarCarta( new Carta(1,Palo.COPA) );
		
		this.jugador.tirarCarta( new Carta(10,Palo.COPA));
		this.jugador.tirarCarta( new Carta(10,Palo.COPA));
	}
	
	@Test (expected = CartaEnManoInexistenteException.class)
	public void testUnJugadorQuiereTiraUnaCartaQueNoTieneLanzaExcepcion() {

		this.jugador.tomarCarta( new Carta(10,Palo.COPA) );
		this.jugador.tomarCarta( new Carta(11,Palo.COPA) );
		this.jugador.tomarCarta( new Carta(1,Palo.COPA) );
		
		this.jugador.tirarCarta( new Carta(1,Palo.ORO));
	}
	
	@Test (expected = JugadorNoPuedeTenerMasDeTresCartasEnManoException.class)
	public void testJugadorQuiereAgarrarMasDeTresCartasYLanzaExcepcion() {
		
		this.jugador.tomarCarta( new Carta(1,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(6,Palo.ORO) );
		this.jugador.tomarCarta( new Carta(12,Palo.ORO) );
	}
	
	@Test
	public void testUnJugadorDebePoderDevolverTodasSusCartasQuedandoseConCeroCartasEnLaMano() {
		
		this.jugador.tomarCarta( new Carta(1,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(6,Palo.ORO) );
		
		this.jugador.devolverCartas();
		
		assertEquals(this.jugador.cantidadDeCartas(),0);
	}
	
	@Test
	public void testUnJugadorDebePoderTirarUnaCartasQuedandoseConDosCartasEnLaMano() {
		
		this.jugador.tomarCarta( new Carta(1,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		this.jugador.tomarCarta( new Carta(6,Palo.ORO) );
		
		this.jugador.tirarCarta( new Carta(1,Palo.ESPADA) );
		
		assertEquals(this.jugador.cantidadDeCartas(),2);
	}
	
	@Test
	public void testJugadorDevuelveNombreCorrectamente() {
		
		assertEquals(this.jugador.getNombre(),"Jugador 1");
	}
	
	@Test
	public void testSiUnJugadorEsIgualAOtroDevuelveTrue() {
		
		Jugador otroJugador = new Jugador("Jugador 1");
		assertTrue(this.jugador.equals(otroJugador));
	}
	
	@Test
	public void testSiUnJugadorEsDistintoAOtroDevuelveFalse() {
		
		Jugador otroJugador = new Jugador("Jugador 2");
		assertFalse(this.jugador.equals(otroJugador));
	}

}
