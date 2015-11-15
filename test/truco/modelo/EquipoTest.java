package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;

public class EquipoTest {
	
	private Jugador unJugador;
	private Jugador otroJugador;

	@Before
	public void setup() {
		this.unJugador = new Jugador("Jugador 1");
		this.otroJugador = new Jugador("Jugador 2");
	}
	
	@Test (expected = ExisteJugadorEnEquipoException.class)
	public void testNoSePuedeAgregarUnJugadorQueYaExisteAlEquipoYLanzaUnaExcepcion() {
		
		Equipo equipo = new Equipo(this.unJugador);
		equipo.agregarJugador(this.unJugador);
	}
	
	@Test
	public void testEquipoDevuelvaCantidadCorrectaDeJugadores() {
		
		Equipo equipo = new Equipo();
		equipo.agregarJugador(this.unJugador);
		equipo.agregarJugador(this.otroJugador);
		
		assertEquals(equipo.cantidadJugadores(),2);
		
	}
	
	@Test
	public void testSiElJugadorEstaEnElEquipoDevuelveTrue() {
		
		Equipo equipo = new Equipo(this.unJugador);
		
		assertTrue(equipo.estaJugador(this.unJugador));
		
	}
	
	@Test
	public void testSiElJugadorNoEstaEnElEquipoDevuelveFalse() {
		
		Equipo equipo = new Equipo(this.unJugador);
		
		assertFalse(equipo.estaJugador(this.otroJugador));

	}

}
