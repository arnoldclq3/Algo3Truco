package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MesaTest {

	private Mesa mesa;
	private Jugador primerJugador;
	private Jugador segundoJugador;


	@Before
	public void setup(){
		this.primerJugador = new Jugador();
		this.segundoJugador = new Jugador();
		this.mesa = new Mesa(primerJugador,segundoJugador);
		
		primerJugador.tomarCarta( new Carta(5,Palo.ESPADA) );
		primerJugador.tomarCarta( new Carta(3,Palo.ESPADA) );
		primerJugador.tomarCarta( new Carta(7,Palo.ESPADA) );
		
		segundoJugador.tomarCarta( new Carta(1,Palo.BASTO) );
		segundoJugador.tomarCarta( new Carta(6,Palo.BASTO) );
		segundoJugador.tomarCarta( new Carta(7,Palo.BASTO) );
	}
	
	@Test
	public void testLaMesaSeIniciaConDosJugadores() {
		assertTrue(this.mesa.cantidadDeJugadores() == 2);
	}
	
	@Test
	public void testElJugadorConMayorEnvidoEsDevueltoComoGanador(){
		Jugador jugadorGanador = this.mesa.resultadoEnvido();
		
		assertEquals(jugadorGanador,this.segundoJugador);
	}
	
	@Test
	public void testElJugadorConMayorFlorEsDevueltoComoGanador(){
		Jugador jugadorGanador = this.mesa.resultadoFlor();
		
		assertEquals(jugadorGanador,this.primerJugador);
	}
}
