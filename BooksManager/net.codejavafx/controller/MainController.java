package controller;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

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
import net.codejava.*;
import net.codejava.BooksManager;

public class MainController {
	@SuppressWarnings("unused")
	private BooksManager booksManager;

	@FXML
	private ListView<Book> bookList_listView;

	@FXML
	private TextField bookId_tf;

	@FXML
	private TextField title_tf;

	@FXML
	private TextField author_tf;

	@FXML
	private TextField preis_tf;

	@FXML
	private Label status_label;

	@FXML
	void onBookListClick(Event event) {
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

		Book selectedBook = getSelectedBook();
		if (selectedBook == null) {
			setStatusError("Bitte ein Buch auswählen");
			return;
		}
		Book book = getBookFromGui();
		Integer id = getSelectedBook().getBookId();
		book.setBookId(id);

		BooksManager.update();

		setStatusInfo("Das Buch wurde aktualisiert");
		int index = bookList_listView.getSelectionModel().getSelectedIndex();
		bookList_listView.getItems().set(index, book);
	}

	@FXML
	void onBookDelete(ActionEvent event) {
		clearStatusText();

		Book selectedBook = getSelectedBook();
		if (selectedBook == null) {
			setStatusError("Bitte ein Buch auswählen");
			return;
		}
		if (getConfirmation("Ein Buch wird aus der Liste unwiderruflich entfernt:", "Sind Sie einverstanden?")) {

			BooksManager.remove();

			bookList_listView.getItems().remove(selectedBook);
			setStatusInfo("Das Buch [" + selectedBook.getTitle() + "] wurde gelöscht.");
		}
	}

	private void onBookList() {
		clearStatusText();
		System.out.println("==> Tab aktiviert!!!");

		List<Book> bookList = BooksManager.getBookList();

		bookList_listView.getItems().setAll(bookList);
	}

	@SuppressWarnings("unused")
	private boolean isButton(Button button, Button clicked) {
		return button.getId().equals(clicked.getId());
	}

	@SuppressWarnings("unused")
	private Book getBook1() {
		return new Book(1, "Rheinwerk Computing", "978-3-8362-1802-3", 50);
	}

	@FXML
	void onBookCreate(ActionEvent event) {

		Book book = getBookFromGui();

		BooksManager.create();

		System.out.println(BooksManager.getBookList());
		JOptionPane.showMessageDialog(null, "Buch wurde angelegt");
		bookList_listView.getItems().add(book);
		bookList_listView.getSelectionModel().select(book);
		setStatusInfo("Das Buch [" + book.getTitle() + "] wurde angelegt.");
	}

	@FXML
	void onBookReset(ActionEvent event) {
		Book book = new Book(null, "", "", 0);
		updateGuiFrom(book);
	}

	@FXML
	void initialize() {
		System.out.println("==> Starting the Book Management Application ...");
		booksManager = new BooksManager();
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
		Alert alert = new Alert(AlertType.CONFIRMATION); 
		alert.setTitle("Bestätigung");
		alert.setHeaderText(header);
		alert.setContentText(text);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}

	private void updateGuiFrom(Book book) {
		title_tf.setText(book.getTitle());
		author_tf.setText(book.getAuthor());
	}

	private Book getBookFromGui() {
		Integer id = Integer.parseInt(bookId_tf.getText());
		String title = title_tf.getText();
		String author = author_tf.getText();
		float preis =  Float.parseFloat(preis_tf.getText());
		return new Book(id, title, author, preis);
	}
}
