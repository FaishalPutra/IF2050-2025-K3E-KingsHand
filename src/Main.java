package src;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        Main.primaryStage = stage;
        showLogin(); // Tampilan default saat aplikasi dimulai
    }

    public static void showLogin() {
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Dashboard");
        primaryStage.show();
    }

    // Menampilkan tampilan Dashboard
    public static void showDashboard() {
        DashboardView dashboardView = new DashboardView();
        Scene scene = new Scene(dashboardView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Dashboard");
        primaryStage.show();
    }

    // Menampilkan tampilan Project
    public static void showProjectView() {
        ProjectView projectView = new ProjectView();
        Scene scene = new Scene(projectView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Project");
    }

    // Menampilkan tampilan Proposal
    public static void showProposalView() {
        ProposalView proposalView = new ProposalView();
        Scene scene = new Scene(proposalView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Proposal");
    }

    // Menampilkan tampilan Profil
    public static void showProfileView() {
        ProfileView profileView = new ProfileView();
        Scene scene = new Scene(profileView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Profile");
    }

    // Getter untuk primaryStage agar bisa diakses dari kelas lain
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args); // Memulai aplikasi JavaFX
    }
}
