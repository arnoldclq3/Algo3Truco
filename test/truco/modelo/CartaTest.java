package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CartaTest {
	
	private Carta miCarta;

	@Before
	public void setup() {
		
		this.miCarta = new Carta(1,Palo.ESPADA);
	}
	
	
	@Test
	public void testCartaDelMismoPaloDevuelveTrue() {
		
		Carta otraCarta = new Carta(7,Palo.ESPADA);
		
		assertTrue(miCarta.mismoPalo(otraCarta));
	}
	
	@Test
	public void testCartasDelMismoValorYPaloSonIguales() {
		
		Carta otraCarta = new Carta(1,Palo.ESPADA);
		
		assertTrue(miCarta.equals(otraCarta));
	}
	
	@Test
	public void testElAnchoDeEspadaEsLaCartaMasAlta(){
		
		Carta primeraCarta = new Carta(1,Palo.ESPADA);
		Carta segundaCarta = new Carta(1,Palo.BASTO);
		
		assertEquals(miCarta.compararCon(primeraCarta),0);
		assertEquals(miCarta.compararCon(segundaCarta),1);
	}
	
	@Test
	public void testElAnchoDeCopaEsMenorQueElAnchoDeEspada(){
		
		Carta primeraCarta = new Carta(1,Palo.COPA);
		
		assertEquals(primeraCarta.compararCon(miCarta),-1);
	}

}
