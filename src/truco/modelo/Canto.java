package truco.modelo;

import java.util.LinkedList;
import java.util.List;

public abstract class Canto {
	
	protected LinkedList<Canto> cantosValidosDeRespuesta;
	private Jugador jugadorQueCanta;
	
	public Canto(Jugador jugadorQueCanta){
		this.cantosValidosDeRespuesta = new LinkedList<Canto>();
		this.jugadorQueCanta = jugadorQueCanta;
	}

	public boolean esUnaRespuestaValidaElCanto(Canto unCanto) {
		return this.cantosValidosDeRespuesta.contains(unCanto);
	}
	
	@Override
	public boolean equals(Object objeto){
		return ( this.getClass() == objeto.getClass());
	}
	
	@Override
	public int hashCode(){
		return  ( this.getClass().getSimpleName().hashCode() );
	}

	public abstract int puntosPorGanar(Jugador jugadorGanador);
	
	public Jugador jugadorQueRealizoElCanto(){
		return this.jugadorQueCanta;
	}

	public List<Canto> cantosValidosDeRespuesta(){
		return this.cantosValidosDeRespuesta;
	}

}
