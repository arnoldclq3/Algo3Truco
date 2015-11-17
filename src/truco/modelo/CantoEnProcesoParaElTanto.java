package truco.modelo;

import truco.excepciones.canto.NoSePuedeCantarContraFlorSinHaberCantadoFlorPrimeroException;
import truco.excepciones.canto.NoSePuedeCantarElTantoDeLaFlorAntesDeAceptarUnCantoDeFormaPreviaException;
import truco.excepciones.canto.NoSePuedeCantarElTantoDelEnvidoAntesDeAceptarUnCantoDeFormaPreviaException;
import truco.excepciones.mesa.AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException;
import truco.excepciones.mesa.RespuestaIncorrectaException;

public class CantoEnProcesoParaElTanto extends CantosEnProceso implements CantosEnvido , CantosFlor{

	private boolean seCantoFlor;
	private boolean sePuedenRealizarCantosNuevos;
	private int cantidadDeJugadoresQueCantaronSuTanto;

	public CantoEnProcesoParaElTanto() {
		this.seCantoFlor = false;
		this.sePuedenRealizarCantosNuevos = true;
		this.cantidadDeJugadoresQueCantaronSuTanto = 0;
	}


	@Override
	public int puntosParaElGanador() {
		int puntaje = 0;
		if (seCantoFlor)
			puntaje = this.puntosParaElGanadorPorFlor();
		else
			puntaje = this.puntosParaElGanadorPorEnvido();
		if (puntaje == 0)
			return 1;
		return puntaje;
	}

	private int puntosParaElGanadorPorEnvido(){
		// Tener en cuenta que si el ultimo aceptado fue un Falta Envido igual recorre toda la lista.
		int puntajeGanador = 0;
		for (Canto unCanto : this.cantosAceptados)
			puntajeGanador += unCanto.puntosPorGanar();
		return puntajeGanador;
	}
	
	private int puntosParaElGanadorPorFlor(){
		if ( this.cantosAceptados.isEmpty() )
			return 0;
		return this.cantosAceptados.getLast().puntosPorGanar();
	}
	
	@Override
	public void quiero(Jugador jugadorQueCanta) {
		// Despues de un quiero no se pueden agregar mas cantos en el proceso
		this.sePuedenRealizarCantosNuevos = false;
	}

	public boolean terminoElProcesoDeCanto(int cantidadJugadoresQueDeberianHaberCantadoSuTanto){
		if ( cantidadJugadoresQueDeberianHaberCantadoSuTanto == this.cantidadDeJugadoresQueCantaronSuTanto )
			return true;
		return false;
	}

	private void verificacionDeCantoRepetido(Canto elCantoABuscar,int maximaVecesQuePuedeEstarElCanto){
		/*	Post:	Si el canto pasado se encuentra mas de 1 vez se lanza Excepcion
		 * 			Excepcion = RespuestaIncorrecta */
		int cantidadDeVecesQueEstaElCantoABuscar = 0;
		for (Canto unCanto : this.cantosAceptados)
			if (unCanto.equals(elCantoABuscar))
				cantidadDeVecesQueEstaElCantoABuscar += 1;
		if (cantidadDeVecesQueEstaElCantoABuscar > maximaVecesQuePuedeEstarElCanto)
			throw new RespuestaIncorrectaException();	
	}
	
	private void verificacionDeRespuestaCorrecta(Canto elCantoRespuesta){
		if (!this.cantosAceptados.isEmpty() && !this.cantosAceptados.getLast().esUnaRespuestaValidaElCanto(elCantoRespuesta) )
			throw new RespuestaIncorrectaException();
	}
	
	private void verificacionDeNoHaberCantadoFlorAntesDeUnEnvido(){
		// Falta verificar con ContraFlor y ContraFlorAResto
		if ( this.seCantoFlor )
			throw new AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException();
	}
	
	private void verificacionesGeneralesDelTanto(Canto unCanto,int maximaVecesQuePuedeEstarElCanto){
		if ( ! this.sePuedenRealizarCantosNuevos )
			throw new RespuestaIncorrectaException();
		// 1º) Se verifica que no se supere la maxima veces que puede estar el canto
		this.verificacionDeCantoRepetido(unCanto,maximaVecesQuePuedeEstarElCanto);
		// 2º) Se verifica que el ultimo canto tenga como canto de respuesta valida el canto.
		this.verificacionDeRespuestaCorrecta(unCanto);	
	}
	
