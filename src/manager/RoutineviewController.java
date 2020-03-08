package manager;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.StrongLift;
import model.Wendler;

public class RoutineviewController implements Initializable {
	@FXML VBox firstpane, secondpane, thirdpane;
	@FXML Button nextbtn, prevbtn, wendlerbtn, strongliftbtn;
	@FXML Hyperlink wendlerhyperlink, stronglifthyperlink;
	private ArrayList<VBox> panelist = new ArrayList<VBox>();
	private int stackintex = 0;
	private MainviewController maincon;
	public void setMaincon(MainviewController maincon) {
		this.maincon = maincon;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		panelist.add(firstpane);	
		panelist.add(secondpane);	
		panelist.add(thirdpane);
	}
	@FXML void wendlerbtn() {
		Wendler wd = new Wendler();
		try {
			if(maincon.member.getWendlerRoutine()==null&&maincon.member.getStrongliftRoutine()==null) {
				maincon.member.setWendlerRoutine(wd.createRoutine(maincon.member, 2.5));
				maincon.fio.savedata();
			}
			Stage now = (Stage)wendlerbtn.getScene().getWindow();
			now.close();
		} catch (Exception e) {}
	}
	@FXML void strongliftbtn() {
		StrongLift sl = new StrongLift();
		try {
			if(maincon.member.getWendlerRoutine()==null&&maincon.member.getStrongliftRoutine()==null) {
				maincon.member.setStrongliftRoutine(sl.createRoutine(maincon.member, 2.5));
				maincon.fio.savedata();
			}
			Stage now = (Stage)strongliftbtn.getScene().getWindow();
			now.close();
		} catch (Exception e) {}
	}
	@FXML private void wendlerhyperlink() throws MalformedURLException, IOException, URISyntaxException {
		Desktop.getDesktop().browse(new URL("https://crossfitoutbreak.com/wendler-531-strength-program/").toURI());
	}
	@FXML private void stronglifthyperlink() throws MalformedURLException, IOException, URISyntaxException {
		Desktop.getDesktop().browse(new URL("https://www.transparentlabs.com/blogs/all/35395395-building-mass-with-5x5-training").toURI());
	}
	@FXML private void nextbtn() {
		if(stackintex != 2) {
			stackintex++;
			for(int i = 0 ; i < panelist.size() ; i++) {
				if(stackintex == i) {
					panelist.get(i).setOpacity(1);
				}
				else {
					panelist.get(i).setOpacity(0);
				}
			}
		}
	}
	@FXML private void prevbtn() {
		if(stackintex != 0) {
			stackintex--;
			for(int i = 0 ; i < panelist.size() ; i++) {
				if(stackintex == i) {
					panelist.get(i).setOpacity(1);
				}
				else {
					panelist.get(i).setOpacity(0);
				}
			}
		}
	}
}
