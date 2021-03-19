package App.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditNoteController {
    @FXML
    private TextArea textNote;

    @FXML
    private TextField titleNote;

    @FXML
    private Button editNoteButton;

    @FXML
    private Button cancelButton;

    private static AppController appController;

    public static void setAppController(AppController appController) {
        EditNoteController.appController = appController;
    }

    public void setTextNote(String text) {
        textNote.setText(text);
    }

    public void setTitleNote(String title) {
        titleNote.setText(title);
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void editNote(ActionEvent event) {
        String title = titleNote.getText();
        String text = textNote.getText();

        appController.editNote(title, text);
        appController.showNotePane(title, text);

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }


}