	private void agregarCanto(Canto unCanto , Jugador jugadorQueLoCanto){
		this.cantosAceptados.addLast(unCanto);
		// Guardo temporalmente como ganador al jugador que esta cantando
		this.jugadorGanadorDelProceso = jugadorQueLoCanto;
	}
	
	private void verificarQueSeHayaCantadoFlorPreviamente(){
		if ( ! this.seCantoFlor )
			throw new NoSePuedeCantarContraFlorSinHaberCantadoFlorPrimeroException();
	}
	
	private void verificarQueSePuedeCantarElTantoDelEnvido(){
		if (this.sePuedenRealizarCantosNuevos && this.cantosAceptados.isEmpty() )
			throw new NoSePuedeCantarElTantoDelEnvidoAntesDeAceptarUnCantoDeFormaPreviaException();
	}
	
	private void verificarQueSePuedeCantarElTantoDeLaFlor(){
		if (this.sePuedenRealizarCantosNuevos && this.cantosAceptados.isEmpty() )
			throw new NoSePuedeCantarElTantoDeLaFlorAntesDeAceptarUnCantoDeFormaPreviaException();	
	}
	
	
	/**	Cantos Envido **/
	
	@Override
	public void envido(Jugador jugadorQueCanta) {
		Canto envido = new Envido();
		this.verificacionDeNoHaberCantadoFlorAntesDeUnEnvido();
		this.verificacionesGeneralesDelTanto(envido, 1);
		
		this.agregarCanto(envido, jugadorQueCanta);
	}

	@Override
	public void realEnvido(Jugador jugadorQueCanta) {
		Canto realEnvido = new RealEnvido();
		this.verificacionDeNoHaberCantadoFlorAntesDeUnEnvido();
		this.verificacionesGeneralesDelTanto(realEnvido, 1);
		
		this.agregarCanto(realEnvido, jugadorQueCanta);
	}

	@Override
	public void faltaEnvido(Jugador jugadorQueCanta) {
		Canto faltaEnvido = new FaltaEnvido();
		this.verificacionDeNoHaberCantadoFlorAntesDeUnEnvido();
		this.verificacionesGeneralesDelTanto(faltaEnvido, 0);
		
		this.agregarCanto(faltaEnvido, jugadorQueCanta);
	}
	

	@Override
	public void cantarTantoDelEnvido(Jugador jugadorQueCanta) {
		this.verificarQueSePuedeCantarElTantoDelEnvido();
		this.cantidadDeJugadoresQueCantaronSuTanto += 1;
		if ( this.jugadorGanadorDelProceso.equals(jugadorQueCanta) )
			return;
		if ( this.jugadorGanadorDelProceso.puntajeEnvido() > jugadorQueCanta.puntajeEnvido() )
			this.jugadorGanadorDelProceso = jugadorQueCanta;
	}

	/**	Cantos Flor **/
	
	@Override
	public void flor(Jugador jugadorQueCanta) {
		Canto flor = new Flor();
		this.verificacionesGeneralesDelTanto(flor, 1);
		
		this.agregarCanto(flor, jugadorQueCanta);
		this.seCantoFlor = true;
	}

	@Override
	public void contraFlor(Jugador jugadorQueCanta) {
		Canto contraFlor = new ContraFlor();
		this.verificarQueSeHayaCantadoFlorPreviamente();
		this.verificacionesGeneralesDelTanto(contraFlor, 0);
		
		this.agregarCanto(contraFlor, jugadorQueCanta);
		
	}

	@Override
	public void contraFlorAResto(Jugador jugadorQueCanta) {
		Canto contraFlorAResto = new ContraFlorAResto();
		this.verificarQueSeHayaCantadoFlorPreviamente();
		this.verificacionesGeneralesDelTanto(contraFlorAResto, 0);
		
		this.agregarCanto(contraFlorAResto, jugadorQueCanta);
		
	}


	@Override
	public void cantarTantoDeLaFlor(Jugador jugadorQueCanta) {
		this.verificarQueSePuedeCantarElTantoDeLaFlor();
		this.cantidadDeJugadoresQueCantaronSuTanto += 1;
		if ( this.jugadorGanadorDelProceso.equals(jugadorQueCanta) )
			return;
		if ( this.jugadorGanadorDelProceso.puntajeFlor() > jugadorQueCanta.puntajeFlor() )
			this.jugadorGanadorDelProceso = jugadorQueCanta;
	}



	/*	Casos que faltan verificar:
	 *  	Envido + Flor (Es posible)
	 *  	Flor + Flor + Quiero (El tema de los puntajes)
	 */
}
