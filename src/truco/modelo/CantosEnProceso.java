package truco.modelo;

import java.util.LinkedList;
import java.util.List;

public abstract class CantosEnProceso implements CantosGenerales{

	protected LinkedList<Canto> cantosAceptados;
	protected Jugador jugadorGanadorDelProceso;

	public CantosEnProceso() {
		this.cantosAceptados = new LinkedList<Canto>();
		this.jugadorGanadorDelProceso = null;
	}
	
	@Override
	public abstract void quiero(Jugador jugadorQueCanta);
	
	@Override
	public void noQuiero(Jugador jugadorQueCanta){
		this.cantosAceptados.removeLast();
	}
	
	public abstract int puntosParaElGanador();
	
	public int puntosParaElPerdedor(){
		// Por default siempre el que pierde no gana puntos.
		// Se desarrolla el metodo por si existen casos como Flor+Flor+Quiero
		return 0;
	}
	
	public abstract Jugador jugadorGanador();
	
	public List<Canto> getCantosRealizados(){
		return this.cantosAceptados;
	}
	
	public boolean seRealizoAlgunCanto(){
		return ( ! this.cantosAceptados.isEmpty() );
	}
}
