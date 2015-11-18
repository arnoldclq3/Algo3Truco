package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.cantos.RespuestaIncorrectaException;

public class CantosEnProcesoParaElTrucoTest {

	private CantosEnProcesoParaElTruco cantosEnProcesoParaElTruco;
	private Jugador jugadorUno;
	private Jugador jugadorDos;

	@Before
	public void setUp(){
		this.jugadorUno = new Jugador();
		this.jugadorDos = new Jugador();
		this.cantosEnProcesoParaElTruco = new CantosEnProcesoParaElTruco();
	}

	
	@Test
	public void testGanarElTrucoImplicaDosPuntos() {
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.quiero(this.jugadorDos);
		
		assertEquals(2,this.cantosEnProcesoParaElTruco.puntosParaElGanador() );
	}
	
	@Test
	public void testGanarElRetrucoImplicaTresPuntos() {
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.retruco(this.jugadorDos);
		this.cantosEnProcesoParaElTruco.quiero(this.jugadorUno);
		
		assertEquals(3,this.cantosEnProcesoParaElTruco.puntosParaElGanador() );
	}
	
	@Test
	public void testGanarElValeCuatroImplicaCuatroPuntos() {
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.retruco(this.jugadorDos);
		this.cantosEnProcesoParaElTruco.valeCuatro(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.quiero(this.jugadorDos);
		
		assertEquals(4,this.cantosEnProcesoParaElTruco.puntosParaElGanador() );
	}
	
	
	@Test
	public void testNoQuieroAlTrucoImplicaQueElOtroJugadorGanaUnPunto() {
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.noQuiero(this.jugadorDos);
		
		assertEquals(1,this.cantosEnProcesoParaElTruco.puntosParaElGanador() );
	}
	
	@Test
	public void testNoQuieroAlRetrucoImplicaQueElOtroJugadorGanaDosPuntos() {
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.retruco(this.jugadorDos);
		this.cantosEnProcesoParaElTruco.noQuiero(this.jugadorUno);
		
		assertEquals(2,this.cantosEnProcesoParaElTruco.puntosParaElGanador() );
	}
	
	@Test
	public void testNoQuieroAlValeCuatroImplicaQueElOtroJugadorGanaTresPunto() {
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.retruco(this.jugadorDos);
		this.cantosEnProcesoParaElTruco.valeCuatro(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.noQuiero(this.jugadorDos);
		
		assertEquals(3,this.cantosEnProcesoParaElTruco.puntosParaElGanador() );
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarRetrucoSinHaberCantadoTrucoPrimero(){
		this.cantosEnProcesoParaElTruco.retruco(this.jugadorDos);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarValeCuatroSinHaberCantadoTrucoPrimero(){
		this.cantosEnProcesoParaElTruco.valeCuatro(this.jugadorDos);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarValeCuatroSinHaberCantadoRetrucoPrimero(){
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.valeCuatro(this.jugadorDos);
	}
	
	@Test
	public void testAlCantarNoQuieroAUnTrucoElJugadorQueCantoEsElGanador(){
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.noQuiero(this.jugadorDos);
		
		assertEquals(this.jugadorUno,this.cantosEnProcesoParaElTruco.jugadorGanador() );
	}
	
	@Test
	public void testAlCantarQuieroAUnTrucoNoSePuedeDeterminarUnGanador(){
		this.cantosEnProcesoParaElTruco.truco(this.jugadorUno);
		this.cantosEnProcesoParaElTruco.quiero(this.jugadorDos);
		
		assertNull(this.cantosEnProcesoParaElTruco.jugadorGanador() );
	}

}
