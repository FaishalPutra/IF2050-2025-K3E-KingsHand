
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ProjectView;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        showDashboard(); // default view
    }

    public static void showDashboard() {
        DashboardView dashboardView = new DashboardView();
        Scene scene = new Scene(dashboardView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Dashboard");
        primaryStage.show();
    }

    public static void showProjectView() {
        ProjectView projectView = new ProjectView();
        Scene scene = new Scene(projectView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Project");
    }

    public static void showProposalView() {
        ProposalView proposalView = new ProposalView();
        Scene scene = new Scene(proposalView.getView(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("King's Hand - Proposal");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
