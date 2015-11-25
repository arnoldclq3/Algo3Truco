package truco.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import truco.excepciones.cantos.AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException;
import truco.excepciones.cantos.NoSePuedeCantarContraFlorSinHaberCantadoFlorPrimeroException;
import truco.excepciones.cantos.NoSePuedeCantarElTantoDeLaFlorAntesDeAceptarUnCantoDeFormaPreviaException;
import truco.excepciones.cantos.NoSePuedeCantarElTantoDelEnvidoAntesDeAceptarUnCantoDeFormaPreviaException;
import truco.excepciones.cantos.RespuestaIncorrectaException;

public class CantosEnProcesoParaElTantoTest {


	private Jugador jugadorConFlorGanadorEnEnvido;
	private Jugador jugadorConFlorGanadorEnFlor;
	private Jugador jugadorManoConEnvidoMinimo;
	private Jugador jugadorPieConEnvidoMinimo;
	private CantoEnProcesoParaElTanto cantoEnProceso;
	private Equipo equipoGanadorEnvido;
	private Equipo equipoGanadorFlor;

	@Before
	public void setUp(){
		this.jugadorConFlorGanadorEnEnvido = new Jugador();
		this.jugadorConFlorGanadorEnFlor = new Jugador();
		this.jugadorManoConEnvidoMinimo = new Jugador();
		this.jugadorPieConEnvidoMinimo = new Jugador();
		
		// Envido = 33 , Flor = 34
		this.jugadorConFlorGanadorEnEnvido.tomarCarta( new Carta(7,Palo.ESPADA) );
		this.jugadorConFlorGanadorEnEnvido.tomarCarta( new Carta(6,Palo.ESPADA) );
		this.jugadorConFlorGanadorEnEnvido.tomarCarta( new Carta(1,Palo.ESPADA) );
		
		// Envido = 32 , Flor = 36
		this.jugadorConFlorGanadorEnFlor.tomarCarta( new Carta(7,Palo.BASTO) );
		this.jugadorConFlorGanadorEnFlor.tomarCarta( new Carta(5,Palo.BASTO) );
		this.jugadorConFlorGanadorEnFlor.tomarCarta( new Carta(4,Palo.BASTO) );
		
		// Envido = 20
		this.jugadorManoConEnvidoMinimo.tomarCarta( new Carta(10,Palo.ESPADA) );
		this.jugadorManoConEnvidoMinimo.tomarCarta( new Carta(12,Palo.ESPADA) );
		this.jugadorManoConEnvidoMinimo.tomarCarta( new Carta(4,Palo.COPA) );
		
		// Envido = 20
		this.jugadorPieConEnvidoMinimo.tomarCarta( new Carta(10,Palo.BASTO) );
		this.jugadorPieConEnvidoMinimo.tomarCarta( new Carta(12,Palo.BASTO) );
		this.jugadorPieConEnvidoMinimo.tomarCarta( new Carta(4,Palo.ORO) );
		
		this.equipoGanadorEnvido = new Equipo();
		equipoGanadorEnvido.agregarJugador(this.jugadorConFlorGanadorEnEnvido);
		equipoGanadorEnvido.agregarJugador(this.jugadorPieConEnvidoMinimo);
		this.equipoGanadorFlor = new Equipo();
		equipoGanadorFlor.agregarJugador(this.jugadorConFlorGanadorEnFlor);
		equipoGanadorFlor.agregarJugador(this.jugadorManoConEnvidoMinimo);
		
		this.cantoEnProceso = new CantoEnProcesoParaElTanto(equipoGanadorEnvido,equipoGanadorFlor);
		
	}

