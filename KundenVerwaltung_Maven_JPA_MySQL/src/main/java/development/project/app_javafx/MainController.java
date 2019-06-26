package development.project.app_javafx;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import development.project.KundenTest;
import development.project.dao.model.Kunde;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
	private TableView<Kunde> kundenList_TableView;

	@FXML
	private TableColumn<Kunde, Integer> idColumn;

	@FXML
	private TableColumn<Kunde, String> vornameColumn;

	@FXML
	private TableColumn<Kunde, String> nachnameColumn;

	@FXML
	private TableColumn<Kunde, Date> gebDColumn;

	@FXML
	private TableColumn<Kunde, String> adresseColumn;

	@FXML
	private TableColumn<Kunde, String> telNrColumn;

	@FXML
	private Label status_label;

	@FXML
	void OnKundeAdd(ActionEvent event) {
		if (checkEingabe()) {
			Kunde kunde = getBookFromGui();
			KundenTest.addKunde(kunde.getKundeId(), kunde.getVorname(), kunde.getNachname(), kunde.getGeburtsdatum(),
					kunde.getAdresse(), kunde.getTelNr());

			JOptionPane.showMessageDialog(null, "Kunde wurde angelegt");
//			showList();
			setStatusInfo("Kunde wurde angelegt");

		} else {
			JOptionPane.showMessageDialog(null, "Bitte ergänzen Sie die Eingabe !");
		}

	}

	public void showList() {
		List<Kunde> list = KundenTest.getKundenList();
		ObservableList<Kunde> data = FXCollections.observableArrayList(list);
		
		kundenList_TableView.setItems(data);

		Object[] row = new Object[6];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getKundeId();
			row[0] = list.get(i).getVorname();
			row[0] = list.get(i).getNachname();
			row[0] = list.get(i).getGeburtsdatum();
			row[0] = list.get(i).getAdresse();
			row[0] = list.get(i).getTelNr();

			data.addAll((Kunde[]) row);
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

	private Kunde getBookFromGui() {
		Integer kundeId = Integer.parseInt(kundenId_tf.getText());
		String vorname = vorname_tf.getText();
		String nachname = nachname_tf.getText();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String gebDat = formatter.format(geburtsdatum_tf.getValue());
		String adresse = adresse_tf.getText();
		String telNr = telNr_tf.getText();
		return new Kunde(kundeId, vorname, nachname, gebDat, adresse, telNr);
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
