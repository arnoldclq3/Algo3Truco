package truco.vista;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VistaMensajeDelUltimoCantoRealizado implements Observer {

	private HBox cajaMuestreoDeCantos;

	public VistaMensajeDelUltimoCantoRealizado(){
		this.iniciarCajaDeMuestreoDeCantos();
		this.apagarLosMensajesDeCanto();
	}
	
	private HBox iniciarCajaHorizontal(){
		HBox cajaARetornar = new HBox();
		cajaARetornar.setAlignment(Pos.CENTER);
		cajaARetornar.setMaxSize(550, 40);
		cajaARetornar.setSpacing(2);
		return cajaARetornar;
	}
	
	private void iniciarCajaDeMuestreoDeCantos() {
		this.cajaMuestreoDeCantos = this.iniciarCajaHorizontal();
		Text canto = this.crearCajaDeTexto("");
		cajaMuestreoDeCantos.getChildren().add(canto);
	}
	
	private Text crearCajaDeTexto(String texto){
		Text caja = new Text(texto);
		caja.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
		caja.setTextAlignment(TextAlignment.CENTER);
		caja.setFill(Color.RED);
		return caja;
	}
	
	public HBox obtenerCajaMuestroDeCantos(){
		return this.cajaMuestreoDeCantos;
	}
	

	public void mostrarElMensajeDeCanto(String descripcion){
		Text textoDesc = (Text) this.cajaMuestreoDeCantos.getChildren().get(0);
		textoDesc.setVisible(true);
		textoDesc.setText(descripcion);
	}
	
	
	public void apagarLosMensajesDeCanto(){
		Text textoDesc = (Text) this.cajaMuestreoDeCantos.getChildren().get(0);
		textoDesc.setVisible(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		String cadena = (String) arg;
		if (cadena == null)
			this.apagarLosMensajesDeCanto();
		else
			this.mostrarElMensajeDeCanto( cadena );
	}
	
}
