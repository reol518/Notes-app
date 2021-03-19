package App.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NotePaneController {
    @FXML
    private TextField titleNotePane;

    @FXML
    private TextArea textNotePane;

    @FXML
    private Button closeNoteButton;

    @FXML
    private Button deleteNoteButton;

    @FXML
    private Button editNoteButton;

    private static AppController appController;

    public static void setAppController(AppController appController) {
        NotePaneController.appController = appController;
    }

    public void setTitle(String title) {
        titleNotePane.setText(title);
    }

    public String getTitle() {
        return titleNotePane.getText();
    }

    public void setText(String text) {
        textNotePane.setText(text);
    }

    public String getText() {
        return textNotePane.getText();
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void closeNotePane() {
        appController.getNotePane().getChildren().clear();
    }

    @FXML
    public void editNote() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/EditNote.fxml"));
            EditNoteController editNoteController = loader.getController();
            editNoteController.setTitleNote(getTitle());
            editNoteController.setTextNote(getText());
            Stage stage = new Stage();
            stage.setTitle("Edit note");
            stage.setScene(new Scene(root, 600, 470));
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteNote() {
        appController.removeNote(AppController.getCurrentNoteId());
        appController.getNotePane().getChildren().clear();
    }

}
