package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ProjectView {
    public BorderPane getView() {
        BorderPane pane = new BorderPane();
        pane.setCenter(new Label("Halaman Project - Konten belum dibuat"));
        return pane;
    }
}
