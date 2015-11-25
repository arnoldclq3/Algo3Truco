package truco.modelo;

public class Envido extends Canto {

	public Envido(Jugador jugadorQueCanta){
		super(jugadorQueCanta);
		this.cantosValidosDeRespuesta.add(this);
		this.cantosValidosDeRespuesta.add( new RealEnvido(null) );
		this.cantosValidosDeRespuesta.add( new FaltaEnvido(null) );
	}

	@Override
	public int puntosPorGanar(Jugador jugadorGanador) {
		return 2;
	}
}
