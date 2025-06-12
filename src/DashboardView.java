import controller.DashboardController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.Notification;
import model.Project;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DashboardView {
    private BorderPane root;
    private DashboardController controller = new DashboardController();

    public DashboardView() {
        root = new BorderPane();
        root.setTop(createTopBar());

        VBox centerContent = new VBox(20);
        centerContent.setPadding(new Insets(30, 40, 30, 40)); // Padding kiri-kanan cukup luas

        // Greeting Section
        Label greeting = new Label("Hello, Yangmul");
        greeting.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        Label role = new Label("Penanggung jawab");
        role.setStyle(
                "-fx-background-color: #FFA500; -fx-text-fill: white; -fx-padding: 5 12; -fx-background-radius: 15;");
        HBox greetingBox = new HBox(10, greeting, role);
        greetingBox.setAlignment(Pos.CENTER_LEFT);

        Label dashboardLabel = new Label("Dashboard");
        dashboardLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        dashboardLabel.setPadding(new Insets(10, 0, 10, 0));

        // Content Area
        HBox projectSummary = createProjectSummary();
        VBox projectListBox = new VBox(10, new Label("List Project"), createProjectList());
        projectListBox.setPrefWidth(700);

        VBox notifBox = createNotifications();
        notifBox.setPrefWidth(400);

        HBox mainContent = new HBox(30, projectListBox, notifBox);
        centerContent.getChildren().addAll(greetingBox, dashboardLabel, projectSummary, mainContent);

        root.setCenter(centerContent);
    }

    public BorderPane getView() {
        return root;
    }

    public void start(Stage stage) {
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

    private HBox createProjectSummary() {
        VBox activeBox = createStatusBox("Project Active", 7, "#6BE3A2");
        VBox inactiveBox = createStatusBox("Project Inactive", 3, "#F46C6B");
        VBox pendingBox = createStatusBox("Project Pending", 1, "#F6C26B");

        HBox hbox = new HBox(20, activeBox, inactiveBox, pendingBox);
        hbox.setPadding(new Insets(10, 0, 20, 0));
        return hbox;
    }

    private VBox createStatusBox(String title, int count, String color) {
        VBox box = new VBox(8);
        box.setPadding(new Insets(15));
        box.setPrefSize(200, 100);
        box.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #DDD; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        Label icon = new Label("\u265B");
        icon.setTextFill(Color.web(color));
        icon.setFont(Font.font(24));

        Label label = new Label(title);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 13));

        Label number = new Label(String.valueOf(count));
        number.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        number.setTextFill(Color.web(color));

        box.getChildren().addAll(icon, label, number);
        return box;
    }

    public String getDescription() {
        return getDescription();
    }

    private VBox createProjectList() {
        VBox listBox = new VBox(10);
        listBox.setPadding(new Insets(10));
        listBox.setStyle("-fx-background-color: white; -fx-border-radius: 15; -fx-background-radius: 15;");

        for (Project project : controller.getProjectList()) {
            VBox itemBox = new VBox(4);
            itemBox.setPadding(new Insets(10));
            itemBox.setStyle(
                    "-fx-background-color: #F9F9F9; -fx-border-color: #DDD; -fx-border-radius: 10; -fx-background-radius: 10;");
            itemBox.setCursor(Cursor.HAND);

            Label name = new Label(project.getName());
            name.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            Label status = new Label("Status: " + project.getStatus());
            status.setTextFill(getStatusColor(project.getStatus()));
            status.setFont(Font.font(13));

            itemBox.getChildren().addAll(name, status);

            // Tambahkan interaksi klik
            itemBox.setOnMouseClicked(e -> {
                Alert detail = new Alert(Alert.AlertType.INFORMATION);
                detail.setTitle("Detail Proyek");
                detail.setHeaderText(project.getName());
                detail.setContentText("Status: " + project.getStatus() + "\n\nDeskripsi: " + project.getDescription());
                detail.showAndWait();
            });

            listBox.getChildren().add(itemBox);
        }

        return listBox;
    }

    private VBox createNotifications() {
        VBox notifBox = new VBox(15);
        notifBox.setPadding(new Insets(20));
        notifBox.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #CCCCCC; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20;");

        Label title = new Label("Notifikasi Admin");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox notifList = new VBox(10);
        for (Notification notif : controller.getNotifications()) {
            Button notifBtn = new Button(notif.getMessage());
            notifBtn.setMaxWidth(Double.MAX_VALUE);
            notifBtn.setStyle("-fx-background-color: " + getNotifColor(notif.getType()) +
                    "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 30; -fx-padding: 10 20;");
            notifBtn.setOnAction(
                    e -> new Alert(Alert.AlertType.INFORMATION, notif.getMessage(), ButtonType.OK).showAndWait());
            notifList.getChildren().add(notifBtn);
        }

        notifBox.getChildren().addAll(title, notifList);
        return notifBox;
    }

    private Color getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "active":
                return Color.web("#6BE3A2");
            case "inactive":
                return Color.web("#F46C6B");
            case "pending":
                return Color.web("#F6C26B");
            default:
                return Color.GRAY;
        }
    }

    private String getNotifColor(String type) {
        switch (type.toLowerCase()) {
            case "proposal":
                return "#6B8AF6";
            case "laporan perkembangan":
                return "#F6C26B";
            default:
                return "#AAAAAA";
        }
    }
}
