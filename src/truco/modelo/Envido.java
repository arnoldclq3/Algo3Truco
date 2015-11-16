package truco.modelo;

public class Envido extends Canto {

	public Envido(){
		this.cantosValidosDeRespuesta.add(this);
		this.cantosValidosDeRespuesta.add( new RealEnvido() );
		this.cantosValidosDeRespuesta.add( new FaltaEnvido() );
	}

	@Override
	public int puntosPorGanar() {
		return 2;
	}
}
