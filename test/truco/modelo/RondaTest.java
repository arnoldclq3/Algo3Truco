package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RondaTest {
		private Ronda unaRonda;
	
	
	@Before
	public void setup() {
		Jugador jugador1 = new Jugador("jugador1", true, false);
		Jugador jugador2 = new Jugador("jugador2", false, false);
		Jugador jugador3 = new Jugador("jugador3", false, true);
		Jugador jugador4 = new Jugador("jugador4", false, false);
		Equipo equipo1 = new Equipo(jugador1);
		equipo1.agregarJugador(jugador2);
		Equipo equipo2 = new Equipo(jugador3);
		equipo2.agregarJugador(jugador4);
		this.unaRonda = new Ronda(equipo1,equipo2);
	}
	
	
	@Test
	public void testGanaLaRondaElEquipoQueGanoLasDosPrimerasManos() {
		
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(1, Palo.ESPADA));
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
	
	
	@Test
	public void testGanaLaRondaElEquipoQuePierdeLaPrimerManoYGanaLasDosUltimasManos() {
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(1, Palo.BASTO));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.BASTO));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(1, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.ORO));
		
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(6, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.BASTO));
		
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.ORO));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(7, Palo.ESPADA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(2, Palo.COPA));
		this.unaRonda.jugarCarta(this.unaRonda.equipoEnTurno().siguienteTurno(), new Carta(3, Palo.BASTO));
		
		
		Jugador jugador = new Jugador("jugador2");
		assertTrue(this.unaRonda.equipoGanadorDeLaRonda().estaJugador(jugador));
	}
}
