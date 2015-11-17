package truco.modelo;

public class Flor extends Canto {

	public Flor(){
		this.cantosValidosDeRespuesta.add(this);
		this.cantosValidosDeRespuesta.add( new ContraFlor() );
		this.cantosValidosDeRespuesta.add( new ContraFlorAResto() );
	}
	
	
	@Override
	public int puntosPorGanar() {
		return 3;
	}

}
