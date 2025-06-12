
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Arrays;

public class ProjectView {

    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #102030; -fx-padding: 15 30;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        ImageView logoImageView = new ImageView(new Image("file:img/logo.png"));
        logoImageView.setFitWidth(24);
        logoImageView.setFitHeight(24);

        Label logo = new Label("King’s Hand");
        logo.setTextFill(Color.GOLD);
        logo.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        logo.setGraphic(logoImageView);
        logo.setGraphicTextGap(10);

        Button dashboardBtn = createNavButton("Dashboard", Main::showDashboard);
        Button projectBtn = createNavButton("Project", Main::showProjectView);
        Button proposalBtn = createNavButton("Proposal", Main::showProposalView);

        HBox nav = new HBox(30, dashboardBtn, projectBtn, proposalBtn);
        nav.setPadding(new Insets(0, 0, 0, 60));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

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
        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(30));
        contentBox.setStyle("-fx-background-color: #F9F9F9;");

        Label header = new Label("Daftar Proyek");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label badge = new Label("Penanggung Jawab");
        badge.setStyle(
                "-fx-background-color: orange; -fx-text-fill: white; -fx-padding: 4px 10px; -fx-background-radius: 15px;");

        HBox headerRow = new HBox(10, header, badge);
        headerRow.setAlignment(Pos.CENTER_LEFT);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");

        VBox projectList = new VBox(15);
        projectList.setPadding(new Insets(10));

        // Contoh data proyek
        projectList.getChildren().addAll(Arrays.asList(
                createExpandableCard("Cafe Le’ de Jainero – Bandung", "Oleh: Nusla Wreckerman",
                        "Kedai kopi modern dengan konsep fusion tropis dan sentuhan seni lokal.",
                        "Dago, Bandung", "Mahasiswa & wisatawan lokal", "Dalam perencanaan", "500 juta"),
                createExpandableCard("Sekolah Luar Biasa Papua", "Oleh: Tim Nusla",
                        "Pembangunan sekolah khusus untuk anak berkebutuhan di Papua.",
                        "Jayapura, Papua", "Anak berkebutuhan khusus", "Dalam proses", "1 miliar")));

        scrollPane.setContent(projectList);

        contentBox.getChildren().addAll(headerRow, scrollPane);

        BorderPane root = new BorderPane();
        root.setTop(createTopBar());
        root.setCenter(contentBox);

        return root;
    }

    /**
     * Membuat kartu proyek yang bisa diklik untuk expand detail.
     */
    private TitledPane createExpandableCard(String titleText, String subtitleText,
            String deskripsi, String lokasi, String target, String status, String nilai) {
        VBox content = new VBox(8);
        content.setPadding(new Insets(15));
        content.setStyle("-fx-background-color: #fff; -fx-border-color: #ddd; -fx-border-radius: 10; " +
                "-fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 4, 0, 0, 2);");

        Label desc = new Label(" " + deskripsi);
        Label loc = new Label("Lokasi: " + lokasi);
        Label tgt = new Label("Target: " + target);
        Label sts = new Label("Status: " + status);
        Label inv = new Label("Investasi: " + nilai);

        Arrays.asList(desc, loc, tgt, sts, inv).forEach(lbl -> {
            lbl.setWrapText(true);
            lbl.setStyle("-fx-text-fill: #444;");
        });

        Button detailBtn = new Button("Lihat Detail");
        detailBtn.setStyle(
                "-fx-background-color: #FDC73E; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8px;");
        detailBtn.setOnAction(e -> showDetailDialog(titleText, deskripsi, lokasi, target, status, nilai));

        content.getChildren().addAll(desc, loc, tgt, sts, inv, detailBtn);

        TitledPane pane = new TitledPane(titleText + "\n" + subtitleText, content);
        pane.setExpanded(false);
        pane.setCollapsible(true);
        pane.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        return pane;
    }

    /**
     * Menampilkan dialog detail saat tombol diklik.
     */
    private void showDetailDialog(String title, String deskripsi, String lokasi,
            String target, String status, String nilai) {
        DetailProject detailPage = new DetailProject(title, deskripsi, lokasi, target, status, nilai);
        Scene detailScene = detailPage.getScene();
        Main.getPrimaryStage().setScene(detailScene);
    }
}
