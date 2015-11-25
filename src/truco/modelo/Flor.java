package truco.modelo;

public class Flor extends Canto {

	public Flor(Jugador jugadorQueCanta){
		super(jugadorQueCanta);
		this.cantosValidosDeRespuesta.add(this);
		this.cantosValidosDeRespuesta.add( new ContraFlor(null) );
		this.cantosValidosDeRespuesta.add( new ContraFlorAResto(null) );
	}
	
	
	@Override
	public int puntosPorGanar(Jugador jugadorGanador) {
		return 3;
	}

}
