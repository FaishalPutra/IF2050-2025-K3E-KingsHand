import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfileView {

        private TextField usernameField = new TextField("rivaldiRajinSholat");
        private TextField emailField = new TextField("rivalll@atns.ac.id");
        private TextField lembagaField = new TextField("PT Maju Mundur Bersama");

        public BorderPane getView() {
                BorderPane root = new BorderPane();
                root.setTop(createTopBar());

                VBox contentArea = new VBox();
                contentArea.setPadding(new Insets(20));
                contentArea.setSpacing(20);

                // Header
                HBox profileHeader = new HBox();
                profileHeader.setAlignment(Pos.CENTER_LEFT);
                profileHeader.setSpacing(15);

                Label header = new Label("Profile");
                header.setFont(Font.font("Arial", FontWeight.BOLD, 24));

                Button roleButton = new Button("Penanggung jawab");
                roleButton.setStyle(
                                "-fx-background-color: #FFA500; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
                roleButton.setPadding(new Insets(5, 10, 5, 10));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button logout = new Button("Log Out");
                logout.setTextFill(Color.RED);
                logout.setFont(new Font("Arial", 14));
                logout.setOnAction(e -> Main.showLogin());

                profileHeader.getChildren().addAll(header, roleButton, spacer, logout);
                contentArea.getChildren().add(profileHeader);

                // Body
                HBox profileBody = new HBox();
                profileBody.setSpacing(50);
                profileBody.setPadding(new Insets(20, 0, 0, 0));

                // LEFT PANEL
                VBox leftPanel = new VBox();
                leftPanel.setSpacing(10);
                leftPanel.setPrefWidth(250);
                leftPanel.setAlignment(Pos.TOP_CENTER);

                ImageView profilePic = new ImageView(new Image("file:img/Profile.png"));
                profilePic.setFitHeight(150);
                profilePic.setFitWidth(150);
                profilePic.setPreserveRatio(true);
                profilePic.setClip(new javafx.scene.shape.Circle(75, 75, 75));

                Label name = new Label("Rivaldi Kusuma Putra");
                name.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                name.setPadding(new Insets(10, 0, 0, 0));

                Label kinerjaLabel = new Label("Kinerja");
                kinerjaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                kinerjaLabel.setPadding(new Insets(15, 0, 0, 0));

                HBox trustRating = new HBox(5, new Label("★★★☆☆"), new Label("Kepercayaan"));
                trustRating.setAlignment(Pos.CENTER_LEFT);
                ((Label) trustRating.getChildren().get(0)).setTextFill(Color.ORANGE);

                HBox commRating = new HBox(5, new Label("☆☆☆☆☆"), new Label("Komunikasi"));
                commRating.setAlignment(Pos.CENTER_LEFT);
                ((Label) commRating.getChildren().get(0)).setTextFill(Color.web("#CCCCCC"));

                HBox projectDoneBox = new HBox(5,
                                new Label("Penyelesaian Proyek:"),
                                new Label("92%"));
                ((Label) projectDoneBox.getChildren().get(1)).setFont(Font.font("Arial", FontWeight.BOLD, 14));

                Label usernameHandle = new Label("📷 ririv");
                usernameHandle.setPadding(new Insets(10, 0, 0, 0));
                Label fullnameLink = new Label("🔗 Rivaldi Putra");

                leftPanel.getChildren().addAll(profilePic, name, kinerjaLabel, trustRating, commRating, projectDoneBox,
                                usernameHandle, fullnameLink);

                // RIGHT PANEL
                VBox rightPanel = new VBox();
                rightPanel.setSpacing(20);
                rightPanel.setPrefWidth(550);

                usernameField.setDisable(true);
                emailField.setDisable(true);
                lembagaField.setDisable(true);

                VBox infoRowsContainer = new VBox(15);
                infoRowsContainer.getChildren().addAll(
                                createInfoRow("Username", usernameField),
                                createInfoRow("Email", emailField),
                                createInfoRow("ID Administrator", new Label("Admin-1")),
                                createInfoRow("Lembaga", lembagaField),
                                createInfoRow("Tanggal Bergabung", new Label("12-02-2025")));

                // Tombol Edit Profile & Save
                Button editProfileBtn = new Button("Edit Profile");
                editProfileBtn.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white; -fx-font-weight: bold;");

                Button saveProfileBtn = new Button("Save");
                saveProfileBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                saveProfileBtn.setVisible(false); // awalnya disembunyikan

                editProfileBtn.setOnAction(e -> {
                        usernameField.setDisable(false);
                        emailField.setDisable(false);
                        lembagaField.setDisable(false);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Edit Profil");
                        alert.setHeaderText("Profil dibuka untuk pengeditan");
                        alert.setContentText("Silakan ubah data yang dibutuhkan.");
                        alert.showAndWait();

                        saveProfileBtn.setVisible(true); // tampilkan tombol save
                });

                saveProfileBtn.setOnAction(e -> {
                        // Logika penyimpanan data (bisa diintegrasikan dengan backend/database)
                        usernameField.setDisable(true);
                        emailField.setDisable(true);
                        lembagaField.setDisable(true);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Simpan Profil");
                        alert.setHeaderText("Profil Disimpan");
                        alert.setContentText("Data profil telah disimpan.");
                        alert.showAndWait();

                        saveProfileBtn.setVisible(false); // sembunyikan kembali tombol save setelah disimpan
                });

                HBox settingsIcons = new HBox(20);
                settingsIcons.setAlignment(Pos.TOP_RIGHT);
                settingsIcons.getChildren().addAll(editProfileBtn, saveProfileBtn);

                infoRowsContainer.getChildren().add(settingsIcons);

                HBox projectProposalCards = new HBox(30);
                projectProposalCards.getChildren().addAll(
                                createStatisticCard("Total Project", "18", "https://i.imgur.com/project_icon.png"),
                                createStatisticCard("Total Proposal Diajukan", "22",
                                                "https://i.imgur.com/proposal_icon.png"));

                rightPanel.getChildren().addAll(infoRowsContainer, projectProposalCards);
                profileBody.getChildren().addAll(leftPanel, rightPanel);
                contentArea.getChildren().add(profileBody);
                root.setCenter(contentArea);

                // Footer
                HBox footer = new HBox();
                footer.setPadding(new Insets(20, 30, 20, 30));
                footer.setAlignment(Pos.BOTTOM_LEFT);
                footer.setStyle("-fx-background-color: #F8F8F8;");

                Button download = new Button("📥 Profile Download");
                download.setStyle(
                                "-fx-background-color: #557CFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
                download.setPrefWidth(180);
                download.setPrefHeight(40);

                Region spacerFooter = new Region();
                HBox.setHgrow(spacerFooter, Priority.ALWAYS);

                Label callCenter = new Label("📞 Call Center\n02121212121");
                callCenter.setTextFill(Color.GREEN);
                callCenter.setStyle("-fx-font-size: 12px;");
                callCenter.setAlignment(Pos.CENTER_RIGHT);

                footer.getChildren().addAll(download, spacerFooter, callCenter);
                root.setBottom(footer);

                return root;
        }

        private HBox createInfoRow(String labelText, Control valueNode) {
                Label label = new Label(labelText + ":");
                label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
                HBox row = new HBox(15, label, valueNode);
                row.setAlignment(Pos.CENTER_LEFT);
                return row;
        }

        private VBox createStatisticCard(String title, String value, String iconUrl) {
                VBox card = new VBox(10);
                card.setPadding(new Insets(15));
                card.setAlignment(Pos.CENTER_LEFT);
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 8; -fx-background-radius: 8;");
                card.setPrefWidth(200);

                ImageView icon = new ImageView(new Image(iconUrl));
                icon.setFitHeight(32);
                icon.setFitWidth(32);

                Label titleLabel = new Label(title);
                titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                Label valueLabel = new Label(value);
                valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                valueLabel.setTextFill(Color.web("#557CFF"));

                card.getChildren().addAll(icon, titleLabel, valueLabel);
                return card;
        }

        private HBox createTopBar() {
                HBox topBar = new HBox();
                topBar.setStyle("-fx-background-color: #102030; -fx-padding: 15 30;");
                topBar.setAlignment(Pos.CENTER_LEFT);

                ImageView logoImageView = new ImageView(new Image("file:img/logo.png"));
                logoImageView.setFitWidth(24);
                logoImageView.setFitHeight(24);

                Label logo = new Label("King's Hand");
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

                Label emailIcon = new Label("\uD83D\uDCEC");
                Label bellIcon = new Label("\uD83D\uDD14");
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
}
