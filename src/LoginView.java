
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class LoginView {

    private VBox loginBox;
    private VBox rightBox;
    private HBox rootLayout;
    private BorderPane layout;

    private TextField emailField;
    private PasswordField passwordField;
    private Label messageLabel;

    private TextField createTextField(String promptText) {
        TextField field = new TextField();
        field.setPromptText(promptText);
        field.setPrefWidth(346);
        field.setPrefHeight(64);
        field.setStyle(
                "-fx-font-size: 20px;" + // untuk text input
                        "-fx-prompt-text-fill: derive(gray, 50%);" + // warna placeholder
                        "-fx-font-family: 'Arial';");

        return field;
    }

    private PasswordField createPasswordField(String promptText) {
        PasswordField field = new PasswordField();
        field.setPromptText(promptText);
        field.setPrefWidth(346);
        field.setPrefHeight(64);
        field.setStyle(
                "-fx-font-size: 20px;" + // untuk text input
                        "-fx-prompt-text-fill: derive(gray, 50%);" + // warna placeholder
                        "-fx-font-family: 'Arial';");
        return field;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #102030; -fx-padding: 15 30;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("\uD83D\uDC96 King's Hand");
        logo.setTextFill(Color.GOLD);
        logo.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button loginButtonNav = createLoginButton("login");
        HBox rightIcons = new HBox(15,
                new Label("\uD83D\uDCEC"), // Mail
                new Label("\uD83D\uDD14"), // Bell
                loginButtonNav);
        rightIcons.setAlignment(Pos.CENTER_RIGHT);

        topBar.getChildren().addAll(logo, spacer, rightIcons);
        return topBar;
    }

    private Button createLoginButton(String text) {
        Button btn = new Button(text);
        btn.setStyle(
                "-fx-background-color: #FEC845;" +
                        "-fx-text-fill: #102030;" +
                        "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: light;" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-padding: 8 20;");
        return btn;
    }

    public LoginView() {

        // LOGIN LEFT
        Label title = new Label("Log in to your account");
        title.setFont(Font.font("Arial", FontWeight.LIGHT, 38)); // Ukuran font diperbesar

        emailField = createTextField("Fill Your Email");

        passwordField = createPasswordField("Now Your Password");

        CheckBox agreeBox = new CheckBox("I Agree to King's Hand ");
        Hyperlink termsLink = new Hyperlink("terms and condition");
        termsLink.setFont(Font.font("Arial", 14));
        HBox termsBox = new HBox(agreeBox, termsLink);
        termsBox.setSpacing(8); // Tambah jarak
        termsBox.setAlignment(Pos.CENTER_LEFT);

        Button loginButton = new Button("Login");
        loginButton.setStyle(
                "-fx-background-color: #FEC845;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-weight: light;" +
                        "-fx-font-size: 24px;" +
                        "-fx-background-radius: 30;");
        loginButton.setPrefWidth(346);
        loginButton.setPrefHeight(60);

        messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        HBox bottomText = new HBox();
        Label registerPrompt = new Label("Don’t have an account yet?");
        Label registerLink = new Label("Contact admin !");
        registerLink.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bottomText.getChildren().addAll(registerPrompt, registerLink);
        bottomText.setSpacing(8);

        loginBox = new VBox(title, emailField, passwordField, termsBox, loginButton, bottomText, messageLabel);
        loginBox.setAlignment(Pos.CENTER_LEFT);
        loginBox.setSpacing(18); // Tambah jarak antar elemen
        loginBox.setPadding(new Insets(60, 40, 60, 80)); // Lebih lega
        loginBox.setPrefWidth(500); // Lebarkan form

        // RIGHT SIDE
        Label welcomeText = new Label("Welcome to\nKing's Hand");
        welcomeText.setFont(Font.font("Arial", FontWeight.LIGHT, 35)); // Besar
        welcomeText.setWrapText(true);

        rightBox = new VBox(welcomeText);
        rightBox.setSpacing(20);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setPadding(new Insets(60, 80, 60, 40));
        rightBox.setPrefWidth(416); // Sesuaikan agar seimbang dengan loginBox

        rootLayout = new HBox(loginBox, rightBox);
        rootLayout.setStyle("-fx-background-color: white;");
        rootLayout.setSpacing(50); // Menambah ruang di antara login dan welcome

        // Controller
        LoginController controller = new LoginController(emailField, passwordField, messageLabel);
        loginButton.setOnAction(e -> controller.handleLogin());

        // WRAP WITH BORDERPANE
        layout = new BorderPane();
        layout.setTop(createTopBar());
        layout.setCenter(rootLayout);
    }

    public BorderPane getView() {
        return layout;
    }

}
