package truco.modelo;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.ronda.*;

public class RondaTest {
	
	private Equipo equipo1;
	private Equipo equipo2;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugador3;
	private Jugador jugador4;	
	LinkedList<Jugador> ordenJugadores;
	
	@Before
	public void setup() {
	
		jugador1 = new Jugador();
		jugador2 = new Jugador();
		jugador3 = new Jugador();
		jugador4 = new Jugador();
		
		this.ordenJugadores = new LinkedList<Jugador>();
		ordenJugadores.add(jugador1);
		ordenJugadores.add(jugador2);
		ordenJugadores.add(jugador3);
		ordenJugadores.add(jugador4);
		
		equipo1 = new Equipo();
		equipo1.agregarJugador(jugador1);
		equipo1.agregarJugador(jugador3);
		equipo1.setEsMano(true);
		
		equipo2 = new Equipo();
		equipo2.agregarJugador(jugador2);
		equipo2.agregarJugador(jugador4);
		equipo2.setEsMano(false);
	}
	
	/* 
	 * 
	 * TEST: DETERMINAR GANADOR RONDA SIN TRUCO SIN ENVIDO 
	 * 
	 * */
	
	@Test
	public void testGanaLaRondaElEquipoQueGanoLasDosPrimerasManos() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(4, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(6, Palo.ESPADA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(5, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQuePierdeLaPrimerManoYGanaLasDosUltimasManos() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador1, new Carta(6, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		unaRonda.jugarCarta(jugador1, new Carta(5, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraYLaUltimaMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(1, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(7, Palo.ESPADA));
		unaRonda.jugarCarta(jugador1, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		unaRonda.jugarCarta(jugador1, new Carta(3, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(5, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(4, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueEmpataLaPrimeraManoYGanaLaSegundaMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(3, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(7, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueEmpataLaPrimeraYSegundaManoYGanaLaTercerMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(3, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueEmpataTodasLasManosYElEquipoEsMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(3, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador1, new Carta(2, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador3, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador4, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYPierdeLaSegundaManoYEmpataLaTercerMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(2, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador4, new Carta(2, Palo.COPA));
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYEmpataLaSegundaMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(4, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(4, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test (expected = NoEsElTurnoDeEsteJugadorException.class)
	public void testElJugadorQueGanoLaPrimeraManoDebeIniciarLaSegundaMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(4, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(4, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(11, Palo.ESPADA));
	}
	
	@Test (expected = NoHayEquipoGanadorHastaQueLaRondaTermineException.class)
	public void testNoHayGanadorHastaQueTermineLaRonda() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.obtenerEquipoGanador();
	}
	
	@Test (expected = NoSePuedeJugarMasCartasRondaTerminadaException.class)
	public void testNoSePuedeJugarSiLaRondaTermino() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(4, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(4, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador2, new Carta(5, Palo.COPA));
	}
	
	/* 
	 * 
	 * TEST: DETERMINAR GANADOR RONDA CON TRUCO 
	 * 
	 * */
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYEmpataLaSegundaManoCantando_Truco() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		
		unaRonda.truco(jugador3);
		unaRonda.quiero(jugador2);
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(4, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(4, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),2);
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYEmpataLaSegundaManoCantando_Truco_Retruco_ValeCuatro_NoQuiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		
		unaRonda.truco(jugador3);
		unaRonda.retruco(jugador2);
		unaRonda.valeCuatro(jugador3);
		unaRonda.noQuiero(jugador2);

		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),3);
	}
	
	@Test
	public void testGanaLaRondaElEquipoQuePierdeLaPrimerManoYGanaLasDosUltimasManosCantando_Truco_Retruco_Quiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		
		unaRonda.truco(jugador4);
		unaRonda.retruco(jugador1);
		unaRonda.quiero(jugador4);
		
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador1, new Carta(6, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		unaRonda.jugarCarta(jugador1, new Carta(5, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo2.obtenerCantidadDePuntos(),3);
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueEmpataTodasLasManosYElEquipoEsManoCantando_Truco_Retruco_ValeCuatro_Quiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(3, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.COPA));
		
		unaRonda.truco(jugador1);
		unaRonda.retruco(jugador2);
		unaRonda.valeCuatro(jugador1);
		unaRonda.quiero(jugador2);
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador1, new Carta(2, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador3, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador4, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),4);
	}
	
	/* 
	 * 
	 * TEST: DETERMINAR GANADOR RONDA CON ENVIDO 
	 * 
	 * */
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYPierdeLaSegundaManoYEmpataLaTercerManoCantando_Envido_Quiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ESPADA));
		jugador1.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(10, Palo.BASTO));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.COPA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.COPA));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		
		unaRonda.envido(jugador3);
		unaRonda.quiero(jugador4);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(2, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.COPA));
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(6, Palo.ORO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),3);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueEmpataLaPrimeraYSegundaManoYGanaLaTercerManoCantando_Envido_Envido_Quiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(3, Palo.ORO));
		jugador1.tomarCarta(new Carta(10, Palo.COPA));
		jugador1.tomarCarta(new Carta(11, Palo.COPA));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(3, Palo.BASTO));
		jugador2.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(1, Palo.ORO));
		jugador3.tomarCarta(new Carta(3, Palo.ESPADA));
		jugador3.tomarCarta(new Carta(7, Palo.ESPADA));
		jugador4.tomarCarta(new Carta(3, Palo.COPA));
		jugador4.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador4.tomarCarta(new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador1, new Carta(3, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		
		unaRonda.envido(jugador3);
		unaRonda.envido(jugador2);
		unaRonda.quiero(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
		
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador1, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),5);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanoLasDosPrimerasManosCantando_RealEnvido_RealEnvido_Quiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(1, Palo.ESPADA));
		jugador1.tomarCarta(new Carta(10, Palo.BASTO));
		jugador1.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(3, Palo.BASTO));
		jugador2.tomarCarta(new Carta(6, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(3, Palo.ORO));
		jugador4.tomarCarta(new Carta(5, Palo.BASTO));
		jugador4.tomarCarta(new Carta(5, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(4, Palo.COPA));
		
		unaRonda.realEnvido(jugador4);
		unaRonda.realEnvido(jugador1);
		unaRonda.quiero(jugador4);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
		
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(6, Palo.ESPADA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(5, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),7);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanoLasDosPrimerasManosCantando_RealEnvido_RealEnvido_Envido_Envido_Quiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(1, Palo.ESPADA));
		jugador1.tomarCarta(new Carta(10, Palo.BASTO));
		jugador1.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(3, Palo.BASTO));
		jugador2.tomarCarta(new Carta(6, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(3, Palo.ORO));
		jugador4.tomarCarta(new Carta(5, Palo.BASTO));
		jugador4.tomarCarta(new Carta(5, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(4, Palo.COPA));
		
		unaRonda.envido(jugador4);
		unaRonda.envido(jugador3);
		unaRonda.realEnvido(jugador4);
		unaRonda.realEnvido(jugador3);
		unaRonda.quiero(jugador4);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
		
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(6, Palo.ESPADA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(5, Palo.BASTO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),11);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanoLasDosPrimerasManosCantando_Envido_FaltaEnvido_Quiero() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(1, Palo.ESPADA));
		jugador1.tomarCarta(new Carta(10, Palo.BASTO));
		jugador1.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(3, Palo.BASTO));
		jugador2.tomarCarta(new Carta(6, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(3, Palo.ORO));
		jugador4.tomarCarta(new Carta(5, Palo.BASTO));
		jugador4.tomarCarta(new Carta(5, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(4, Palo.COPA));
		
		unaRonda.envido(jugador4);
		unaRonda.faltaEnvido(jugador1);
		unaRonda.quiero(jugador4);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
				
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),30);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test (expected = NoSePuedeJugarMasCartasRondaTerminadaException.class)
	public void testNoSePuedeJugarMasCartasSiSeGanoUnFaltaEnvido() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(1, Palo.ESPADA));
		jugador1.tomarCarta(new Carta(10, Palo.BASTO));
		jugador1.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(3, Palo.BASTO));
		jugador2.tomarCarta(new Carta(6, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(4, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(3, Palo.ORO));
		jugador4.tomarCarta(new Carta(5, Palo.BASTO));
		jugador4.tomarCarta(new Carta(5, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(4, Palo.COPA));
		
		unaRonda.envido(jugador4);
		unaRonda.faltaEnvido(jugador1);
		unaRonda.quiero(jugador4);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ESPADA));
	}
	
	
	@Test (expected = SoloLosJugadoresPieDeLaRondaPuedenCantarElEnvidoException.class)	
	public void testSiUnJugadorNoEsPieYCantaEnvidoSeLanzaExcepcion(){
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		unaRonda.envido(jugador1);
	}
	
