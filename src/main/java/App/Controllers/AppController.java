package App.Controllers;

import App.NotesDomain.Note;
import App.NotesDomain.Notes;
import App.NotesData.NotesDataServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox notesVBox;

    @FXML
    private AnchorPane notePane;

    @FXML
    private Button addNewNoteButton;

    private Notes notes;
    private static int currentNoteId;

    public AnchorPane getNotePane() {
        return notePane;
    }

    public static int getCurrentNoteId() {
        return currentNoteId;
    }

    public void initialize() {
        NewNoteController.setAppController(this);
        NotePaneController.setAppController(this);
        EditNoteController.setAppController(this);

        notes = new Notes(new NotesDataServiceImpl());

        showNotes();
    }

    // перерисовка всех, тк не нашел решения для нормальной работы при удалении только 1 элемента
    private void showNotes() {
        notesVBox.getChildren().clear();
        for (int key : notes.getKeySet()) {
            addNoteButton(key);
        }
    }

    public void addNoteButton(int id) {
        Button noteButton = new Button(notes.getNote(id).getTitle());
        noteButton.setMinWidth(245);
        noteButton.setMinHeight(50);
        noteButton.setStyle("-fx-font-size:20; -fx-border-color:#76797e;");
        noteButton.setOnAction(new ShowNoteEventHandler(id));
        notesVBox.getChildren().add(noteButton);
    }

    public void addNewNote(Note note) {
        notes.addNote(note);
        addNoteButton(note.getId());
    }

    public void editNote(String title, String text) {
        Note note = notes.getNote(currentNoteId);
        note.setTitle(title);
        note.setText(text);
        notes.addNote(note);
        showNotes();
    }

    public void removeNote(int id) {
        notes.removeNote(id);
        showNotes();
    }

    @FXML
    public void openNewNoteEditor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/NewNote.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New note");
            stage.setScene(new Scene(root, 600, 470));
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ShowNoteEventHandler implements EventHandler<ActionEvent> {

        private int id;

        ShowNoteEventHandler(int id) {
            this.id = id;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            Note note = notes.getNote(id);
            showNotePane(note.getTitle(), note.getText());
            currentNoteId = note.getId();
        }
    }

    public void showNotePane(String title, String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/NotePane.fxml"));
            Parent NoteFMXL = fxmlLoader.load();
            NotePaneController notePaneController = fxmlLoader.getController();
            notePaneController.setTitle(title);
            notePaneController.setText(text);
            notePane.getChildren().clear();
            notePane.getChildren().add(NoteFMXL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
