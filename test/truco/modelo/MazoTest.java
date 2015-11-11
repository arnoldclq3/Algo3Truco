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
		
		miMazo.obtenerCarta();
		
		assertEquals(39,miMazo.cantidadCartas());
	}

}
