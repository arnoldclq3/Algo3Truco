package truco.modelo;

import java.util.ArrayList;
import java.util.List;

public class EscalaDeCartas {

	private List<String> escala;
	
	public EscalaDeCartas() {
		
		this.escala = new ArrayList<String>();
		this.establecerEscala();
	}
	
	public int enfrentarCartas(Carta carta1, Carta carta2) {
		
		String nombre1 = carta1.getNombre();
		String nombre2 = carta2.getNombre();
		
		int valor1=0;
		int valor2=0;
		int i = 0;
	
		for(String valor : escala){
			
			if (valor.matches("(.*)"+nombre1+"(.*)")) {
				valor1 = i;
			}
			
			if (valor.matches("(.*)"+nombre2+"(.*)")) {
				valor2 = i;
			}
			
			i++;
		}
		
		if ( valor1 < valor2 ){ return 1;}
		if ( valor1 > valor2 ){ return -1;}
		
		return 0;
	}
	
	private void establecerEscala() {
		
		escala.add("anchoespada");
		
		escala.add("anchobasto");

		escala.add("7espada");

		escala.add("7oro");

		escala.add("3espada 3basto 3oro 3copa");

		escala.add("2espada 2basto 2oro 2copa");
	
		escala.add("anchooro anchocopa");
		
		escala.add("12espada 12basto 12oro 12copa");
		
		escala.add("11espada 11basto 11oro 11copa");
		
		escala.add("10espada 10basto 10oro 10copa");
		
		escala.add("7basto 7copa");
		
		escala.add("6espada 6basto 6oro 6copa");
		
		escala.add("5espada 5basto 5oro 5copa");
		
		escala.add("4espada 4basto 4oro 4copa");
	}
}
