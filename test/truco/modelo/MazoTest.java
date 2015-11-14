package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MazoTest {

	private Mazo miMazo;
	
	@Before
	public void setup() {
		
		this.miMazo = new Mazo();
	}
	
	@Test
	public void testElMazoSeCreaCon40Cartas() {
		
		assertEquals(40,miMazo.cantidadCartas());
	}
	
	@Test
	public void testObtenerCartaSacaLaCartaDelMazo() {
		
		miMazo.repartirCarta();
		
		assertEquals(39,miMazo.cantidadCartas());
	}
	
	@Test
	public void testElMazoNoContieneLasCartasOchoYNueve() {
		
		Carta unaCarta = new Carta(9,Palo.ORO);
		Carta otraCarta = new Carta(8,Palo.ESPADA);
		
		assertFalse(miMazo.contieneEstaCarta(unaCarta));
		assertFalse(miMazo.contieneEstaCarta(otraCarta));
	}

}
