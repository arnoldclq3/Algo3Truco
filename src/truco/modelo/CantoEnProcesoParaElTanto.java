package truco.modelo;

import truco.excepciones.cantos.AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException;
import truco.excepciones.cantos.NoSePuedeCantarContraFlorSinHaberCantadoFlorPrimeroException;
import truco.excepciones.cantos.NoSePuedeCantarElTantoDeLaFlorAntesDeAceptarUnCantoDeFormaPreviaException;
import truco.excepciones.cantos.NoSePuedeCantarElTantoDelEnvidoAntesDeAceptarUnCantoDeFormaPreviaException;
import truco.excepciones.cantos.NoSePuedeCantarTantoDosVecesEnUnaRondaException;
import truco.excepciones.cantos.RespuestaIncorrectaException;

public class CantoEnProcesoParaElTanto extends CantosEnProceso implements CantosEnvido , CantosFlor{

	private boolean seCantoFlor;
	private boolean sePuedenRealizarCantosNuevos;
	private int cantidadDeJugadoresQueCantaronSuTanto;

	public CantoEnProcesoParaElTanto() {
		this.seCantoFlor = false;
		this.sePuedenRealizarCantosNuevos = true;
		this.cantidadDeJugadoresQueCantaronSuTanto = 0;
	}
	
	/*************************************************
	 **  Sobrecarga de Metodos en CantosEnProceso	**
	 *************************************************/
	@Override
	public Jugador jugadorGanador() {
		return this.jugadorGanadorDelProceso;
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

	@Override
	public void quiero(Jugador jugadorQueCanta) {
		// Despues de un quiero no se pueden agregar mas cantos en el proceso
		this.sePuedenRealizarCantosNuevos = false;
	}
	
	@Override
	public void noQuiero(Jugador jugadorQueCanta){
		if (!this.seCantoFlor){
			super.noQuiero(jugadorQueCanta);
			return;
		}
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
	

	public boolean terminoElProcesoDeCanto(int cantidadJugadoresQueDeberianHaberCantadoSuTanto){
		if ( cantidadJugadoresQueDeberianHaberCantadoSuTanto == this.cantidadDeJugadoresQueCantaronSuTanto )
			return true;
		return false;
	}
	
	/*************************************************
	 ** 			 VERIFICACIONES					**
	 *************************************************/
	
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
	
	private void verificacionDeCantoDeTantoUnicoPorLaRonda() {
		if (!this.sePuedenRealizarCantosNuevos)
			throw new NoSePuedeCantarTantoDosVecesEnUnaRondaException();
	}

	private void verificacionesDelEnvido(Canto unCanto,int maximaVecesQuePuedeEstarElCanto){
		this.verificacionDeCantoDeTantoUnicoPorLaRonda();
		this.verificacionDeNoHaberCantadoFlorAntesDeUnEnvido();
		this.verificacionesGeneralesDelTanto(unCanto, maximaVecesQuePuedeEstarElCanto);
	}
	
	private void agregarCanto(Canto unCanto , Jugador jugadorQueLoCanto){
		this.cantosAceptados.addLast(unCanto);
		// Guardo temporalmente como ganador al jugador que esta cantando
		this.jugadorGanadorDelProceso = jugadorQueLoCanto;
	}
	
	/*************************************************
	 **       Implementacion Cantos Envido			**
	 *************************************************/
	
	@Override
	public void envido(Jugador jugadorQueCanta) {
		Canto envido = new Envido();
		this.verificacionesDelEnvido(envido, 1);
		
		this.agregarCanto(envido, jugadorQueCanta);
	}

	@Override
	public void realEnvido(Jugador jugadorQueCanta) {
		Canto realEnvido = new RealEnvido();
		this.verificacionesDelEnvido(realEnvido, 1);
		
		this.agregarCanto(realEnvido, jugadorQueCanta);
	}

	@Override
	public void faltaEnvido(Jugador jugadorQueCanta) {
		Canto faltaEnvido = new FaltaEnvido();
		this.verificacionesDelEnvido(faltaEnvido, 0);
		
		this.agregarCanto(faltaEnvido, jugadorQueCanta);
	}
	

	@Override
	public void cantarTantoDelEnvido(Jugador jugadorQueCanta) {
		this.verificarQueSePuedeCantarElTantoDelEnvido();
		this.cantidadDeJugadoresQueCantaronSuTanto += 1;
		if ( this.jugadorGanadorDelProceso.equals(jugadorQueCanta) )
			return;
		if ( jugadorQueCanta.puntajeEnvido() > this.jugadorGanadorDelProceso.puntajeEnvido()  )
			this.jugadorGanadorDelProceso = jugadorQueCanta;
	}
	
	@Override
	public void sonBuenas(Jugador jugadorQueCanta) {
		if (this.seCantoFlor)
			this.verificarQueSePuedeCantarElTantoDeLaFlor();
		else
			this.verificarQueSePuedeCantarElTantoDelEnvido();
		this.cantidadDeJugadoresQueCantaronSuTanto += 1;
	}

	/*************************************************
	 **       Implementacion Cantos Flor			**
	 *************************************************/
	
	@Override
	public void flor(Jugador jugadorQueCanta) {
		Canto flor = new Flor();
		this.verificacionDeCantoDeTantoUnicoPorLaRonda();
		this.verificacionesGeneralesDelTanto(flor, 1);
		
		this.agregarCanto(flor, jugadorQueCanta);
		this.seCantoFlor = true;
	}

	@Override
	public void contraFlor(Jugador jugadorQueCanta) {
		Canto contraFlor = new ContraFlor();
		this.verificacionDeCantoDeTantoUnicoPorLaRonda();
		this.verificarQueSeHayaCantadoFlorPreviamente();
		this.verificacionesGeneralesDelTanto(contraFlor, 0);
		
		this.agregarCanto(contraFlor, jugadorQueCanta);
		
	}

	@Override
	public void contraFlorAResto(Jugador jugadorQueCanta) {
		Canto contraFlorAResto = new ContraFlorAResto();
		this.verificacionDeCantoDeTantoUnicoPorLaRonda();
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
		if ( jugadorQueCanta.puntajeFlor() > this.jugadorGanadorDelProceso.puntajeFlor() )
			this.jugadorGanadorDelProceso = jugadorQueCanta;
	}


}
