package manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Member;

public class JoinviewController implements Initializable {
	@FXML TextField newName, newAge, newAddress, newPhonenumber, newEmail;
	@FXML Button create, cancel;
	@FXML Label nameWarning, ageWarning, genderWarning, addressWarning, phonenumberWarning, emailWarning;
	@FXML RadioButton checkMale, checkFemale;
	@FXML ToggleGroup group = new ToggleGroup();
	MainviewController maincon;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		checkMale.setToggleGroup(group);	
		checkFemale.setToggleGroup(group);
		newName.addEventFilter(KeyEvent.KEY_PRESSED, newKeyEvent);
		newAge.addEventFilter(KeyEvent.KEY_PRESSED, newKeyEvent);
		newAddress.addEventFilter(KeyEvent.KEY_PRESSED, newKeyEvent);
		newPhonenumber.addEventFilter(KeyEvent.KEY_PRESSED, newKeyEvent);
		newEmail.addEventFilter(KeyEvent.KEY_PRESSED, newKeyEvent);
	}
	@FXML private void create() {
		if (newName.getText().contentEquals("")) {
			nameWarning.setText("* Essential information"); 
			newName.requestFocus();
		}
		else if (newAge.getText().contentEquals("")) {
			ageWarning.setText("* Essential information"); 
			newAge.requestFocus();
		}
		else if (checkMale.isSelected()==false && checkFemale.isSelected()==false) {
			genderWarning.setText("* Essential information");
			checkMale.requestFocus();
		}
		else if (newAddress.getText().contentEquals("")) {
			addressWarning.setText("* Essential information"); 
			newAddress.requestFocus();
		}
		else if (newPhonenumber.getText().contentEquals("")) {
			phonenumberWarning.setText("* Essential information"); 
			newPhonenumber.requestFocus();
		}
		else if (newEmail.getText().contentEquals("")) {
			emailWarning.setText("* Essential information"); 
			newEmail.requestFocus();
		}
		else {
			try {
				String name = newName.getText();
				String age = newAge.getText();
				String gender = null;
				if(checkMale.isSelected()) {gender = "Male";} 
				else {gender = "Female";}
				String address = newAddress.getText();
				String phonenumber = newPhonenumber.getText();
				String email = newEmail.getText();
				maincon.fio.list.add(new Member(name, age, gender, address, phonenumber, email, 0, 0, 0, 0, 0, 0));
				maincon.fio.savedata();
				Stage now = (Stage) create.getScene().getWindow();
				now.close();
			} catch (Exception e) {}
		}
	}
	@FXML private void cancel() {
		try {
			Stage now = (Stage) create.getScene().getWindow();
			now.close();
		} catch (Exception e) {}
	}
	private EventHandler<KeyEvent> newKeyEvent = (value)->{
		if(value.getCode().equals(KeyCode.ESCAPE))	{
			TextField tf = (TextField) value.getTarget();
			tf.setText("");
		}
		if(value.getCode().equals(KeyCode.ENTER))	{
			create();
		}
	};
}
