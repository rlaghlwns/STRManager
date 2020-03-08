package manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebviewController implements Initializable {
	@FXML WebView webview;
	WebEngine engine;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.engine  = webview.getEngine();
		engine.load("https://en.wikipedia.org/wiki/One-repetition_maximum");
	}
}
