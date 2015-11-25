package truco.modelo;

public class RealEnvido extends Canto {
	
	public RealEnvido(Jugador jugadorQueCanta){
		super(jugadorQueCanta);
		this.cantosValidosDeRespuesta.add(this);
		this.cantosValidosDeRespuesta.add( new FaltaEnvido(null) );
	}

	@Override
	public int puntosPorGanar(Jugador jugadorGanador) {
		return 3;
	}

}
