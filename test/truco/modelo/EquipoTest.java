package truco.modelo;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;

public class EquipoTest {
	
	private Jugador unJugador;
	private Jugador otroJugador;

	@Before
	public void setup() {
		this.unJugador = new Jugador();
		this.otroJugador = new Jugador();
	}
	
	@Test (expected = ExisteJugadorEnEquipoException.class)
	public void testNoSePuedeAgregarUnJugadorQueYaExisteAlEquipo() {
		
		Equipo equipo = new Equipo(this.unJugador);
		equipo.agregarJugador(this.unJugador);
	}

}
