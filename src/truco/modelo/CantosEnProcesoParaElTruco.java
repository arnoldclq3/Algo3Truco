package truco.modelo;

import truco.excepciones.cantos.RespuestaIncorrectaException;

public class CantosEnProcesoParaElTruco extends CantosEnProceso implements CantosTruco{

	private boolean existeGanador = false;
	
	/*************************************************
	 **  Sobrecarga de Metodos en CantosEnProceso	**
	 *************************************************/
	
	@Override
	public void quiero(Jugador jugadorQueCanta) {
		// En el truco, aceptar no implica mas nada que seguir jugando
		return;
	}
	
	@Override
	public void noQuiero(Jugador jugadorQueCanta){
		super.noQuiero(jugadorQueCanta);
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
		this.verificarQueNoExisteCantoPrevio();
		
		this.agregarCanto(truco,jugadorQueCanta);
	}

	@Override
	public void retruco(Jugador jugadorQueCanta) {
		Retruco reTruco = new Retruco(jugadorQueCanta);
		this.verificarCantoValido(reTruco);
		
		this.agregarCanto(reTruco,jugadorQueCanta);
	}


	@Override
	public void valeCuatro(Jugador jugadorQueCanta) {
		ValeCuatro valeCuatro = new ValeCuatro(jugadorQueCanta);
		this.verificarCantoValido(valeCuatro);
		
		this.agregarCanto(valeCuatro,jugadorQueCanta);	
	}

}
