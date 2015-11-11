package truco.modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JugadorTest {
	
	private Jugador jugador;

	@Before
	public void setup(){
		this.jugador = new Jugador();
	}

	@Test
	public void testPuntajeEnvidoDevuelveCorretamenteElEnvidoTeniendoDosCartasDelMismoPalo() {
		// Se le pasa el 1 de Espada, el 7 de Espada y el 3 de Oro. Envido = 28.
		int envidoEsperado = 28;
		this.jugador.nuevaCarta( new Carta(1,"espada") );
		this.jugador.nuevaCarta( new Carta(7,"espada") );
		this.jugador.nuevaCarta( new Carta(3,"oro") );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveCeroCuandoNoTieneCartasDelMismoPalo() {
		// Se le pasa el 1 de Espada, el 2 de copa y el 3 de Oro. Envido = 0.
		int envidoEsperado = 0;
		this.jugador.nuevaCarta( new Carta(1,"espada") );
		this.jugador.nuevaCarta( new Carta(2,"copa") );
		this.jugador.nuevaCarta( new Carta(3,"oro") );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveElValorCorrectoCuandoTieneDosFiguras() {
		// Se le pasa el 10 de Basto , el 12 de Basto y el 3 de Oro. Envido = 20.
		int envidoEsperado = 20;
		this.jugador.nuevaCarta( new Carta(10,"basto") );
		this.jugador.nuevaCarta( new Carta(12,"basto") );
		this.jugador.nuevaCarta( new Carta(3,"oro") );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveElValorCorrectoCuandoTieneSoloUnaFigura() {
		// Se le pasa el 10 de Basto , el 4 de Basto y el 3 de Oro. Envido = 24.
		int envidoEsperado = 24;
		this.jugador.nuevaCarta( new Carta(10,"basto") );
		this.jugador.nuevaCarta( new Carta(4,"basto") );
		this.jugador.nuevaCarta( new Carta(3,"oro") );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}
	
	@Test
	public void testPuntajeEnvidoDevuelveElMaximoEnvidoPosibleCuandoTieneTresCartasDelMismoPalo() {
		// Se le pasa el 7 de Copa , el 12 de Copa y el 6 de Copa. Envido = 33.
		int envidoEsperado = 33;
		this.jugador.nuevaCarta( new Carta(7,"copa") );
		this.jugador.nuevaCarta( new Carta(12,"copa") );
		this.jugador.nuevaCarta( new Carta(6,"copa") );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(envidoEsperado,resultadoEnvido);
	}

	
	@Test
	public void testPuntajeFlorDevuelveCorretamenteElValorTeniendoTresCartasDelMismoPalo() {
		// Se le pasa el 1 de Espada, el 7 de Espada y el 6 de Esapda. Envido = 34.
		int valorFlorEsperado = 34;
		this.jugador.nuevaCarta( new Carta(1,"espada") );
		this.jugador.nuevaCarta( new Carta(7,"espada") );
		this.jugador.nuevaCarta( new Carta(6,"espada") );
		
		int valorDeLaFlor = this.jugador.puntajeFlor();
		
		assertEquals(valorFlorEsperado,valorDeLaFlor);
	}
	
	@Test
	public void testPuntajeFlorDevuelveCorretamenteElValorTeniendoTresFigurasDelMismoPalo() {
		// Se le pasa el 10 de Copa, el 11 de Copa y el 12 de Copa. Envido = 20.
		int valorFlorEsperado = 20;
		this.jugador.nuevaCarta( new Carta(10,"copa") );
		this.jugador.nuevaCarta( new Carta(11,"copa") );
		this.jugador.nuevaCarta( new Carta(12,"copa") );
		
		int valorDeLaFlor = this.jugador.puntajeFlor();
		
		assertEquals(valorFlorEsperado,valorDeLaFlor);
	}
	
	@Test (expected = RuntimeException.class)
	public void testUnJugadorSinFlorDevuelveUnaExcepcionCuandoSeLePideSuPuntajeFlor() {
		// Se le pasa el 1 de Espada, el 7 de Esapda y el 6 de Oro. NO TIENE FLOR
		this.jugador.nuevaCarta( new Carta(1,"espada") );
		this.jugador.nuevaCarta( new Carta(7,"espada") );
		this.jugador.nuevaCarta( new Carta(6,"oro") );
		
		this.jugador.puntajeFlor();
	}
	
	@Test
	public void testUnJugadorTiraLaCartaQueYNoLaTieneMasEnLaMano() {

		this.jugador.nuevaCarta( new Carta(10,"copa") );
		this.jugador.nuevaCarta( new Carta(11,"copa") );
		this.jugador.nuevaCarta( new Carta(1,"copa") );
		
		List<Carta> cartas = this.jugador.obtenerCartasEnMano();
		this.jugador.tirarCarta(cartas.get(0));
		
		cartas = this.jugador.obtenerCartasEnMano();
		
		assertFalse(cartas.contains(new Carta(10,"copa")));
	}

}
