package development.project.app_javafx;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.swing.JOptionPane;

import development.project.KundenTest;
import development.project.dao.model.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private TableView<?> kundenList_TableView;

	@FXML
	private Label status_label;

	@FXML
	void OnKundeAdd(ActionEvent event) {
		if (checkEingabe()) {
			KundenTest.addKunde(Integer.parseInt(kundenId_tf.getText()), vorname_tf.getText(), nachname_tf.getText(),
					geburtsdatum_tf.toString(), adresse_tf.getText(), telNr_tf.getText());

		} else {

			JOptionPane.showMessageDialog(null, "Bitte ergänzen Sie die Eingabe !");
		}

	}

	@FXML
	void OnKundeDelete(ActionEvent event) {

	}

	@FXML
	void OnKundeReset(ActionEvent event) {
		Kunde kunde = new Kunde(null, "", "", "", "", "");
		updateGuiFrom(kunde);
	}

	@FXML
	void OnKundeUpdate(ActionEvent event) {

	}

	private void updateGuiFrom(Kunde kunde) {
		kundenId_tf.setText(null);
		vorname_tf.setText(kunde.getVorname());
		nachname_tf.setText(kunde.getNachname());
		geburtsdatum_tf.setValue(null);
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

	private boolean getConfirmation(String header, String text) {
		Alert alert = new Alert(AlertType.CONFIRMATION); // Controlsfx
		alert.setTitle("Bestätigung");
		alert.setHeaderText(header);
		alert.setContentText(text);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}

	private boolean checkEingabe() {
		if (kundenId_tf == null || vorname_tf == null || nachname_tf == null || geburtsdatum_tf == null
				|| adresse_tf == null || telNr_tf == null) {
			return false;
		} else {
			return true;
		}

	}

}
