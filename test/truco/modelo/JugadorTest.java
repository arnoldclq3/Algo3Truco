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
	public void testDevuelveCorretamenteElEnvidoTeniendoDosCartasDelMismoPalo() {
		// Se le pasa el 1 de Espada, el 7 de Esapda y el 3 de Oro. Envido = 28.
		int envidoEsperado = 28;
		this.jugador.nuevaCarta( new Carta(1,"espada") );
		this.jugador.nuevaCarta( new Carta(7,"espada") );
		this.jugador.nuevaCarta( new Carta(3,"oro") );
		
		int resultadoEnvido = this.jugador.puntajeEnvido();
		
		assertEquals(resultadoEnvido,envidoEsperado);
	}

}
