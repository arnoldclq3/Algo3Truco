package truco.modelo;

import truco.excepciones.cantos.RespuestaIncorrectaException;

public class CantosEnProcesoParaElTruco extends CantosEnProceso implements CantosTruco{

	private boolean existeGanador = false;
	private boolean trucoEnProceso = false;
	private boolean seAceptaronLosCantos;

	
	/*************************************************
	 **  Sobrecarga de Metodos en CantosEnProceso	**
	 *************************************************/
	
	@Override
	public void quiero(Jugador jugadorQueCanta) {

		this.trucoEnProceso = false;
		this.seAceptaronLosCantos = true;
		return;
	}
	
	@Override
	public void noQuiero(Jugador jugadorQueCanta){
		super.noQuiero(jugadorQueCanta);
		this.seAceptaronLosCantos = false;
		this.existeGanador = true;
	}

	@Override
	public int puntosParaElGanador() {
		if (this.cantosAceptados.isEmpty())
			return 1;
		return this.cantosAceptados.getLast().puntosPorGanar(null);
	}

	@Override
	public Jugador jugadorGanador(){
		if (this.existeGanador)
			return this.jugadorGanadorDelProceso;
		return null;
	}

	/*************************************************
	 ** 			 VERIFICACIONES					**
	 *************************************************/
	
	private void verificarCantoValido(Canto unCanto) {
		if (this.cantosAceptados.isEmpty() || !this.cantosAceptados.getLast().esUnaRespuestaValidaElCanto(unCanto) )
			throw new RespuestaIncorrectaException(); 
	}
	
	private void verificarQueNoExisteCantoPrevio(){
		if ( ! this.cantosAceptados.isEmpty() )
			throw new RespuestaIncorrectaException(); 
	}
	
	private void agregarCanto(Canto unCanto,Jugador jugadorQueCanta){
		this.cantosAceptados.addLast(unCanto);
		this.jugadorGanadorDelProceso = jugadorQueCanta;
	}
	
	/*************************************************
	 **       Implementacion Cantos Truco			**
	 *************************************************/
	
	@Override
	public void truco(Jugador jugadorQueCanta) {
		Truco truco = new Truco(jugadorQueCanta);
		this.seAceptaronLosCantos = false;
		this.verificarQueNoExisteCantoPrevio();
		
		this.agregarCanto(truco,jugadorQueCanta);
		this.trucoEnProceso = true;
	}

	@Override
	public void retruco(Jugador jugadorQueCanta) {
		Retruco reTruco = new Retruco(jugadorQueCanta);
		this.seAceptaronLosCantos = false;
		this.verificarCantoValido(reTruco);
		
		this.agregarCanto(reTruco,jugadorQueCanta);
		this.trucoEnProceso = true;
	}


	@Override
	public void valeCuatro(Jugador jugadorQueCanta) {
		ValeCuatro valeCuatro = new ValeCuatro(jugadorQueCanta);
		this.seAceptaronLosCantos = false;
		this.verificarCantoValido(valeCuatro);
		
		this.agregarCanto(valeCuatro,jugadorQueCanta);
		this.trucoEnProceso = true;
	}


	
	/*************************************************
	 **       			GETTER						**
	 *************************************************/
	
	public boolean seAceptaronLosCantos(){
		return this.seAceptaronLosCantos;
	}
	
	public boolean hayTrucoEnProceso() {
		return this.trucoEnProceso;
	}

	public Jugador obtenerJugadorQueRealizoElUltimoCanto() {
		if (this.cantosAceptados.isEmpty())
			return null;
		return this.cantosAceptados.getLast().jugadorQueRealizoElCanto();
	}

}
