package development.project.app_javafx;

import java.time.format.DateTimeFormatter;

import development.project.KundenTest;
import development.project.dao.model.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class MainController {
	
	KundenTest kundenTest = new KundenTest();
	@FXML
	private TextField kundenId_tf;

	@FXML
	private TextField vorname_tf;

	@FXML
	private TextField nachname_tf;

	@FXML
	private TextField adresse_tf;

	@FXML
	private TextField telNr_tf;

	@FXML
	private DatePicker geburtsdatum_tf;

	@FXML
	private ListView<?> kundenList_listView;

	@FXML
	private Label status_label;

	@FXML
	void OnKundeAdd(ActionEvent event) {

	}

	@FXML
	void OnKundeDelete(ActionEvent event) {

	}

	@FXML
	void OnKundeReset(ActionEvent event) {
		Kunde kunde = new Kunde(0, "", "", "", "", "");
		updateGuiFrom(kunde);
	}

	@FXML
	void OnKundeUpdate(ActionEvent event) {

	}

	private void updateGuiFrom(Kunde kunde) {
		kundenId_tf.setText(String.valueOf(kunde.getKundeId()));
		vorname_tf.setText(kunde.getVorname());
		nachname_tf.setText(kunde.getNachname());
//		geburtsdatum_tf.
		adresse_tf.setText(kunde.getAdresse());
		telNr_tf.setText(kunde.getTelNr());
		
	}
	
	private void clearStatusText() {
		status_label.setText("");
	}

	private void setStatusInfo(String info) {
		status_label.setTextFill(Color.GREEN);
		status_label.setText(info);
	}

	private void setStatusError(String error) {
		status_label.setTextFill(Color.RED);
		status_label.setText(error);
	}

}
