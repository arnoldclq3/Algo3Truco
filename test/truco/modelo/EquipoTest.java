package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;
import truco.excepciones.equipo.JugadorInexistenteException;

public class EquipoTest {
	
	private Jugador unJugador;
	private Jugador otroJugador;
	private Jugador tercerJugador;

	@Before
	public void setup() {
		this.unJugador = new Jugador("Jugador 1");
		this.otroJugador = new Jugador("Jugador 2");
		this.tercerJugador = new Jugador("Jugador 3");
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
	
	@Test
	public void testEquipoDeUnJugadorDevuelveElMismoJugadorAlPedirTurnoSiguiente() {
		
		Equipo equipo = new Equipo(this.unJugador);
		
		Jugador nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.unJugador));
		
		nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.unJugador));
		
		
	}
	
	@Test
	public void testEquipoDeDosJugadoresDevuelvePrimeroUnJugadorYLuegoElOtro() {
		
		Equipo equipo = new Equipo(this.unJugador);
		equipo.agregarJugador(this.otroJugador);
		
		Jugador nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.unJugador));
		
		nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.otroJugador));
		
		nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.unJugador));
		
	}
	
	@Test
	public void testEquipoDeTresJugadoresDevuelveTodosLosJugadoresYVuelveAlPrimero() {
		
		Equipo equipo = new Equipo(this.unJugador);
		equipo.agregarJugador(this.otroJugador);
		equipo.agregarJugador(this.tercerJugador);
		
		Jugador nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.unJugador));
		
		nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.otroJugador));
		
		nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.tercerJugador));
		
		nuevoJugador = equipo.siguienteTurno();
		assertTrue(nuevoJugador.equals(this.unJugador));
		
	}
	
	@Test
	public void testEquipoSumaPuntosCorrectamenteSiElJugadorExiste() {
		
		Equipo equipo = new Equipo(this.unJugador);
		int puntos = 2;
		
		equipo.agregarJugador(this.otroJugador);
		
		equipo.sumarPuntosAJugador(this.unJugador,puntos);
		assertEquals(equipo.obtenerCantidadDePuntos(),2);
		
	}
	
	@Test (expected = JugadorInexistenteException.class)
	public void testEquipoSumarPuntosLanzaExcepecionSiJugadorNoExisteEnElEquipo() {
		
		Equipo equipo = new Equipo(this.unJugador);
		int puntos = 2;
		
		equipo.sumarPuntosAJugador(this.otroJugador,puntos);
		
	}
	
	

}
