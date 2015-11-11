package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CartaTest {
	
	private Carta miCarta;

	@Before
	public void setup() {
		
		this.miCarta = new Carta(1,"espada");
	}
	
	@Test
	public void testCartaDelMismoPaloDevuelveTrue() {
		
		Carta otraCarta = new Carta(7,"espada");
		
		assertTrue(miCarta.mismoPalo(otraCarta));
	}
	
	@Test
	public void testCartasDelMismoValorYPaloSonIguales() {
		
		Carta otraCarta = new Carta(1,"espada");
		
		assertTrue(miCarta.equals(otraCarta));
	}

}
