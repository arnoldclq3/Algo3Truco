package truco.modelo;

import static org.junit.Assert.*;

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
		// Se le pasa el 1 de Espada, el 7 de Esapda y el 3 de Oro. Envido = 28.
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


}
