package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.cantos.NoSePuedeCantarTantoDosVecesEnUnaRondaException;
import truco.excepciones.cantos.RespuestaIncorrectaException;


public class MesaTest {

	private Mesa mesa;
	private Jugador jugadorNosotros;
	private Jugador jugadorEllos;
	private Equipo equipoNosotros;
	private Equipo equipoEllos;
	
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugador3;
	private Jugador jugador4;
	private Jugador jugador5;
	private Jugador jugador6;
	private Equipo equipoA;
	private Equipo equipoB;
	private Mesa mesaPicaPica;


	@Before
	public void setup(){
		
		this.jugadorNosotros = new Jugador("Jugador 1");
		this.jugadorEllos = new Jugador("Jugador 2");
		this.equipoNosotros = new Equipo();
		equipoNosotros.agregarJugador(this.jugadorNosotros);
		this.equipoEllos = new Equipo();
		equipoEllos.agregarJugador(this.jugadorEllos);
		this.mesa = new Mesa(equipoNosotros,equipoEllos, new GeneradorRondasNormales() );
		
		this.jugador1 = new Jugador("Juan1");
		this.jugador2 = new Jugador("Juan2");
		this.jugador3 = new Jugador("Juan3");
		this.jugador4 = new Jugador("Juan4");
		this.jugador5 = new Jugador("Juan5");
		this.jugador6 = new Jugador("Juan6");
		this.equipoA = new Equipo();
		this.equipoA.agregarJugador(jugador1);
		this.equipoA.agregarJugador(jugador3);
		this.equipoA.agregarJugador(jugador5);
		this.equipoB = new Equipo();
		this.equipoB.agregarJugador(jugador2);
		this.equipoB.agregarJugador(jugador4);
		this.equipoB.agregarJugador(jugador6);
		
	}
	
	
	@Test
	public void testLaMesaSeIniciaConDosJugadores() {
		assertTrue(this.mesa.cantidadDeJugadores() == 2);
	}
	
