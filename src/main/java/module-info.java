module notes_desktop {
    requires javafx.controls;
    requires javafx.fxml;
    opens App.Controllers to javafx.fxml;
    exports App;
}

