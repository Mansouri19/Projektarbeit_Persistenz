package development.project.app_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {
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

	    }

	    @FXML
	    void OnKundeUpdate(ActionEvent event) {

	    }


}
