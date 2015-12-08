package truco.vista;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VistaMesa {
	
	
	private HBox cartasJugadorUno;
	private HBox cartasJugadorDos;
	private VBox cartasMesa;

	public VistaMesa(){
		this.iniciarCajas();
	}
	
	private void iniciarCajas() {
		this.cartasJugadorUno = this.iniciarCajaHorizontal();
		this.cartasJugadorDos = this.iniciarCajaHorizontal();
		this.iniciarCajaVertical();
	}

	private HBox iniciarCajaHorizontal(){
		HBox cajaARetornar = new HBox();
		cajaARetornar.setAlignment(Pos.CENTER);
		cajaARetornar.setMaxSize(100, 60);
		cajaARetornar.setSpacing(-5);
		this.iniciarCartas(cajaARetornar);
		return cajaARetornar;
	}
	
	private void iniciarCartas(HBox cajaARetornar) {
		cajaARetornar.getChildren().add(this.iniciarImageView());
		cajaARetornar.getChildren().add(this.iniciarImageView());
		cajaARetornar.getChildren().add(this.iniciarImageView());
	}

	private void iniciarCajaVertical(){
		this.cartasMesa = new VBox();
		cartasMesa.setAlignment(Pos.CENTER);
		this.cartasMesa.getChildren().add(this.cartasJugadorUno);
		this.cartasMesa.getChildren().add(this.cartasJugadorDos);
	}
	
	
	private ImageView iniciarImageView(){
		ImageView cartaJugada = new ImageView();
		cartaJugada.setFitWidth(30);
		cartaJugada.setPreserveRatio(true);
		return cartaJugada;
	}

	
	public Node obtenerVista() {
		return this.cartasMesa;
	}
  
	

}
