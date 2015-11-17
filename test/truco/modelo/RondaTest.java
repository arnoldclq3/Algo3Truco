package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RondaTest {
	
	private Ronda unaRonda;
	private Equipo equipo1;
	private Equipo equipo2;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugador3;
	private Jugador jugador4;	
	
	@Before
	public void setup() {
	
		jugador1 = new Jugador("jugador1", true, false);
		jugador2 = new Jugador("jugador2", false, false);
		jugador3 = new Jugador("jugador3", false, false);
		jugador4 = new Jugador("jugador4", false, false);
		
		equipo1 = new Equipo();
		equipo1.agregarJugador(jugador1);
		equipo1.agregarJugador(jugador3);
		equipo2 = new Equipo();
		equipo2.agregarJugador(jugador2);
		equipo2.agregarJugador(jugador4);
		
		this.unaRonda = new Ronda(equipo1,equipo2);
	}

	@Test
	public void testGanaLaRondaElEquipoQueGanoLasDosPrimerasManos() {
		
		this.unaRonda.jugarCarta(jugador1, new Carta(1, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		this.unaRonda.jugarCarta(jugador3, new Carta(4, Palo.COPA));
		this.unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		this.unaRonda.jugarCarta(jugador1, new Carta(10, Palo.BASTO));
		this.unaRonda.jugarCarta(jugador2, new Carta(6, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		this.unaRonda.jugarCarta(jugador4, new Carta(5, Palo.BASTO));
		
		assertEquals(equipo1,this.unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQuePierdeLaPrimerManoYGanaLasDosUltimasManos() {
		
		this.unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		this.unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		this.unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		this.unaRonda.jugarCarta(jugador1, new Carta(7, Palo.COPA));
		this.unaRonda.jugarCarta(jugador2, new Carta(3, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador3, new Carta(6, Palo.COPA));
		this.unaRonda.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		
		this.unaRonda.jugarCarta(jugador2, new Carta(6, Palo.ORO));
		this.unaRonda.jugarCarta(jugador3, new Carta(5, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador4, new Carta(3, Palo.COPA));
		this.unaRonda.jugarCarta(jugador1, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo2,this.unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraYLaUltimaMano() {
		
		this.unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		this.unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		this.unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		this.unaRonda.jugarCarta(jugador1, new Carta(7, Palo.COPA));
		this.unaRonda.jugarCarta(jugador2, new Carta(7, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador3, new Carta(2, Palo.ESPADA));
		this.unaRonda.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		
		this.unaRonda.jugarCarta(jugador2, new Carta(6, Palo.ORO));
		this.unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		this.unaRonda.jugarCarta(jugador4, new Carta(3, Palo.COPA));
		this.unaRonda.jugarCarta(jugador1, new Carta(4, Palo.BASTO));
		
		assertEquals(equipo1,this.unaRonda.obtenerEquipoGanador());
	}
	/*
	 * Estos Test contemplan mano, todavia no implementado
	 *
	 *
	@Test
	public void testGanaLaRondaElEquipoQueGanoLaSegundaManoYEmpatoLaPrimerManoYElEQuipoEraMano() {
		
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(4, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.BASTO));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(4, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.ORO));
		
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(6, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(5, Palo.BASTO));
		
		Jugador jugador = new Jugador("jugador1");
		assertTrue(this.unaRonda.equipoGanadorDeLaRonda().estaJugador(jugador));
	}
	
	
	@Test
	public void testGanaLaRondaElEquipoQuePierdeLaPrimerManoEmpataLaSegundaManoYGanaLaTercerMano() {
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(1, Palo.BASTO));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.BASTO));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(1, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.ORO));
		
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(4, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(6, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.BASTO));
		
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.ORO));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(2, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.BASTO));
		
		
		Jugador jugador = new Jugador("jugador2");
		assertTrue(this.unaRonda.equipoGanadorDeLaRonda().estaJugador(jugador));
	}
	

	*/
}