	@Test
	public void testAlInciarseUnProcesoDeEnvidoElJugadorConMayorTantoEsElGanador(){
		this.mesa.envido(jugadorNosotros);
		this.mesa.quiero(jugadorEllos);
		this.mesa.cantarTantoDelEnvido(jugadorNosotros);
		this.mesa.cantarTantoDelEnvido(jugadorEllos);
		
		Jugador jugadorConMayorTanto = this.jugadorEllos;
		if (this.jugadorNosotros.puntajeEnvido() >= this.jugadorEllos.puntajeEnvido() )
			jugadorConMayorTanto = this.jugadorNosotros;
		
		assertEquals(this.mesa.ganadorDelTantoDeLaRondaActual() , jugadorConMayorTanto);
	}
	
	
	@Test (expected = NoSePuedeCantarTantoDosVecesEnUnaRondaException.class)
	public void testUnaVezFinalizadoElProcesoDeUnEnvidoNoPuedeVolverseACantarOtroEnvido(){
		this.mesa.envido(jugadorNosotros);
		this.mesa.quiero(jugadorEllos);
		this.mesa.cantarTantoDelEnvido(jugadorNosotros);
		this.mesa.cantarTantoDelEnvido(jugadorEllos);
		
		this.mesa.envido(jugadorNosotros);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeResponderEnvidoAUnRealEnvido(){
		this.mesa.realEnvido(jugadorEllos);
		
		this.mesa.envido(jugadorNosotros);
	}
	
	@Test
	public void testSePuedeResponderRealEnvidoAUnEnvidoYGanaElJugadorQueTengaMayorTanto(){
		this.mesa.envido(jugadorEllos);
		this.mesa.realEnvido(jugadorNosotros);
		this.mesa.quiero(jugadorEllos);
		this.mesa.cantarTantoDelEnvido(jugadorNosotros);
		this.mesa.cantarTantoDelEnvido(jugadorEllos);
		
		Jugador jugadorConMayorTanto = this.jugadorNosotros;
		if (this.jugadorEllos.puntajeEnvido() > this.jugadorNosotros.puntajeEnvido() )
			jugadorConMayorTanto = this.jugadorEllos;
		Equipo equipoGanador = this.equipoEllos;
		if (this.equipoNosotros.estaJugador(jugadorConMayorTanto) )
			equipoGanador = this.equipoNosotros;
		
		assertEquals(5,equipoGanador.obtenerCantidadDePuntos() );
	}
	
	@Test
	public void testElEquipoBGanaUnaRonda() {
		
		this.mesaPicaPica = new Mesa(this.equipoA,this.equipoB, new GeneradorRondasPicaPica() );
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(12, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(12, Palo.ORO));
		
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(6, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(7, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(12, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(12, Palo.BASTO));
		
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(5, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(3, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(2, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(11, Palo.ORO));
		
		assertEquals(1, this.equipoB.obtenerCantidadDePuntos());
	}
	
	@Test
	public void testElEquipoBGanaUnaRondaCon_Truco_Quiero() {
		
		this.mesaPicaPica = new Mesa(this.equipoA,this.equipoB, new GeneradorRondasPicaPica() );
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(12, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(12, Palo.ORO));
		
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(6, Palo.COPA));
		
		this.mesaPicaPica.truco(jugador6);
		this.mesaPicaPica.quiero(jugador1);
		
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(7, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(12, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(12, Palo.BASTO));
		
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(5, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(3, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(2, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(11, Palo.ORO));
		
		assertEquals(2, this.equipoB.obtenerCantidadDePuntos());
	}
	
	@Test
	public void testElEquipoBGanaUnaRondaCon_Truco_Retruco_ValeCuatro_NoQuiero() {
		
		this.mesaPicaPica = new Mesa(this.equipoA,this.equipoB, new GeneradorRondasPicaPica() );
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(12, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(12, Palo.ORO));
		
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(6, Palo.COPA));
		
		this.mesaPicaPica.truco(jugador6);
		this.mesaPicaPica.retruco(jugador1);
		this.mesaPicaPica.valeCuatro(jugador6);
		this.mesaPicaPica.noQuiero(jugador1);
		
		assertEquals(3, this.equipoB.obtenerCantidadDePuntos());
	}
	
	@Test
	public void testElEquipoBGanaDosRondas() {
		
		this.mesaPicaPica = new Mesa(this.equipoA,this.equipoB, new GeneradorRondasPicaPica() );
		
		// RONDA 1
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(1, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(3, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(1, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(12, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(12, Palo.ORO));
		
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(3, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(6, Palo.COPA));
		
		this.mesaPicaPica.truco(jugador6);
		this.mesaPicaPica.quiero(jugador1);
		
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(7, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(12, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(12, Palo.BASTO));
		
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(6, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(5, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(3, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(2, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(11, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(11, Palo.ORO));
		
		// RONDA 2
		
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(1, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(3, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(1, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(3, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(12, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(12, Palo.ORO));
		
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(3, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(6, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(11, Palo.COPA));
		
		this.mesaPicaPica.truco(jugador2);
		this.mesaPicaPica.retruco(jugador3);
		this.mesaPicaPica.valeCuatro(jugador2);
		this.mesaPicaPica.noQuiero(jugador3);
		
		assertEquals(5, this.equipoB.obtenerCantidadDePuntos());
	}
	
	@Test
	public void testElJugador1GanaUnaRondaPicaPicaYSumaPuntosASuEquipo() {
		
		this.equipoA.sumarPuntos(6);
		this.equipoB.sumarPuntos(6);
		this.mesaPicaPica = new Mesa(this.equipoA,this.equipoB, new GeneradorRondasPicaPica() );
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(11, Palo.COPA));
		
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(11, Palo.BASTO));
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(7, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(11, Palo.ESPADA));
		
		assertEquals(7, this.equipoA.obtenerCantidadDePuntos());
	}

	@Test
	public void testElJugador1GanaUnaRondaPicaPicaYSumaPuntosASuEquipoCon_Truco_Retruco_Quiero() {
		
		this.equipoA.sumarPuntos(6);
		this.equipoB.sumarPuntos(6);
		this.mesaPicaPica = new Mesa(this.equipoA,this.equipoB, new GeneradorRondasPicaPica() );
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(11, Palo.COPA));
		
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		
		this.mesaPicaPica.truco(jugador1);
		this.mesaPicaPica.retruco(jugador4);
		this.mesaPicaPica.quiero(jugador1);
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(11, Palo.BASTO));
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(7, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(11, Palo.ESPADA));
		
		assertEquals(9, this.equipoA.obtenerCantidadDePuntos());
	}
	
	@Test
	public void testEquipoAGanasDosRondasPicaPicaYEquipoBGanaUnaSola() {
		
		this.equipoA.sumarPuntos(6);
		this.equipoB.sumarPuntos(6);
		this.mesaPicaPica = new Mesa(this.equipoA,this.equipoB, new GeneradorRondasPicaPica() );
		
		// RONDA PICAPICA 1 GANA Jugador1
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(7, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(11, Palo.COPA));
		
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(7, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(11, Palo.BASTO));
		
		this.mesaPicaPica.jugarCarta(jugador1, new Carta(7, Palo.ESPADA));
		this.mesaPicaPica.jugarCarta(jugador4, new Carta(11, Palo.ESPADA));
		
		// RONDA PICAPICA 2 GANA Jugador2
		
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(10, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(11, Palo.ORO));
		
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(6, Palo.BASTO));
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(10, Palo.BASTO));
		
		this.mesaPicaPica.jugarCarta(jugador2, new Carta(7, Palo.ORO));
		this.mesaPicaPica.jugarCarta(jugador5, new Carta(10, Palo.ESPADA));
		
		// RONDA PICAPICA 3 GANA Jugador3
		
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(5, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(6, Palo.COPA));
		
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(6, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(1, Palo.COPA));
		
		this.mesaPicaPica.jugarCarta(jugador3, new Carta(3, Palo.COPA));
		this.mesaPicaPica.jugarCarta(jugador6, new Carta(4, Palo.ESPADA));
		
		assertEquals(8, this.equipoA.obtenerCantidadDePuntos());
		assertEquals(7, this.equipoB.obtenerCantidadDePuntos());
	}
}
