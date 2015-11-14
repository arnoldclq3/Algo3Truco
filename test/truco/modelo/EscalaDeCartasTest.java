package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EscalaDeCartasTest {

	private EscalaDeCartas miEscala;
	
	@Before
	public void setup() {
		
		this.miEscala = new EscalaDeCartas();
	}
	
	@Test
	public void testUnoEspadaGanaAUnoBasto() {
		
		Carta carta1 = new Carta(1,Palo.ESPADA);
		Carta carta2 = new Carta(1,Palo.BASTO);
		
		int resultado = miEscala.enfrentarCartas(carta1, carta2);
		
		assertEquals(1,resultado);
	}
		
	@Test
	public void testTresCopaEmpataTresBasto() {
		
		Carta carta1 = new Carta(3,Palo.COPA);
		Carta carta2 = new Carta(3,Palo.BASTO);
		
		int resultado = miEscala.enfrentarCartas(carta2, carta1);
		
		assertEquals(0,resultado);
	}
}
