import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.File;

public class ProposalView {

    private Stage stage;
    private Label uploadedFileLabel = new Label(); // Untuk menampilkan nama file
    private Button reuploadButton = new Button("Hapus / Upload Ulang"); // Tombol untuk upload ulang
    private File uploadedFile = null; // Untuk menyimpan referensi file yang telah diupload

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

        ImageView logoImageView = new ImageView(new Image("file:img/logo.png"));
        logoImageView.setFitWidth(24); // Sesuaikan ukuran gambar di sini
        logoImageView.setFitHeight(24);

        Label logo = new Label("King's Hand");
        logo.setTextFill(Color.GOLD);
        logo.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        logo.setGraphic(logoImageView); // Set gambar sebagai icon
        logo.setGraphicTextGap(10); // Jarak antara gambar dan teks

        Button dashboardBtn = createNavButton("Dashboard", Main::showDashboard);
        Button projectBtn = createNavButton("Project", Main::showProjectView);
        Button proposalBtn = createNavButton("Proposal", Main::showProposalView);

        HBox nav = new HBox(30, dashboardBtn, projectBtn, proposalBtn);
        nav.setPadding(new Insets(0, 0, 0, 60));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Tombol Profile yang terhubung ke ProfileView
        Button profileBtn = new Button("Profile");
        profileBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: gold; -fx-border-radius: 10;");
        profileBtn.setOnAction(e -> Main.showProfileView());

        Label emailIcon = new Label("\uD83D\uDCEC"); // mail icon
        Label bellIcon = new Label("\uD83D\uDD14"); // bell icon
        emailIcon.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
        bellIcon.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        HBox rightIcons = new HBox(15, emailIcon, bellIcon, profileBtn);
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

        TextField judulField = new TextField("Proyek Pembuatan Wisata Alam");
        TextField detailField = new TextField(
                "Maul Kiper");

        judulField.setDisable(true);
        detailField.setDisable(true);

        GridPane formGrid = new GridPane();
        formGrid.setVgap(10);
        formGrid.setHgap(10);
        formGrid.addRow(0, new Label("Judul Proyek:"), judulField);
        formGrid.addRow(1, new Label(" PJ Proyek:"), detailField);

        Label statement = new Label(
                "PT Purwacaraka dengan mengedepankan konsep eco tourism berinisiasi menjadikan air terjun telu leuwi opat sebagai dedestinasi terbaru kota Bandung deng "
                        + "Apabila di kemudian hari terjadi penyalahgunaan dana, maka saya bersedia menerima sanksi sesuai ketentuan yang berlaku di ITB.");
        statement.setWrapText(true);
        statement.setStyle("-fx-font-size: 14px;");

        VBox ttdBox = new VBox(5,
                new Label("Bandung, 9 Juni 2024"),
                new Label("(Tanda tangan)"),
                new Label("M. Rivaldi Mahari"));

        // ===== BUTTONS =====
        Button editButton = new Button("Edit Proposal");
        editButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white; -fx-font-weight: bold;");
        editButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Proposal");
            alert.setHeaderText("Form telah dibuka untuk pengeditan.");
            judulField.setDisable(false);
            detailField.setDisable(false);
            alert.showAndWait();
        });

        Button uploadButton = new Button("Upload Proposal");
        uploadButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold;");
        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih File Proposal");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));

            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                try {
                    File targetDir = new File("proposal_uploads");
                    if (!targetDir.exists()) {
                        targetDir.mkdirs();
                    }

                    File targetFile = new File(targetDir, selectedFile.getName());
                    java.nio.file.Files.copy(
                            selectedFile.toPath(),
                            targetFile.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                    uploadedFile = targetFile;
                    uploadButton.setText("Proposal Berhasil diupload");
                    uploadButton.setDisable(true); // Opsional: tombol upload dinonaktifkan

                    uploadedFileLabel.setText("📄 " + targetFile.getName());
                    uploadedFileLabel.setStyle("-fx-text-fill: green; -fx-font-style: italic;");

                    reuploadButton.setVisible(true);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upload Berhasil");
                    alert.setHeaderText(null);
                    alert.setContentText("File berhasil disimpan ke: " + targetFile.getAbsolutePath());
                    alert.showAndWait();

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Upload Gagal");
                    alert.setHeaderText(null);
                    alert.setContentText("Terjadi kesalahan saat menyimpan file.");
                    alert.showAndWait();
                    ex.printStackTrace();
                }
            }
        });

        reuploadButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");
        reuploadButton.setVisible(false); // Awalnya disembunyikan

        reuploadButton.setOnAction(e -> {
            if (uploadedFile != null && uploadedFile.exists()) {
                uploadedFile.delete(); // Hapus file yang sudah diupload
            }

            uploadedFile = null;
            uploadedFileLabel.setText("");
            uploadButton.setText("Upload Proposal");
            uploadButton.setDisable(false);
            reuploadButton.setVisible(false);
        });

        Button evalButton = new Button("Evaluasi Proposal");
        evalButton.setStyle("-fx-background-color:rgb(175, 204, 235); -fx-text-fill: white; -fx-font-weight: bold;");
        evalButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Evaluasi Proposal");
            alert.setHeaderText("Fitur evaluasi");
            alert.setContentText("Admin belum memberikan evaluasi");
            alert.showAndWait();
        });

        HBox buttonBox = new HBox(10, editButton, uploadButton, evalButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        VBox uploadSection = new VBox(5, buttonBox, uploadedFileLabel, reuploadButton);
        uploadSection.setAlignment(Pos.CENTER_LEFT);

        VBox card = new VBox(15, title, formGrid, statement, ttdBox, uploadSection);
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
