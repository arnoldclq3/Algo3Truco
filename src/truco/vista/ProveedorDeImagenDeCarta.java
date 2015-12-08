package truco.vista;

import java.util.HashMap;

import truco.modelo.Carta;
import truco.modelo.Palo;

import javafx.scene.image.Image;


public class ProveedorDeImagenDeCarta {
	
	private HashMap<Carta, Image> mapa;

	public ProveedorDeImagenDeCarta(){
		this.mapa = new HashMap<Carta, Image>();
		this.iniciarPalo(Palo.BASTO, "file:Imagenes/Basto/");
		this.iniciarPalo(Palo.COPA, "file:Imagenes/Copa/");
		this.iniciarPalo(Palo.ESPADA, "file:Imagenes/Espada/");
		this.iniciarPalo(Palo.ORO, "file:Imagenes/Oro/");
	}
	
	private void iniciarPalo(Palo unPalo , String carpetaDelPalo){
		this.iniciarPaloEnIntervalo(unPalo, carpetaDelPalo, 1, 7);
		this.iniciarPaloEnIntervalo(unPalo, carpetaDelPalo, 10, 12);
	}
	
	private void iniciarPaloEnIntervalo(Palo unPalo , String carpetaDelPalo , int valorInicial , int valorFinal){
		for (int valor = valorInicial ; valor <= valorFinal ; valor ++){
			Carta unaCarta = new Carta(valor,unPalo);
			String direccion = carpetaDelPalo + String.valueOf(valor) + ".jpg";
			this.mapa.put(unaCarta, new Image(direccion,200,200,true,true) );
		}
	}
	
	public Image obtenerImagenParaCarta(Carta unaCarta){
		return this.mapa.get(unaCarta);
	}
	
}
