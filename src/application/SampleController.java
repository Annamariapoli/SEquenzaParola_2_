package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	

	private Model m = new Model();
	
	public void setModel(Model m){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLung;

    @FXML
    private Button btnCarica;

    @FXML
    private TextField txtInizio;

    @FXML
    private TextField txtFine;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCarica(ActionEvent event) {
    	txtResult.clear();
    	try{
    		int lun = Integer.parseInt(txtLung.getText());
    		if(lun<=2){
    			txtResult.appendText("Lunghezza errata!\n");
    			return;
    		}
    		
    		int conta = m.getCon(lun);		
    		UndirectedGraph<String, DefaultEdge> grafo = m.buildGraph(lun);
    		int vert = m.numV(grafo);
    		int archi = m.numA(grafo);
    		List<String> nonragg = m.calcoloGradiVertici(grafo);
    		
    		
    		txtResult.appendText("Il numero delle parole è : "+conta+" \n");
    		txtResult.appendText("Il numero di vertici del grafo è : "+vert+" \n");
    		txtResult.appendText("Il numero di archi è : " +archi +" \n");
    		txtResult.appendText("Le parole non raggiungibili sono : "+nonragg +"\n");
    	
    	}catch(Exception e ){
    		txtResult.appendText("Il formato non è corretto!\n");
    		return;
    	}

    }

    @FXML
    void doCerca(ActionEvent event) {
    	String s1 = txtInizio.getText();
    	String s2 = txtFine.getText();
    	if(s1== null || s2 == null){
    		txtResult.appendText("Inserisci due parole!\n");
    		return;    		
    	}
    	if(s1.equals(s2)){
    		txtResult.appendText("Inserisci due parole diverse!\n");
    		return;
    	}
    	int lung1 = s1.length();
    	int lung2 = s2.length();
    	
    	int lun = Integer.parseInt(txtLung.getText());
    	if(lung1 != lung2){
    		txtResult.appendText("La lunghezza delle due parole deve essere uguale\n");
    		return;
    	}
    	if(lung1==lun && lung2 == lun){
    		if(lun >= 3){
    			List<String > cammino = m.getCammino(s1, s2);
    			if(cammino.size()==0){
    				txtResult.appendText("Il cammino non esiste!\n");
    				return;
    			}
    			else {
    				txtResult.appendText("Il cammino è : "+cammino.toString()+"\n");
    			}
    		}else {
    			txtResult.appendText("La lunghezza deve essere almeno 3!\n");
    			return;
    		}  		
    	}else {
    		txtResult.appendText("La lunghezza non è uguale a quella inserita!\n");
    		return;
    	}

    }

    @FXML
    void initialize() {
        assert txtLung != null : "fx:id=\"txtLung\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtInizio != null : "fx:id=\"txtInizio\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtFine != null : "fx:id=\"txtFine\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}
