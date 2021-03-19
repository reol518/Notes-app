package App.Controllers;

import App.NotesDomain.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewNoteController {
    @FXML
    private TextArea textNewNote;

    @FXML
    private TextField titleNewNote;

    @FXML
    private Button addNewNoteButton;

    @FXML
    private Button cancelNewNoteButton;

    private static AppController appController;

    public static void setAppController(AppController appController) {
        NewNoteController.appController = appController;
    }

    public void initialize() {

    }

    @FXML
    public void addNewNote(ActionEvent event) {
        appController.addNewNote(new Note(titleNewNote.getText(), textNewNote.getText()));

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void cancelAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
