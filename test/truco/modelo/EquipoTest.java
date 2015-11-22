package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.equipo.ExisteJugadorEnEquipoException;
import truco.excepciones.equipo.JugadorInexistenteException;

public class EquipoTest {
	
	private Jugador primerJugador;
	private Jugador segundoJugador;
	private Jugador tercerJugador;
	private Equipo equipo;

	@Before
	public void setup() {
		this.primerJugador = new Jugador("Jugador 1");
		this.segundoJugador = new Jugador("Jugador 2");
		this.tercerJugador = new Jugador("Jugador 3");
		this.equipo = new Equipo();
	}
	
	@Test (expected = ExisteJugadorEnEquipoException.class)
	public void testNoSePuedeAgregarUnJugadorQueYaExisteAlEquipoYLanzaUnaExcepcion() {
		
		this.equipo.agregarJugador(primerJugador);
		equipo.agregarJugador(this.primerJugador);
	}
	
	@Test
	public void testEquipoDevuelvaCantidadCorrectaDeJugadores() {
		
		equipo.agregarJugador(this.primerJugador);
		equipo.agregarJugador(this.segundoJugador);
		
		assertEquals(equipo.cantidadJugadores(),2);
		
	}
	
	@Test
	public void testSiElJugadorEstaEnElEquipoDevuelveTrue() {
		
		this.equipo.agregarJugador(primerJugador);
		
		assertTrue(equipo.estaJugador(this.primerJugador));
		
	}
	
	@Test
	public void testSiElJugadorNoEstaEnElEquipoDevuelveFalse() {
		
		this.equipo.agregarJugador(primerJugador);
		
		assertFalse(equipo.estaJugador(this.segundoJugador));
		
	}
	
	@Test
	public void testEquipoDeUnJugadorDevuelveElMismoJugadorAlPedirTurnoSiguiente() {
		
		this.equipo.agregarJugador(primerJugador);
		
		Jugador nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.primerJugador));
		
		nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.primerJugador));
		
		
	}
	
	@Test
	public void testEquipoDeDosJugadoresDevuelvePrimeroUnJugadorYLuegoElOtro() {
		
		this.equipo.agregarJugador(primerJugador);
		equipo.agregarJugador(this.segundoJugador);
		
		Jugador nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.primerJugador));
		
		nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.segundoJugador));
		
		nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.primerJugador));
		
	}
	
	@Test
	public void testEquipoDeTresJugadoresDevuelveTodosLosJugadoresYVuelveAlPrimero() {
		
		this.equipo.agregarJugador(primerJugador);
		equipo.agregarJugador(this.segundoJugador);
		equipo.agregarJugador(this.tercerJugador);
		
		Jugador nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.primerJugador));
		
		nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.segundoJugador));
		
		nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.tercerJugador));
		
		nuevoJugador = equipo.siguienteJugador();
		assertTrue(nuevoJugador.equals(this.primerJugador));
		
	}
	
	@Test
	public void testEquipoSumaPuntosCorrectamenteSiElJugadorExiste() {
		
		this.equipo.agregarJugador(primerJugador);
		int puntos = 2;
		
		equipo.agregarJugador(this.segundoJugador);
		
		equipo.sumarPuntosAJugador(this.primerJugador,puntos);
		assertEquals(equipo.obtenerCantidadDePuntos(),2);
		
	}
	
	@Test (expected = JugadorInexistenteException.class)
	public void testEquipoSumarPuntosLanzaExcepecionSiJugadorNoExisteEnElEquipo() {
		
		this.equipo.agregarJugador(primerJugador);
		int puntos = 2;
		
		equipo.sumarPuntosAJugador(this.segundoJugador,puntos);
		
	}
	
	

}
