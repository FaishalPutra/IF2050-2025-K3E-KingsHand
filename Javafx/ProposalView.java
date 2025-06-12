import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProposalView {

    private Stage stage;

    public void start(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(getView(), 1200, 700);
        stage.setTitle("Dashboard - King's Hand");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #102030; -fx-padding: 15 30;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("\uD83D\uDC96 King's Hand");
        logo.setTextFill(Color.GOLD);
        logo.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

        Button dashboardBtn = createNavButton("Dashboard", Main::showDashboard);
        Button projectBtn = createNavButton("Project", Main::showProjectView);
        Button proposalBtn = createNavButton("Proposal", Main::showProposalView);

        HBox nav = new HBox(30, dashboardBtn, projectBtn, proposalBtn);
        nav.setPadding(new Insets(0, 0, 0, 60));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox rightIcons = new HBox(15,
                new Label("\uD83D\uDCEC"), // Mail
                new Label("\uD83D\uDD14"), // Bell
                new Button("ProFile"));
        rightIcons.setAlignment(Pos.CENTER_RIGHT);

        topBar.getChildren().addAll(logo, nav, spacer, rightIcons);
        return topBar;
    }

    private Button createNavButton(String text, Runnable action) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        btn.setOnAction(e -> action.run());
        return btn;
    }

    public Parent getView() {
        // ===== TOP NAVIGATION BAR =====
        HBox topNavbar = createTopBar();

        // ===== HEADER =====
        Label header = new Label("Proposal");
        header.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label badge = new Label("Penanggung jawab");
        badge.setStyle(
                "-fx-background-color: #FFA500; -fx-text-fill: white; -fx-padding: 4px 8px; -fx-background-radius: 15px;");

        HBox headerBox = new HBox(10, header, badge);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        // ===== FORM =====
        Label title = new Label("Overview Penanggung Jawab");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField namaField = new TextField("M. Rivaldi Mahari");
        TextField nimField = new TextField("18222014");
        TextField prodiField = new TextField("Teknik Informatika");
        TextField fakultasField = new TextField("STEI-K");
        TextField noHpField = new TextField("0852-6732-7783");
        TextField emailField = new TextField("m.rivaldimahari@students.itb.ac.id");

        namaField.setDisable(true);
        nimField.setDisable(true);
        prodiField.setDisable(true);
        fakultasField.setDisable(true);
        noHpField.setDisable(true);
        emailField.setDisable(true);

        GridPane formGrid = new GridPane();
        formGrid.setVgap(10);
        formGrid.setHgap(10);
        formGrid.addRow(0, new Label("Nama:"), namaField);
        formGrid.addRow(1, new Label("NIM:"), nimField);
        formGrid.addRow(2, new Label("Program Studi:"), prodiField);
        formGrid.addRow(3, new Label("Fakultas/Sekolah:"), fakultasField);
        formGrid.addRow(4, new Label("No. HP:"), noHpField);
        formGrid.addRow(5, new Label("Email:"), emailField);

        Label statement = new Label(
                "Menyatakan bahwa saya bersedia menjadi penanggung jawab atas pelaksanaan kegiatan seperti yang tertulis dalam proposal ini.\n\n"
                        + "Apabila di kemudian hari terjadi penyalahgunaan dana, maka saya bersedia menerima sanksi sesuai ketentuan yang berlaku di ITB.");
        statement.setWrapText(true);
        statement.setStyle("-fx-font-size: 14px;");

        VBox ttdBox = new VBox(5,
                new Label("Bandung, 9 Juni 2024"),
                new Label("(Tanda tangan)"),
                new Label("M. Rivaldi Mahari"));

        // ===== BUTTON EDIT =====
        Button editButton = new Button("Edit Proposal");
        editButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white; -fx-font-weight: bold;");
        editButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Proposal");
            alert.setHeaderText("Form telah dibuka untuk pengeditan.");
            namaField.setDisable(false);
            nimField.setDisable(false);
            prodiField.setDisable(false);
            fakultasField.setDisable(false);
            noHpField.setDisable(false);
            emailField.setDisable(false);
            alert.showAndWait();
        });

        // ===== CARD FORM =====
        VBox card = new VBox(15, title, formGrid, statement, ttdBox, editButton);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10px; -fx-border-color: #ccc; -fx-border-radius: 10px;");
        card.setPrefWidth(600);

        VBox content = new VBox(20, headerBox, card);
        content.setPadding(new Insets(30));
        content.setStyle("-fx-background-color: #F9F9F9;");
        content.setAlignment(Pos.TOP_LEFT);

        BorderPane root = new BorderPane();
        root.setTop(topNavbar);
        root.setCenter(content);

        return root;
    }
}
