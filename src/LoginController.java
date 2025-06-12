
import firebase.FirebaseAuthService;
import firebase.FirestoreService;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private TextField emailField;
    private PasswordField passwordField;
    private Label messageLabel;

    public LoginController(TextField emailField, PasswordField passwordField, Label messageLabel) {
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.messageLabel = messageLabel;
    }

    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            FirebaseAuthService.AuthResponse auth = FirebaseAuthService.login(email, password);
            String name = FirestoreService.getUserName(auth.idToken, auth.uid);
            messageLabel.setText("✅ Hai, " + name + "!");

            // Pindah ke dashboard jika login berhasil
            Main.showDashboard();

        } catch (Exception e) {
            messageLabel.setText("Login gagal: " + e.getMessage());
        }
    }
}