	@Test
	public void testElJugadorConMayorTantoDeEnvidoEsElGanadorDeUnEnvidoGanandoDosPuntos() {
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorManoConEnvidoMinimo);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorPieConEnvidoMinimo);
		
		assertTrue(this.cantoEnProceso.terminoElProcesoDeCanto(4) );
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 2);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testElJugadorConMayorTantoDeEnvidoEsElGanadorDeUnRealEnvidoGanandoTresPuntos() {
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorManoConEnvidoMinimo);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorPieConEnvidoMinimo);
		
		assertTrue(this.cantoEnProceso.terminoElProcesoDeCanto(4) );
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 3);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testElJugadorConMayorTantoDeEnvidoEsElGanadorDeUnFaltaEnvido() {
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorManoConEnvidoMinimo);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorPieConEnvidoMinimo);
		
		assertTrue(this.cantoEnProceso.terminoElProcesoDeCanto(4) );
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test 
	public void testAlGanarUnFaltaEnvidoSeObtieneElPuntajeQueLeFaltaParaGanarAlEquipoPerdedor() {
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnFlor);

		assertEquals(this.cantoEnProceso.puntosParaElGanador(),this.equipoGanadorEnvido.obtenerPuntosFaltantesParaGanar() );
	}
	
	@Test
	public void testElJugadorConMayorTantoDeFlorEsElGanadorDeUnaFlorGanandoTresPuntos() {
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnFlor);
		
		assertTrue(this.cantoEnProceso.terminoElProcesoDeCanto(2) );
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 3);
		assertEquals(this.jugadorConFlorGanadorEnFlor , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testElJugadorConMayorTantoDeFlorEsElGanadorDeUnaContraFlorGanandoSeisPuntos() {
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.contraFlor(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnFlor);
		
		assertTrue(this.cantoEnProceso.terminoElProcesoDeCanto(2) );
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 6);
		assertEquals(this.jugadorConFlorGanadorEnFlor , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testElJugadorConMayorTantoDeFlorEsElGanadorDeUnaContraFlorAResto() {
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.contraFlorAResto(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnFlor);
		
		assertTrue(this.cantoEnProceso.terminoElProcesoDeCanto(2) );
		assertEquals(this.jugadorConFlorGanadorEnFlor , this.cantoEnProceso.jugadorGanador() );
	}

	@Test
	public void testAlGanarUnaContraFlorARestoSeObtieneElPuntajeQueLeFaltaParaGanarAlEquipoPerdedor() {
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.contraFlorAResto(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnFlor);
		
		assertEquals(this.cantoEnProceso.puntosParaElGanador(),this.equipoGanadorFlor.obtenerPuntosFaltantesParaGanar() );
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarUnEnvidoAUnRealEnvido() {
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnEnvido);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarUnEnvidoAUnFaltaEnvido() {
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnEnvido);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarUnRealEnvidoAUnFaltaEnvido() {
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnEnvido);
	}
	
	@Test (expected = NoSePuedeCantarContraFlorSinHaberCantadoFlorPrimeroException.class)
	public void testNoSePuedeCantarContraFlorSinHaberCantadoFlorPrimero() {
		this.cantoEnProceso.contraFlor(jugadorConFlorGanadorEnFlor);
	}
	
	@Test (expected = NoSePuedeCantarContraFlorSinHaberCantadoFlorPrimeroException.class)
	public void testNoSePuedeCantarContraFlorARestoSinHaberCantadoFlorPrimero() {
		this.cantoEnProceso.contraFlorAResto(jugadorConFlorGanadorEnFlor);
	}
	
	@Test (expected = AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException.class)
	public void testNoSePuedeCantarEnvidoSiSeCantoFlor() {
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnEnvido);
	}
	
	@Test (expected = AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException.class)
	public void testNoSePuedeCantarFaltaEnvidoSiSeCantoFlor() {
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnEnvido);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarEnvidoTresVecesSeguidas(){
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnFlor);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarRealEnvidoTresVecesSeguidas(){
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnFlor);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarFaltaEnvidoDosVecesSeguidas(){
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnEnvido);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarFlorTresVecesSeguidas(){
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarContraFlorDosVecesSeguidas(){
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.contraFlor(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.contraFlor(jugadorConFlorGanadorEnFlor);
	}
	
	@Test (expected = RespuestaIncorrectaException.class)
	public void testNoSePuedeCantarContraFlorARestoDosVecesSeguidas(){
		this.cantoEnProceso.flor(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.contraFlorAResto(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.contraFlorAResto(jugadorConFlorGanadorEnFlor);
	}
	
	@Test
	public void testAlDecirNoQuieroAUnEnvidoGanaElJugadorQueNoLoCantoElPuntajeAcumuladoHastaElMomento() {
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.noQuiero(jugadorConFlorGanadorEnFlor);
		
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 10);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test (expected = NoSePuedeCantarElTantoDelEnvidoAntesDeAceptarUnCantoDeFormaPreviaException.class)
	public void testNoSePuedeCantarElTantoDeEnvidoSinHaberCantadoAlgunEnvidoDeFormaPrevia() {
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnEnvido);
	}
	
	@Test (expected = NoSePuedeCantarElTantoDeLaFlorAntesDeAceptarUnCantoDeFormaPreviaException.class)
	public void testNoSePuedeCantarElTantoDeLaFlorSinHaberCantadoAlMenosFlorDeFormaPrevia() {
		this.cantoEnProceso.cantarTantoDeLaFlor(this.jugadorConFlorGanadorEnFlor);
	}
	
	@Test
	public void testNoQuererUnEnvidoImplicaQueElJugadorQueCantoGanaUnPunto(){
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.noQuiero(jugadorConFlorGanadorEnFlor);
		
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 1);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testNoQuererUnRealEnvidoImplicaQueElJugadorQueCantoGanaUnPunto(){
		this.cantoEnProceso.realEnvido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.noQuiero(jugadorConFlorGanadorEnFlor);
		
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 1);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testNoQuererUnFaltaEnvidoImplicaQueElJugadorQueCantoGanaUnPunto(){
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.noQuiero(jugadorConFlorGanadorEnFlor);
		
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 1);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testCantarSonBuenasImplicaQueGanaElJugadorConMayorTanto(){
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.sonBuenas(this.jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.sonBuenas(this.jugadorManoConEnvidoMinimo);
		this.cantoEnProceso.sonBuenas(this.jugadorPieConEnvidoMinimo);
		
		assertTrue(this.cantoEnProceso.terminoElProcesoDeCanto(4) );
		assertEquals(this.cantoEnProceso.puntosParaElGanador() , 2);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
	
	@Test
	public void testAlCantarUnFaltaEnvidoElEquipoGanadorGanaCorrectamenteLosPuntosQueLeFaltanAlOtroEquipo() {
		// Sumo 10 Puntos al equipo perdedor para que el ganador gane 20 por el falta envido y no 22.
		this.equipoGanadorFlor.sumarPuntos(10);
		this.cantoEnProceso.envido(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.faltaEnvido(jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.quiero(jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnEnvido);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorConFlorGanadorEnFlor);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorManoConEnvidoMinimo);
		this.cantoEnProceso.cantarTantoDelEnvido(this.jugadorPieConEnvidoMinimo);
		
		assertTrue( this.cantoEnProceso.terminoElProcesoDeCanto(4) );
		assertEquals( this.cantoEnProceso.puntosParaElGanador() , 20);
		assertEquals(this.jugadorConFlorGanadorEnEnvido , this.cantoEnProceso.jugadorGanador() );
	}
}