	@Test (expected = SoloSePuedeCantarElTantoEnLaPrimeraManoException.class)	
	public void testSiUnJugadorIntentaCantarEnvidoEnLaSegundaRondaSeDebeLanzarExcepcion(){
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(11, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ORO));
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(10, Palo.BASTO));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.COPA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.COPA));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(11, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.envido(jugador1);
	}
	
	/* 
	 * 
	 * TEST: DETERMINAR GANADOR RONDA CON FLOR
	 * 
	 * */
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYPierdeLaSegundaManoYEmpataLaTercerManoCantando_Flor() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(11, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ORO));
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(10, Palo.BASTO));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.COPA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.COPA));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.flor(jugador1);
		unaRonda.noQuiero(jugador2);
		
		unaRonda.jugarCarta(jugador1, new Carta(11, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(2, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.COPA));
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(6, Palo.ORO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),4);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYPierdeLaSegundaManoYEmpataLaTercerManoCantando_Flor_Flor() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(11, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ORO));
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.COPA));
		jugador2.tomarCarta(new Carta(10, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.BASTO));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.flor(jugador1);
		unaRonda.flor(jugador2);
		unaRonda.quiero(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador2);
		
		unaRonda.jugarCarta(jugador1, new Carta(11, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(2, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.COPA));
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(6, Palo.ORO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo2.obtenerCantidadDePuntos(),2);
		assertEquals(equipo1.obtenerCantidadDePuntos(),5);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYPierdeLaSegundaManoYEmpataLaTercerManoCantando_Flor_ContraFlor() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(11, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ORO));
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.COPA));
		jugador2.tomarCarta(new Carta(10, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.BASTO));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.flor(jugador1);
		unaRonda.contraFlor(jugador2);
		unaRonda.quiero(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador2);
		
		unaRonda.jugarCarta(jugador1, new Carta(11, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(11, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador1, new Carta(2, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.COPA));
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(10, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(6, Palo.ORO));
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),7);
		assertEquals(equipo2.obtenerCantidadDePuntos(),0);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testGanaLaRondaElEquipoQueGanaLaPrimeraManoYPierdeLaSegundaManoYEmpataLaTercerManoCantando_Flor_ContraFlorAResto() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(11, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ORO));
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.COPA));
		jugador2.tomarCarta(new Carta(10, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.BASTO));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.flor(jugador1);
		unaRonda.contraFlorAResto(jugador2);
		unaRonda.quiero(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador2);
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),30);
		assertEquals(equipo2.obtenerCantidadDePuntos(),0);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test (expected = NoSePuedeJugarMasCartasRondaTerminadaException.class)
	public void testNoSePuedeJugarMasCartasSiSeGanoUnContraFlorAResto() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(11, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ORO));
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.COPA));
		jugador2.tomarCarta(new Carta(10, Palo.COPA));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.BASTO));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.flor(jugador1);
		unaRonda.contraFlorAResto(jugador2);
		unaRonda.quiero(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador1);
		unaRonda.cantarTantoDeLaFlor(jugador2);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
		
		unaRonda.jugarCarta(jugador3, new Carta(6, Palo.ORO));
	}
	
	/* 
	 * 
	 * TEST: IRSE AL MAZO 
	 * 
	 * */
	
	@Test
	public void testSeVaAlMazoElJugador1EnLaSegundaPrimerMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.irseAlMazo(jugador1);
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testSeVaAlMazoElJugador1EnLaSegundaMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		unaRonda.irseAlMazo(jugador1);
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testSeVaAlMazoElJugador1EnLaTercerMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		unaRonda.jugarCarta(jugador1, new Carta(4, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		unaRonda.irseAlMazo(jugador1);
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testTodosLosJugadoresDelEquipo1SeVanAlMazo() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.irseAlMazo(jugador3);
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		unaRonda.irseAlMazo(jugador1);
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testTodosLosJugadoresDelEquipo2SeVanAlMazo() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador3, new Carta(3, Palo.ESPADA));
		unaRonda.irseAlMazo(jugador4);
		unaRonda.jugarCarta(jugador1, new Carta(7, Palo.BASTO));
		unaRonda.irseAlMazo(jugador2);
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
	}
	
	@Test
	public void testTodosLosJugadoresDelEquipo2SeVanAlMazoEnLaPrimeraMano() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.irseAlMazo(jugador2);
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.irseAlMazo(jugador4);
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),2);
	}
	
	@Test
	public void testEquipo1GanaEnvidoYLuegoSeVaAlMazo() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ESPADA));
		jugador1.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(10, Palo.BASTO));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.COPA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.COPA));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		
		unaRonda.envido(jugador3);
		unaRonda.quiero(jugador4);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.irseAlMazo(jugador3);
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.BASTO));
		unaRonda.irseAlMazo(jugador1);
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),2);
		assertEquals(equipo2.obtenerCantidadDePuntos(),1);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test
	public void testEquipo1GanaEnvidoYLuegoElEquipo2SeVaAlMazo() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		jugador1.tomarCarta(new Carta(10, Palo.ORO));
		jugador1.tomarCarta(new Carta(2, Palo.ESPADA));
		jugador1.tomarCarta(new Carta(10, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(1, Palo.COPA));
		jugador2.tomarCarta(new Carta(11, Palo.ESPADA));
		jugador2.tomarCarta(new Carta(10, Palo.BASTO));
		jugador3.tomarCarta(new Carta(7, Palo.ORO));
		jugador3.tomarCarta(new Carta(11, Palo.COPA));
		jugador3.tomarCarta(new Carta(6, Palo.ORO));
		jugador4.tomarCarta(new Carta(10, Palo.COPA));
		jugador4.tomarCarta(new Carta(3, Palo.BASTO));
		jugador4.tomarCarta(new Carta(6, Palo.COPA));
		
		unaRonda.jugarCarta(jugador1, new Carta(10, Palo.ORO));
		unaRonda.jugarCarta(jugador2, new Carta(1, Palo.COPA));
		
		unaRonda.envido(jugador3);
		unaRonda.quiero(jugador4);
		unaRonda.cantarTantoDelEnvido(jugador1);
		unaRonda.cantarTantoDelEnvido(jugador2);
		unaRonda.cantarTantoDelEnvido(jugador3);
		unaRonda.cantarTantoDelEnvido(jugador4);
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.ORO));
		unaRonda.jugarCarta(jugador4, new Carta(10, Palo.COPA));
		
		unaRonda.jugarCarta(jugador3, new Carta(3, Palo.BASTO));
		unaRonda.irseAlMazo(jugador4);
		unaRonda.jugarCarta(jugador1, new Carta(11, Palo.ESPADA));
		unaRonda.irseAlMazo(jugador2);
		
		assertEquals(equipo1,unaRonda.obtenerEquipoGanador());
		assertEquals(equipo1.obtenerCantidadDePuntos(),3);
		assertEquals(equipo2.obtenerCantidadDePuntos(),0);
		
		jugador1.devolverCartas();
		jugador2.devolverCartas();
		jugador3.devolverCartas();
		jugador4.devolverCartas();
	}
	
	@Test (expected = EsteJugadorSeFueAlMazoException.class)
	public void testUnJugadorSeFueAlMazoYQuiereJugarCarta() {
		
		Ronda unaRonda = new Ronda(this.equipo1, this.equipo2, this.ordenJugadores);
		
		unaRonda.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		unaRonda.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		
		unaRonda.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		unaRonda.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		unaRonda.irseAlMazo(jugador1);
		unaRonda.jugarCarta(jugador2, new Carta(7, Palo.BASTO));
		
		unaRonda.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		unaRonda.jugarCarta(jugador1, new Carta(4, Palo.COPA));
		unaRonda.jugarCarta(jugador2, new Carta(3, Palo.COPA));
		unaRonda.jugarCarta(jugador3, new Carta(2, Palo.BASTO));
		
		assertEquals(equipo2,unaRonda.obtenerEquipoGanador());
	}

	
	
	//*/
}
