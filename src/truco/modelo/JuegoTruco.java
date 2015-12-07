package truco.modelo;

import truco.excepciones.juego.ElJuegoConfiguradoNoPoseeTodosLosJugadoresQueDeberiaException;
import truco.excepciones.juego.UnJuegoYaIniciadoNoPuedeConfigurarseException;

public class JuegoTruco {
	
	private boolean juegoIniciado;
	private Mesa mesaEnJuego;
	
	private Equipo nosotros;
	private Equipo ellos;
	
	private boolean seJuegaConFlor;
	private int cantidadJugadores;
	private GeneradorRondas generadorDeRondas;


	public JuegoTruco(){
		this.juegoIniciado = false;
		this.nosotros = new Equipo();
		this.ellos = new Equipo();
		
		// El Juego por Default es Sin Flor y de Dos Jugadores
		this.jugarSinFlor();
		this.juegoDeDosJugadores();
	}
	
	/*************************************************
	 ** 		Agregar Jugadores al Juego			**
	 *************************************************/
	
	public boolean agregarJugadorEnEquipoNosotros(Jugador unJugador){
		return this.agregarJugador( this.nosotros, unJugador);
	}
	
	public boolean agregarJugadorEnEquipoEllos(Jugador unJugador){
		return this.agregarJugador( this.ellos, unJugador);
	}
	
	private boolean agregarJugador(Equipo unEquipo, Jugador unJugador){
		this.verificarQueElJuegoNoHaIniciado();
		if ( unEquipo.estaJugador(unJugador) )
			return false;
		unEquipo.agregarJugador(unJugador);
		return true;
	}
	
	/*************************************************
	 ** 			Configuracion Juego				**
	 *************************************************/
	
	public void jugarConFlor(){
		this.configurarFlor(true);
	}
	
	public void jugarSinFlor(){
		this.configurarFlor(false);
	}
	
	private void configurarFlor(boolean seJuegaConFlor){
		this.verificarQueElJuegoNoHaIniciado();
		this.seJuegaConFlor = seJuegaConFlor;
	}
	
	public void juegoDeDosJugadores(){
		this.juegoConJugadores(2, new GeneradorRondasNormales() );
	}
	
	public void juegoDeCuatroJugadores(){
		this.juegoConJugadores(4, new GeneradorRondasNormales() );
	}
	
	public void juegoDeSeisJugadores(){
		this.juegoConJugadores(6, new GeneradorRondasPicaPica() );
	}
	
	private void juegoConJugadores(int cantidadJugadores, GeneradorRondas generadorDeRonda){
		this.verificarQueElJuegoNoHaIniciado();
		this.cantidadJugadores = cantidadJugadores;
		this.generadorDeRondas = generadorDeRonda;
	}
	
	/*************************************************
	 ** 			  Iniciar Juego					**
	 *************************************************/
	
	public Mesa iniciarJuego(){
		this.verificarQueElJuegoHaSidoConfiguradoCorrectamente();
		this.juegoIniciado = true;
		this.mesaEnJuego = new Mesa( this.nosotros, this.ellos, this.generadorDeRondas, this.seJuegaConFlor);
		return mesaEnJuego;
	}
	
	private void verificarQueElJuegoHaSidoConfiguradoCorrectamente() {
		this.verificarQueElJuegoNoHaIniciado();
		this.controlarQueLosEquiposEstenLlenos();
	}
	
	private void verificarQueElJuegoNoHaIniciado(){
		if (this.juegoIniciado)
			throw new UnJuegoYaIniciadoNoPuedeConfigurarseException();
	}

	private void controlarQueLosEquiposEstenLlenos() {
		int cantidadJugadoresActualmente = this.nosotros.cantidadJugadores() + this.ellos.cantidadJugadores();
		if ( cantidadJugadoresActualmente != this.cantidadJugadores &&
			 this.nosotros.cantidadJugadores() == this.ellos.cantidadJugadores() )
			throw new ElJuegoConfiguradoNoPoseeTodosLosJugadoresQueDeberiaException();
	}
	
	/*************************************************
	 ** 			Obtener Mesa en Juego			**
	 *************************************************/
	
	public Mesa obtenerMesaEnJuego(){
		return this.mesaEnJuego;
	}

}
