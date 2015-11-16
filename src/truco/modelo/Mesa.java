package truco.modelo;

import java.util.ArrayList;
import java.util.LinkedList;

import truco.excepciones.mesa.NoSePuedeCantarTantoDosVecesEnUnaRonda;
import truco.excepciones.mesa.RespuestaInconrrecta;

public class Mesa {

	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private ArrayList<Jugador> jugadores;
	private Ronda ronda;
	private LinkedList<Canto> listadoCantosDelTanto;
	private Jugador jugadorConMayorTanto;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	
	public Mesa(Jugador primerJugador, Jugador segundoJugador) {
		this.jugadores = new ArrayList<Jugador>();
		this.jugadores.add(primerJugador);
		this.jugadores.add(segundoJugador);
		this.ronda = new Ronda();
		this.listadoCantosDelTanto = null;
	}
	
	/*************************************************
	 ** 			  Metodos Publicos				**
	 *************************************************/
	public int cantidadDeJugadores() {
		return this.jugadores.size();
	}
	
	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
		
		this.ronda.jugarCarta(unJugador,unaCarta);
	}
			
	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
				
		return ( this.ronda.mostrarUltimaCartaJugadaPor(unJugador) ); 
	}
	
	public void cantarEnvido(Jugador unJugador) {
		Canto envido = new Envido();
		this.procesoDelCantoDelEnvido(envido);
	}
	
	public void cantarRealEnvido(Jugador unJugador) {
		Canto realEnvido = new RealEnvido();
		this.procesoDelCantoDelEnvido(realEnvido);

	}
	
	private void procesoDelCantoDelEnvido(Canto unCanto){
		// Si la lista ya fue creada y esta vacia implica que se canto el tanto
		if (this.listadoCantosDelTanto != null && this.listadoCantosDelTanto.isEmpty() )
			throw new NoSePuedeCantarTantoDosVecesEnUnaRonda();
		if (this.listadoCantosDelTanto == null)
			this.listadoCantosDelTanto = new LinkedList<Canto>();
		// Si existe un canto debo verificar que tengo una respuesta valida para dicho canto
		if (!this.listadoCantosDelTanto.isEmpty() && !this.listadoCantosDelTanto.getLast().esUnaRespuestaValidaElCanto(unCanto))
			throw new RespuestaInconrrecta();
		this.listadoCantosDelTanto.addLast(unCanto);
	}
	
	public void cantarFlor(Jugador unJugador) {
		if (this.listadoCantosDelTanto != null)
			throw new NoSePuedeCantarTantoDosVecesEnUnaRonda();
		this.listadoCantosDelTanto = new LinkedList<Canto>();
		Canto flor = new Flor();
		this.listadoCantosDelTanto.addLast(flor);	
	}


	public void cantarQuiero(Jugador unJugador) {
		this.jugadorConMayorTanto = null;
	}

	public void cantarTantoDeEnvido(Jugador unJugador) {
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeEnvido() < unJugador.puntajeEnvido() )
			this.jugadorConMayorTanto = unJugador;
		// Por ahora vacio la lista, pero deberia hacer el caculo cuando cantaron todos los jugadores
		this.listadoCantosDelTanto.clear();
	}

	public Jugador ganadorDelTantoDeLaRondaActual() {
		return this.jugadorConMayorTanto;
	}


	public void cantarTantoDeFlor(Jugador unJugador) {
		if (this.jugadorConMayorTanto == null){
			this.jugadorConMayorTanto = unJugador;
			return;
		}
		if (this.jugadorConMayorTanto.puntajeFlor() < unJugador.puntajeFlor() )
			this.jugadorConMayorTanto = unJugador;	
		// Por ahora vacio la lista, pero deberia hacer el caculo cuando cantaron todos los jugadores
		this.listadoCantosDelTanto.clear();
	}

	
	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
