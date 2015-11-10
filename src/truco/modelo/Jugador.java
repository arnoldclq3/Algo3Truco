package truco.modelo;

import java.util.ArrayList;

public class Jugador {
	
	// carta.mismoPalo

	private ArrayList<Carta> manoDelJugador;
	
	public Jugador(){
		this.manoDelJugador = new ArrayList<Carta>();
	}

	public void nuevaCarta(Carta unaCarta) {
		this.manoDelJugador.add(unaCarta);
	}

	
	public int puntajeEnvido() {
		int puntajeARetornar = 0;
		for (Carta unaCarta : this.manoDelJugador )
			for (Carta otraCarta : this.manoDelJugador){
				if (unaCarta != otraCarta){
					int valorEnvido = this.calcularEnvido(unaCarta,otraCarta);	
					if (valorEnvido > puntajeARetornar)
						puntajeARetornar = valorEnvido;	
				}
			}
		return puntajeARetornar;
	}


	private int calcularEnvido(Carta unaCarta, Carta otraCarta) {
		if (! unaCarta.mismoPalo(otraCarta) )
			return 0;
		int valorRetorno = 20;
		if ( unaCarta.getValor() < 8 )
			valorRetorno = valorRetorno + unaCarta.getValor();
		if ( otraCarta.getValor() < 8 )
			valorRetorno = valorRetorno + otraCarta.getValor();
		return valorRetorno;
	}
	
	

}
