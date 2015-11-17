package truco.modelo;

import java.util.LinkedList;

public abstract class Canto {
	
	protected LinkedList<Canto> cantosValidosDeRespuesta;
	
	public Canto(){
		this.cantosValidosDeRespuesta = new LinkedList<Canto>();
	}

	public boolean esUnaRespuestaValidaElCanto(Canto unCanto) {
		return this.cantosValidosDeRespuesta.contains(unCanto);
	}
	
	public boolean equals(Object objeto){
		return ( this.getClass() == objeto.getClass());
	}

	public abstract int puntosPorGanar();


}
