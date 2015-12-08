package truco.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class VentanaJugador {
	
	private GridPane vistas;

	public VentanaJugador(Stage escenaPrincipal){
		this.iniciarGridVistas();
		this.iniciarVistasRelacionadasConLaRondaActual();
		this.iniciarVistaPuntaje();

		// Aca lo que tengo que hacer es vincular esta ventana con la MESA
		
		Scene escenaJugador = new Scene(vistas, 1050, 650, Color.BLACK);
		escenaPrincipal.setScene(escenaJugador);
	}
	
	private void iniciarVistaPuntaje() {
		// TODO Auto-generated method stub
		
	}

	private void iniciarGridVistas() {
		this.vistas = new GridPane();
		this.vistas.setAlignment(Pos.CENTER);
		this.vistas.setHgap(10);
		this.vistas.setVgap(10);
		this.vistas.setPadding( new Insets(25, 25, 25, 25) );
	}
	
	private void iniciarVistasRelacionadasConLaRondaActual() {
		this.iniciarBotonera();
		this.iniciarManoJugador();
		this.iniciarMesa();
	}

	private void iniciarBotonera() {
		VistaBotonera botonera = new VistaBotonera();
		this.vistas.add(botonera.obtenerVista(), 1, 1);
	}
	
	private void iniciarManoJugador(){
		VistaManoJugador manoJugador = new VistaManoJugador();
		this.vistas.add( manoJugador.obtenerVista(), 0, 1);
	}
	
	private void iniciarMesa(){
		VistaMesa mesa = new VistaMesa();
		this.vistas.add( mesa.obtenerVista(), 0, 0);
	}
	
	

}
