package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManoTest {

	private Mano manoDeDos;
	private Mano manoDeCuatro;
	private Mano manoDeSeis;
	
	@Before
	public void setup() {

		this.manoDeDos = new Mano(2);
		this.manoDeCuatro = new Mano(4);
		this.manoDeSeis = new Mano(6);
	}
	
	@Test
	public void testMostrarLaUltimaCartaJugadaDeUnJugador() {
		
		Jugador unJugador = new Jugador();
		Carta unaCarta = new Carta(1,Palo.ESPADA);
		unJugador.tomarCarta(unaCarta);
		
		this.manoDeDos.jugarCarta(unJugador, unaCarta);
		
		assertEquals(unaCarta,this.manoDeDos.mostrarUltimaCartaJugadaPor(unJugador));
	}
	
	@Test
	public void testMostrarJugadorQueGanoLaManoDeDosJugadores() {
		
		Jugador jugador1 = new Jugador();
		Carta cartaJugador1 = new Carta(1,Palo.ESPADA);
		Jugador jugador2 = new Jugador();
		Carta cartaJugador2 = new Carta(7,Palo.ESPADA);
		
		this.manoDeDos.jugarCarta(jugador1, cartaJugador1);
		this.manoDeDos.jugarCarta(jugador2, cartaJugador2);
		
		assertEquals(jugador1,this.manoDeDos.obtenerGanador());
	}
	
	@Test
	public void testMostrarJugadorQueGanoLaManoDeCuatroJugadores() {
		
		Jugador jugador1 = new Jugador();
		Carta cartaJugador1 = new Carta(1,Palo.BASTO);
		Jugador jugador2 = new Jugador();
		Carta cartaJugador2 = new Carta(1,Palo.ESPADA);
		Jugador jugador3 = new Jugador();
		Carta cartaJugador3 = new Carta(7,Palo.ESPADA);
		Jugador jugador4 = new Jugador();
		Carta cartaJugador4 = new Carta(7,Palo.ORO);

		
		this.manoDeCuatro.jugarCarta(jugador1, cartaJugador1);
		this.manoDeCuatro.jugarCarta(jugador2, cartaJugador2);
		this.manoDeCuatro.jugarCarta(jugador3, cartaJugador3);
		this.manoDeCuatro.jugarCarta(jugador4, cartaJugador4);
		
		
		assertEquals(jugador2,this.manoDeCuatro.obtenerGanador());
	}
	
	@Test
	public void testMostrarJugadorQueGanoLaManoDeSeisJugadores() {
		
		Jugador jugador1 = new Jugador();
		Carta cartaJugador1 = new Carta(1,Palo.BASTO);
		Jugador jugador2 = new Jugador();
		Carta cartaJugador2 = new Carta(7,Palo.ESPADA);
		Jugador jugador3 = new Jugador();
		Carta cartaJugador3 = new Carta(1,Palo.ESPADA);
		Jugador jugador4 = new Jugador();
		Carta cartaJugador4 = new Carta(7,Palo.ORO);
		Jugador jugador5 = new Jugador();
		Carta cartaJugador5 = new Carta(3,Palo.ORO);
		Jugador jugador6 = new Jugador();
		Carta cartaJugador6 = new Carta(2,Palo.ORO);
		
		this.manoDeSeis.jugarCarta(jugador1, cartaJugador1);
		this.manoDeSeis.jugarCarta(jugador2, cartaJugador2);
		this.manoDeSeis.jugarCarta(jugador3, cartaJugador3);
		this.manoDeSeis.jugarCarta(jugador4, cartaJugador4);
		this.manoDeSeis.jugarCarta(jugador5, cartaJugador5);
		this.manoDeSeis.jugarCarta(jugador6, cartaJugador6);
		
		
		assertEquals(jugador3,this.manoDeSeis.obtenerGanador());
	}
	
	/*
	 * TODAVIA FALTA CONSIDERAR CASO EMPATE
	 * 
	@Test
	public void testMostrarJugadorQueGanoLaManoConUnaCartaDelMismoValorQueElOtroJugadorPeroEsMano() {
		Jugador jugador1 = new Jugador();
		Carta cartaJugador1 = new Carta(7,Palo.COPA);
		jugador1.tomarCarta(cartaJugador1);
		
		Jugador jugador2 = new Jugador("jugador2",true,false);
		Carta cartaJugador2 = new Carta(7,Palo.BASTO);
		jugador2.tomarCarta(cartaJugador2);
		
		this.mimano.jugarCarta(jugador1, cartaJugador1);
		this.mimano.jugarCarta(jugador2, cartaJugador2);
		
		assertEquals(jugador2,this.mimano.enfrentarTodasLasCartas());
	}
	*/

}
