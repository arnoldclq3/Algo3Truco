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
	
	@Test
	public void testMostrarJugadorQueGanoLaManoConUnaCartaMasAltaQueElOtroJugador() {
		Jugador jugador1 = new Jugador();
		Carta cartaJugador1 = new Carta(1,Palo.ESPADA);
		jugador1.tomarCarta(cartaJugador1);
		
		Jugador jugador2 = new Jugador();
		Carta cartaJugador2 = new Carta(1,Palo.ESPADA);
		jugador2.tomarCarta(cartaJugador2);
		
		this.mimano.jugarCarta(jugador1, cartaJugador1);
		this.mimano.jugarCarta(jugador2, cartaJugador2);
		
		assertEquals(jugador1,this.mimano.enfrentarTodasLasCartas());
	}
	
	@Test
	public void testMostrarJugadorQueGanoLaManoConUnaCartaDelMismoValorQueElOtroJugadorPeroEsMano() {
		Jugador jugador1 = new Jugador();
		Carta cartaJugador1 = new Carta(1,Palo.ESPADA);
		jugador1.tomarCarta(cartaJugador1);
		
		Jugador jugador2 = new Jugador();
		Carta cartaJugador2 = new Carta(1,Palo.ESPADA);
		jugador2.tomarCarta(cartaJugador2);
		
		this.mimano.jugarCarta(jugador1, cartaJugador1);
		this.mimano.jugarCarta(jugador2, cartaJugador2);
		
		assertEquals(jugador1,this.mimano.enfrentarTodasLasCartas());
	}

}
