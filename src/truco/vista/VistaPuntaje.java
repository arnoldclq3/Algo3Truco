package truco.vista;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import truco.modelo.*;

public class VistaPuntaje implements Observer {

	private GridPane vistaPuntaje;
	private VBox nosotros;
	private VBox ellos;
	private Text puntajeNosotros;
	private Text puntajeEllos;
	private Text numeroRonda;
	private Jugador miJugador;
	private Ronda miRonda;
	private int numero;
	
	public VistaPuntaje(Jugador jugador) {
		
		this.miJugador = jugador;
		this.numero = 1;
		this.numeroRonda = this.nuevoTitulo("Ronda "+this.numero);
		this.nosotros = this.nuevaCajaVertical();
		this.ellos = this.nuevaCajaVertical();
		this.puntajeNosotros = this.nuevoTitulo("0");
		this.puntajeEllos = this.nuevoTitulo("0");
		this.vistaPuntaje = this.nuevoGridPane();
		this.agregarTitulos();
		this.agregarCajasVerticales();
	}
	
	private VBox nuevaCajaVertical() {
		
		VBox cajaVertical = new VBox();
		cajaVertical.setAlignment(Pos.TOP_CENTER);
		cajaVertical.setSpacing(10);
		cajaVertical.setPadding(new Insets(10, 10, 10, 10));
		
		return cajaVertical;
	}
	
	private GridPane nuevoGridPane() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		return grid;
	}
	
	private void agregarTitulos() {
		
		this.nosotros.getChildren().add(this.nuevoTitulo("NOSOTROS"));
		this.nosotros.getChildren().add(this.puntajeNosotros);
		this.ellos.getChildren().add(this.nuevoTitulo("ELLOS"));
		this.ellos.getChildren().add(this.puntajeEllos);
	}

	private void agregarCajasVerticales() {
		
		this.vistaPuntaje.add(this.numeroRonda, 0, 0);
		this.vistaPuntaje.add(nosotros, 0, 1);
		this.vistaPuntaje.add(ellos, 1, 1);
	}
	
	public Node obtenerVista() {
		
		return this.vistaPuntaje;
	}
	
	private Text nuevoTitulo(String string) {
		
		Text texto = new Text(string);
		texto.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		texto.setTextAlignment(TextAlignment.CENTER);
		texto.setFill(Color.WHITE);
        
        return texto;
	}

	@Override
	public void update(Observable o, Object arg) {
        
        Mesa laMesa = (Mesa)o;
        Equipo equipo1 = laMesa.getEquipo1();
        Equipo equipo2 = laMesa.getEquipo2();
        
        if ( this.miRonda == null ) {
        	this.miRonda = laMesa.getRondaActual();
        }
        
        if ( equipo1.estaJugador(miJugador) ) {
        	this.puntajeNosotros.setText(equipo1.obtenerCantidadDePuntos()+"");
        	this.puntajeEllos.setText(equipo2.obtenerCantidadDePuntos()+"");
        } else {
        	this.puntajeNosotros.setText(equipo2.obtenerCantidadDePuntos()+"");
        	this.puntajeEllos.setText(equipo1.obtenerCantidadDePuntos()+"");
        }  
        
        if ( this.miRonda.estaTerminada() ) {
        	this.miRonda = laMesa.getRondaActual();
        	this.numero++;
        	this.numeroRonda.setText("Ronda "+this.numero);
        }
	}
	
}
