package manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.RepMaxCalc;

public class CalcviewController implements Initializable {
	@FXML Spinner<Integer> weightSpinner;
	SpinnerValueFactory<Integer> weightlist = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
	@FXML ChoiceBox<Integer> repsChoiceBox;
	ObservableList<Integer> repslist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
	@FXML GridPane gp;
	@FXML Hyperlink goWikiforFormula;
	@FXML private void goWikiforFormula() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("webview.fxml"));
			Stage next = new Stage();
			next.setScene(new Scene(root));
			next.setAlwaysOnTop(true);
			next.show();
		} catch (Exception e) {}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RepMaxCalc rmc = new RepMaxCalc();
		weightSpinner.setValueFactory(weightlist);
		weightSpinner.setEditable(true);
		repsChoiceBox.setItems(repslist);
		repsChoiceBox.setValue(1);
		weightSpinner.valueProperty().addListener((obs, ov, nv)->{
			ArrayList<String[]> rm = rmc.rmCalc(nv, Double.parseDouble(repsChoiceBox.getValue()+""));
			if(repsChoiceBox.getValue()==1) {
				gp.getChildren().clear();
				for(int i = 0 ; i < rm.size() ; i++) {
					for(int j = 0 ; j < rm.get(i).length ; j++) {
						if(i == 1 && j==0) {
							gp.add(new Label("1RM"), j, i);
						}else if(i == 1 && j!=0) {gp.add(new Label(nv+""), j, i);
						}else{gp.add(new Label(rm.get(i)[j]), j, i);}
					}
				}
			} else {
				gp.getChildren().clear();
				for(int i = 0 ; i < rm.size() ; i++) {
					for(int j = 0 ; j < rm.get(i).length ; j++) {
						gp.add(new Label(rm.get(i)[j]), j, i);
					}
				}
			}
		});
		repsChoiceBox.valueProperty().addListener((obs, ov, nv)->{
			ArrayList<String[]> rm = rmc.rmCalc(Double.parseDouble(weightSpinner.getValue()+""), nv);
			if(nv==1) {
				gp.getChildren().clear();
				for(int i = 0 ; i < rm.size() ; i++) {
					for(int j = 0 ; j < rm.get(i).length ; j++) {
						if(i == 1 && j==0) {
							gp.add(new Label("1RM"), j, i);
						}else if(i == 1 && j!=0) {gp.add(new Label(weightSpinner.getValue()+""), j, i);
						}else{gp.add(new Label(rm.get(i)[j]), j, i);}
					}
				}
			} else {
				gp.getChildren().clear();
				for(int i = 0 ; i < rm.size() ; i++) {
					for(int j = 0 ; j < rm.get(i).length ; j++) {
						gp.add(new Label(rm.get(i)[j]), j, i);
					}
				}
			}
		});
	}
}
