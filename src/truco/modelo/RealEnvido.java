package truco.modelo;

public class RealEnvido extends Canto {
	
	public RealEnvido(){
		this.cantosValidosDeRespuesta.add(this);
		this.cantosValidosDeRespuesta.add( new FaltaEnvido() );
	}

	@Override
	public int puntosPorGanar() {
		return 3;
	}

}
