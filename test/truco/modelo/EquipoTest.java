package truco.modelo;

import static org.junit.Assert.assertEquals;

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
	public void testDevuelvaCantidadCorrectaDeJugadoresEnEquipo() {
		
		Equipo equipo = new Equipo();
		equipo.agregarJugador(this.unJugador);
		equipo.agregarJugador(this.otroJugador);
		
		assertEquals(equipo.cantidadJugadores(),2);
	}

}
