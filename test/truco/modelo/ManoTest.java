package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManoTest {

	private Mano mimano;
	
	@Before
	public void setup() {
		
		this.mimano = new Mano();
	}
	
	@Test
	public void testMostrarLaUltimaCartaJugadaDeUnJugador() {
		
		Jugador unJugador = new Jugador();
		Carta unaCarta = new Carta(1,Palo.ESPADA);
		unJugador.tomarCarta(unaCarta);
		
		this.mimano.jugarCarta(unJugador, unaCarta);
		
		assertEquals(unaCarta,this.mimano.mostrarUltimaCartaJugadaPor(unJugador));
	}

}
