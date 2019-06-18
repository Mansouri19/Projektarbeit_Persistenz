package controller;

import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Book;
import model.BookService;

public class MainController {
	private BookService bookService;
	// ########################################################################
	// # Tab: "Neu" #
	// ########################################################################
	@FXML
	private TextField title_tf;
	@FXML
	private TextField publisher_tf;
	@FXML
	private TextField isbn_tf;
	@FXML
	private TextField author_tf;
	@FXML
	private TextField date_tf;
	@FXML
	private Button fillData1_btn;
	@FXML
	private Button fillData2_btn;
	@FXML
	private Button fillData3_btn;

	// ########################################################################
	// # Tab: "Bücherliste" #
	// ########################################################################
	@FXML
	private ListView<Book> bookList_listView;
	// ########################################################################
	@FXML
	private Label status_label;

	// ########################################################################
	// ########################################################################
	@FXML
	void onBookListClick(Event event) { // Event und nicht ActionEvent !!!
		clearStatusText();
		Book selectedBook = getSelectedBook();
		if (selectedBook != null) {
			updateGuiFrom(selectedBook);
		}
	}

	private Book getSelectedBook() {
		return bookList_listView.getSelectionModel().getSelectedItem();
	}

	@FXML
	void onBookUpdate(ActionEvent event) {
		// 1. Benutzereingabe auswerten
		Book selectedBook = getSelectedBook();
		if (selectedBook == null) {
			setStatusError("Bitte ein Buch auswählen");
			return;
		}
		Book book = getBookFromGui(); // neue Daten holen (ohne id)
		Long id = getSelectedBook().getId();
		book.setId(id);
		// 2. Modell aufrufen
		bookService.update(book); // hier wird die "echte" BookList aktualisiert
		// 3. View aktualisieren
		setStatusInfo("Das Buch wurde aktualisiert"); // TODO listview einfach aktualieren
		int index = bookList_listView.getSelectionModel().getSelectedIndex();
		bookList_listView.getItems().set(index, book);
	}

	@FXML
	void onBookDelete(ActionEvent event) {
		clearStatusText();
		// 1. Benutzereingabe auslesen usw.
		Book selectedBook = getSelectedBook();
		if (selectedBook == null) {
			setStatusError("Bitte ein Buch auswählen");
			return;
		}
		if (getConfirmation("Ein Buch wird aus der Liste unwiderruflich entfernt:", "Sind Sie einverstanden?")) {
			// 2. Modell aufrufen
			bookService.delete(selectedBook);
			// 3. View aktualisieren
			bookList_listView.getItems().remove(selectedBook); // Aus der Grafik entfernen
			setStatusInfo("Das Buch [" + selectedBook.getTitle() + "] wurde gelöscht.");
		}
	}

	private void onBookList() { // Wird von der initialize()-Methode ausgeführt
		clearStatusText();
		System.out.println("================> Tab aktiviert!!!");
		// 1. Benutzereingabe auswerten, konvertieren, validieren und passende Objekte
		// bilden
		// Nichts zu tun
		// 2. Modell aufrufen
		List<Book> bookList = bookService.getBookList();
		// 3. View aktualisieren
		bookList_listView.getItems().setAll(bookList);
	}

	// ########################################################################
	@FXML
	void fillData(ActionEvent event) {
		Button clicked = (Button) event.getSource();
		Book book;
		if (isButton(fillData1_btn, clicked)) {
			book = getBook1();
		} else if (isButton(fillData2_btn, clicked)) {
			book = getBook2();
		} else { // "3" wurde geklickt
			book = getBook3();
		}
		updateGuiFrom(book);
	}

	private boolean isButton(Button button, Button clicked) {
		return button.getId().equals(clicked.getId());
	}

	private Book getBook1() {
		return new Book("Java ist auch eine Insel", "Rheinwerk Computing", "978-3-8362-1802-3", "Christian Ullenboom",
				"2011");
	}

	private Book getBook2() {
		return new Book("Professionell entwickeln mit Java EE 7", "Rheinwerk Computing", "978-3-8362-2004-0",
				"Alexander Salvanos", "2014");
	}

	private Book getBook3() {
		return new Book("OCEJWCD Study Companion", "Garner Press", "978-0955160349", "Charles Lyons",
				"15. August 2012");
	}

	@FXML
	void onBookCreate(ActionEvent event) { // Vorlage für alle Aufgaben
		// ###############################################################
		// # 1. Aufgaben der Controller-Schicht:
		// # Benutzereingabe auswerten, konvertieren, validieren
		// # und passende Objekte bilden
		// ###############################################################
		Book book = getBookFromGui();
		// ###############################################################
		// # 2. Model-Komponente aufrufen
		// ###############################################################
		bookService.create(book);
		// ###############################################################
		// # 3. View-Komponente aufrufen
		// ###############################################################
		System.out.println(bookService.getBookList()); // Debugging
//	    JOptionPane.showMessageDialog(null, "Buch wurde angelegt");
		bookList_listView.getItems().add(book); // Buch zur View hinzufügen
		bookList_listView.getSelectionModel().select(book); // Buch in der ListView selektieren
		setStatusInfo("Das Buch [" + book.getTitle() + "] wurde angelegt.");
	}

	@FXML
	void onBookReset(ActionEvent event) {
		Book book = new Book("", "", "", "", "");
		updateGuiFrom(book);
	}

	@FXML
	void initialize() {
		System.out.println("=================> Starting the Book Management Application ...");
		bookService = new BookService();
		clearStatusText();
		onBookList();
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

	private void updateGuiFrom(Book book) {
		title_tf.setText(book.getTitle());
		publisher_tf.setText(book.getPublisher());
		isbn_tf.setText(book.getIsbn());
		author_tf.setText(book.getAuthor());
		date_tf.setText(book.getDate());
	}

	private Book getBookFromGui() {
		String title = title_tf.getText();
		String publisher = publisher_tf.getText();
		String isbn = isbn_tf.getText();
		String author = author_tf.getText();
		String date = date_tf.getText();
		return new Book(title, publisher, isbn, author, date);
	}
}
