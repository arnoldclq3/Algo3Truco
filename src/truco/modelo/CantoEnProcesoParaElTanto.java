package truco.modelo;

import truco.excepciones.canto.NoSePuedeCantarContraFlorSinHaberCantadoFlorPrimeroException;
import truco.excepciones.mesa.AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException;
import truco.excepciones.mesa.RespuestaInconrrectaException;

public class CantoEnProcesoParaElTanto extends CantosEnProceso implements CantosEnvido , CantosFlor{

	private boolean seCantoFlor;

	public CantoEnProcesoParaElTanto() {
		this.seCantoFlor = false;
	}


	@Override
	public int puntosParaElGanador() {
		int puntajeGanador = 0;
		for (Canto unCanto : this.cantosAceptados)
			puntajeGanador += unCanto.puntosPorGanar();
		return puntajeGanador;
	}

	@Override
	public void quiero(Jugador jugadorQueCanta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noQuiero(Jugador jugadorQueCanta) {
		// TODO Debe revisar para el caso FLOR + FLOR

	}


	private void verificacionDeCantoRepetido(Canto elCantoABuscar,int maximaVecesQuePuedeEstarElCanto){
		/*	Post:	Si el canto pasado se encuentra mas de 1 vez se lanza Excepcion
		 * 			Excepcion = RespuestaIncorrecta */
		int cantidadDeVecesQueEstaElCantoABuscar = 0;
		for (Canto unCanto : this.cantosAceptados)
			if (unCanto.equals(elCantoABuscar))
				cantidadDeVecesQueEstaElCantoABuscar += 1;
		if (cantidadDeVecesQueEstaElCantoABuscar > maximaVecesQuePuedeEstarElCanto)
			throw new RespuestaInconrrectaException();	
	}
	
	private void verificacionDeRespuestaCorrecta(Canto elCantoRespuesta){
		if (! this.cantosAceptados.getLast().esUnaRespuestaValidaElCanto(elCantoRespuesta) )
			throw new RespuestaInconrrectaException();
	}
	
	private void verificacionDeNoHaberCantadoFlorAntesDeUnEnvido(){
		// Falta verificar con ContraFlor y ContraFlorAResto
		if ( this.seCantoFlor )
			throw new AlCantarFlorEnUnaRondaNoSePuedeCantarEnvidoException();
	}
	
	private void verificacionesGeneralesDelTanto(Canto unCanto,int maximaVecesQuePuedeEstarElCanto){
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
		// TODO Auto-generated method stub
		Canto faltaEnvido = new FaltaEnvido();
		this.verificacionDeNoHaberCantadoFlorAntesDeUnEnvido();
		this.verificacionesGeneralesDelTanto(faltaEnvido, 0);
		
		this.agregarCanto(faltaEnvido, jugadorQueCanta);
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

	/*	Casos que faltan verificar:
	 *  	Envido + Flor (Es posible)
	 *  	Flor + Flor + Quiero (El tema de los puntajes)
	 */
}
