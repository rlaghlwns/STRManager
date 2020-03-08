package manager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Member;
import model.WilksPoint;
import model.WilksRank;
import model.FileIO;

public class MainviewController implements Initializable{
	@FXML AnchorPane ap;
	@FXML TableView<Member> table;
	@FXML TableColumn<Member, String> tableColumn;
	@FXML Button delbtn, editbtn, routinebtn;
	@FXML Text name, age, gender, address, phonenumber, email, weight, bp1rm, sq1rm, dl1rm, ohp1rm, pr1rm,
				wpName, wpBodyweight, wpWilkspoint, wpGender, wpWeightclass, wpLevel;
	@FXML TextField search, editName, editAge, editGender, editPhonenumber, editEmail, editAddress, 
				editbodyweight, editbp, editsq, editdl, editohp, editpr;
	@FXML Label strNameGender;
	@FXML GridPane month1, month2, month3;
	@FXML StackPane tableBtnStack, infoStack, strStack, scheduleStack;
	@FXML TabPane tab1, tab2;
	Member member;
	FileIO fio =  new FileIO();
	String filePath;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (fio.oblist == null) {
			fio.list = new ArrayList<Member>();
			fio.oblist = FXCollections.observableArrayList();
		}
		try {
			fio.loaddata();	
			fio.initModel();
		} catch (Exception e) {}
		search.addEventFilter(KeyEvent.KEY_PRESSED, searchEvent);
		editName.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editAge.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editGender.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editPhonenumber.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editEmail.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editAddress.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editbodyweight.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editbp.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editsq.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editdl.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editohp.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		editpr.addEventFilter(KeyEvent.KEY_PRESSED, editKeyEvent);
		tab1.getStyleClass().add("floating");
		tab2.getStyleClass().add("floating");
		delbtn.setDisable(true);	
		editbtn.setDisable(true);	
		routinebtn.setDisable(true);
		setHideandShow("H", tableBtnStack.getChildren().get(1), infoStack.getChildren().get(1), strStack.getChildren().get(1), scheduleStack.getChildren().get(1));
		tableColumn.setCellValueFactory(v->v.getValue().getNameSSP());
		table.setItems(fio.oblist);
		table.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {
			member = nv;		//		for Member Edit and Workout Scheduling
			// init member info display
			delbtn.setDisable(false);	
			editbtn.setDisable(false);	
			scheduleStack.getChildren().get(1).setOpacity(0);
			scheduleStack.getChildren().get(0).setOpacity(0);
			if(member.getStrongliftRoutine()!=null||member.getWendlerRoutine()!=null) {
				setHideandShow("H", scheduleStack.getChildren().get(0));
				setHideandShow("S", scheduleStack.getChildren().get(1));
			}
			else {
				setHideandShow("H", scheduleStack.getChildren().get(1));
				setHideandShow("S", scheduleStack.getChildren().get(0));
				routinebtn.setDisable(false);
			}
			//Member Information Tab
			name.setText(nv.getName());				
			age.setText(nv.getAge());						
			gender.setText(nv.getGender());
			address.setText(nv.getAddress());		
			phonenumber.setText(nv.getPhonenumber());		
			email.setText(nv.getEmail());
			//Strength(Performance) Tab
			strNameGender.setText(nv.getName()+"("+nv.getGender()+")");
			weight.setText(nv.getWeight()+"");				
			bp1rm.setText(nv.getBp1rm()+"");
			sq1rm.setText(nv.getSq1rm()+"");				
			dl1rm.setText(nv.getDl1rm()+"");
			ohp1rm.setText(nv.getOhp1rm()+"");				
			pr1rm.setText(nv.getPr1rm()+"");
			//Wilks Point Tab
			wpName.setText(nv.getName());	
			wpBodyweight.setText(nv.getWeight()+"");		
			WilksPoint wp = new WilksPoint();
			WilksRank wr = new WilksRank();
			double wilkspoint = wp.Coeff(nv);			
			wpWilkspoint.setText(wilkspoint+"");
			wpGender.setText(nv.getGender());
			String weightclass = wr.weightClass(nv);			
			wpWeightclass.setText(weightclass);
			String level = wr.getRank(nv);					
			wpLevel.setText(level);
			//Schedule
			if(nv.getWendlerRoutine()!=null) 	{	routineDisplay(nv.getWendlerRoutine());		}
			if(nv.getStrongliftRoutine()!=null) {	routineDisplay(nv.getStrongliftRoutine());	}
			//editField init
			Bindings.bindBidirectional(editName.textProperty(), name.textProperty());
			Bindings.bindBidirectional(editAge.textProperty(), age.textProperty());
			Bindings.bindBidirectional(editGender.textProperty(), gender.textProperty());
			Bindings.bindBidirectional(editAddress.textProperty(), address.textProperty());
			Bindings.bindBidirectional(editPhonenumber.textProperty(), phonenumber.textProperty());
			Bindings.bindBidirectional(editEmail.textProperty(), email.textProperty());
			Bindings.bindBidirectional(editbodyweight.textProperty(), weight.textProperty());
			Bindings.bindBidirectional(editbp.textProperty(), bp1rm.textProperty());
			Bindings.bindBidirectional(editsq.textProperty(), sq1rm.textProperty());
			Bindings.bindBidirectional(editdl.textProperty(), dl1rm.textProperty());
			Bindings.bindBidirectional(editohp.textProperty(), ohp1rm.textProperty());
			Bindings.bindBidirectional(editpr.textProperty(), pr1rm.textProperty());
		});
	}
	@FXML private void open() throws Exception {
		try {
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(new Stage());
			fio.loaddata(file);
			filePath = file.getAbsolutePath().replace("\\", "\\\\");
			fio.initModel();
			fio.savedata();
			Stage now = (Stage) table.getScene().getWindow();	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
			now.setResizable(false);
			now.setScene(new Scene(loader.load()));
		} catch (Exception e) {}
	}
	@FXML private void save() throws Exception {
		FileChooser fc = new FileChooser();
		File file = fc.showSaveDialog(new Stage());
		fio.savedata(file);
	}
	@FXML private void quit() throws Exception {	System.exit(0);	}
	@FXML private void delbtn() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Member");
			alert.setHeaderText("Delete this Member : "+member.getName());
			alert.setContentText("Are you sure you want to delete this member?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				fio.list.remove(table.getSelectionModel().getSelectedIndex());
				fio.savedata();
				Stage now = (Stage) table.getScene().getWindow();	
				FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
				now.setResizable(false);
				now.setScene(new Scene(loader.load()));
			} else {}
		} catch (Exception e) {}
	}
	@FXML private void editbtn() {
		setHideandShow("H", tableBtnStack.getChildren().get(0));
		setHideandShow("S", tableBtnStack.getChildren().get(1), infoStack.getChildren().get(1), strStack.getChildren().get(1));
	}
	@FXML private void modificationbtn() {
		modification();
	}
	private void modification() {
		try {
			member.setName(editName.getText());
			member.setAge(editAge.getText());
			member.setGender(editGender.getText());
			member.setAddress(editAddress.getText());
			member.setEmail(editEmail.getText());
			member.setPhonenumber(editPhonenumber.getText());
			member.setWeight(Double.parseDouble(editbodyweight.getText()));
			member.setBp1rm(Double.parseDouble(editbp.getText()));
			member.setSq1rm(Double.parseDouble(editsq.getText()));
			member.setDl1rm(Double.parseDouble(editdl.getText()));
			member.setOhp1rm(Double.parseDouble(editohp.getText()));
			member.setPr1rm(Double.parseDouble(editpr.getText()));
			setHideandShow("H", tableBtnStack.getChildren().get(0));
			setHideandShow("S", tableBtnStack.getChildren().get(1), infoStack.getChildren().get(1), strStack.getChildren().get(1));
			fio.savedata();
			Stage now = (Stage) table.getScene().getWindow();	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
			now.setResizable(false);
			now.setScene(new Scene(loader.load()));
		} catch (Exception e) {}
	}
	@FXML private void newbtn() {
		try {
			Stage now = (Stage) ap.getScene().getWindow();
			now.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("joinview.fxml"));
			Parent root = loader.load();
			JoinviewController jcon = loader.getController();
			jcon.maincon = this;
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("mainview.fxml"));
			Stage next = new Stage();
			next.setScene(new Scene(loader2.load()));
			next.show();
		} catch (Exception e) {}
	}
	@FXML private void onermbtn() {
		try {
			ap.setDisable(true);
			Stage newStage = new Stage();
			newStage.setAlwaysOnTop(true);
			Parent root = FXMLLoader.load(getClass().getResource("calcview.fxml"));
			newStage.setScene(new Scene(root));
			newStage.showAndWait();
			ap.setDisable(false);
		} catch (Exception e) {}
	}
	@FXML private void routinebtn() throws IOException {
		routinebtn.setDisable(true);
		ap.setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("routineview.fxml"));
		Parent root = loader.load();
		RoutineviewController rvc = loader.getController();
		rvc.setMaincon(this);
		Stage stage = new Stage();
		stage.setAlwaysOnTop(true);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		routinebtn.setDisable(false);
		Stage now = (Stage) ap.getScene().getWindow();
		Parent root2 = FXMLLoader.load(getClass().getResource("mainview.fxml"));
		now.setScene(new Scene(root2));
	}
	@FXML private void scheduleRemovebtn() {
		member.setStrongliftRoutine(null);
		member.setWendlerRoutine(null);
		fio.savedata();
		try {
			Stage now = (Stage) table.getScene().getWindow();	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
			now.setResizable(false);
			now.setScene(new Scene(loader.load()));
		} catch (Exception e) {}
	}
	private void routineDisplay(ArrayList<String[]> routine) {
		month1.getChildren().clear();	
		month2.getChildren().clear();	
		month3.getChildren().clear();
		String[] day = {"Mon", "Tues", "Wed", "Thur", "Fri"};
		for(int i = 0 ; i < routine.size() ; i++) {
			for(int j = 0 ; j < routine.get(i).length ; j++) {
				if(i > 7) {
					month3.add(new Label(routine.get(i)[j]), j+1, i-7);
					Label label = new Label("Week"+(i+1));
					label.setPrefSize(100, 100);
					label.setFont(new Font(20));
					label.setAlignment(Pos.CENTER);
					label.setTextAlignment(TextAlignment.CENTER);
					month3.add(label, 0, i-7);
					Label label2 = new Label(day[j]);
					label2.setPrefSize(100, 100);
					label2.setFont(new Font(24));
					label2.setAlignment(Pos.CENTER);
					month3.add(label2, j+1, 0);
				}
				else if(i > 3) {
					month2.add(new Label(routine.get(i)[j]), j+1, i-3);
					Label label = new Label("Week"+(i+1));
					label.setPrefSize(100, 100);
					label.setFont(new Font(20));
					label.setAlignment(Pos.CENTER);
					label.setTextAlignment(TextAlignment.CENTER);
					month2.add(label, 0, i-3);
					Label label2 = new Label(day[j]);
					label2.setPrefSize(100, 100);
					label2.setFont(new Font(24));
					label2.setAlignment(Pos.CENTER);
					month2.add(label2, j+1, 0);
				}
				else {
					month1.add(new Label(routine.get(i)[j]), j+1, i+1);
					Label label = new Label("Week"+(i+1));
					label.setPrefSize(100, 100);
					label.setFont(new Font(20));
					label.setAlignment(Pos.CENTER);
					label.setTextAlignment(TextAlignment.CENTER);
					month1.add(label, 0, i+1);
					Label label2 = new Label(day[j]);
					label2.setPrefSize(100, 100);
					label2.setFont(new Font(24));
					label2.setAlignment(Pos.CENTER);
					month1.add(label2, j+1, 0);
				}
			}
		}
	}
	private void setHideandShow(String hs, Node...no) {
		if(hs.equals("H")) {
			for(Node n : no) {
				n.setOpacity(0);
				n.setDisable(true);
			}
		}
		else if(hs.equals("S")) {
			for(Node n : no) {
				n.setOpacity(1);
				n.setDisable(false);
			}
		}
	}
	private EventHandler<KeyEvent> searchEvent = (value)->{
		if(value.getCode().equals(KeyCode.ESCAPE))	{
			TextField tf = (TextField) value.getTarget();
			tf.setText("");
		}
		if(value.getCode().equals(KeyCode.ENTER))	{
			String searchname = search.getText();
			for(int i = 0 ; i < table.getItems().size() ; i++) {
				if(table.getItems().get(i).getName().equals(searchname)) {
					table.getFocusModel().focus(i);
					table.requestFocus();
				}
			}
		}
	};
	private EventHandler<KeyEvent> editKeyEvent = (value)->{
		if(value.getCode().equals(KeyCode.ESCAPE))	{
			TextField tf = (TextField) value.getTarget();
			tf.setText("");
		}
		if(value.getCode().equals(KeyCode.ENTER))	{
			modification();
		}
	};
}