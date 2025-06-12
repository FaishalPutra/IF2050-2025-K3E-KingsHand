import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DetailProject {

    private final Scene scene;
    private File uploadedFileKeuangan;
    private File uploadedFilePerkembangan;

    public DetailProject(String titleText, String deskripsi, String lokasi,
            String target, String status, String nilai) {

        // Header
        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: #ddd; -fx-text-fill: #000; -fx-background-radius: 6;");
        backButton.setOnAction(e -> Main.showProjectView());

        HBox headerBar = new HBox(backButton);
        headerBar.setPadding(new Insets(15, 30, 15, 30));
        headerBar.setAlignment(Pos.CENTER_LEFT);
        headerBar.setStyle("-fx-background-color: #F1F1F1; -fx-border-color: #DDD;");

        // Info Section
        Label tag = new Label("Detail Project");
        tag.setStyle(
                "-fx-background-color: orange; -fx-text-fill: white; -fx-padding: 4 10 4 10; -fx-background-radius: 8;");

        ImageView banner = new ImageView(new Image("file:assets/images/header.png"));
        banner.setFitWidth(1000);
        banner.setPreserveRatio(true);

        Label title = new Label(titleText);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#6E6E00"));

        Label descLabel = new Label(deskripsi);
        descLabel.setWrapText(true);

        VBox infoSection = new VBox(10, tag, banner, title, descLabel);
        infoSection.setPadding(new Insets(20));
        infoSection.setStyle(
                "-fx-background-color: #F9F9F9; -fx-background-radius: 8; -fx-border-color: #DDD; -fx-border-radius: 8;");

        // Chart Section
        NumberAxis xAxis = new NumberAxis(1, 7, 1);
        xAxis.setLabel("Month");
        xAxis.setTickLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return switch (object.intValue()) {
                    case 1 -> "Jan";
                    case 2 -> "Feb";
                    case 3 -> "Mar";
                    case 4 -> "Apr";
                    case 5 -> "May";
                    case 6 -> "Jun";
                    case 7 -> "Jul";
                    default -> "";
                };
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Project Statistik");
        lineChart.setLegendVisible(true);
        lineChart.setAnimated(false);
        lineChart.setPrefHeight(300);
        lineChart.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #DDD; -fx-border-radius: 8;");

        XYChart.Series<Number, Number> us = new XYChart.Series<>();
        us.setName("US");
        us.getData().addAll(
                new XYChart.Data<>(1, 20), new XYChart.Data<>(2, 35),
                new XYChart.Data<>(3, 30), new XYChart.Data<>(4, 45),
                new XYChart.Data<>(5, 38), new XYChart.Data<>(6, 25),
                new XYChart.Data<>(7, 32));

        XYChart.Series<Number, Number> uk = new XYChart.Series<>();
        uk.setName("UK");
        uk.getData().addAll(
                new XYChart.Data<>(1, 40), new XYChart.Data<>(2, 30),
                new XYChart.Data<>(3, 25), new XYChart.Data<>(4, 20),
                new XYChart.Data<>(5, 22), new XYChart.Data<>(6, 19),
                new XYChart.Data<>(7, 21));

        lineChart.getData().addAll(us, uk);

        VBox chartSection = new VBox(10, new Label("Statistik Proyek"), lineChart);
        chartSection.setPadding(new Insets(20));
        chartSection.setStyle(
                "-fx-background-color: #F9F9F9; -fx-background-radius: 8; -fx-border-color: #DDD; -fx-border-radius: 8;");

        // Detail Box
        Label lokasiLabel = makeDetailLabel("Lokasi: " + lokasi);
        Label targetLabel = makeDetailLabel("Target: " + target);
        Label statusLabel = makeDetailLabel("Status: " + status);
        Label nilaiLabel = makeDetailLabel("Investasi: " + nilai);

        VBox detailBox = new VBox(10, lokasiLabel, targetLabel, statusLabel, nilaiLabel);
        detailBox.setPadding(new Insets(20));
        detailBox.setStyle(
                "-fx-background-color: #F9F9F9; -fx-background-radius: 8; -fx-border-color: #DDD; -fx-border-radius: 8;");

        // Upload Section
        VBox uploadSection = new VBox(20,
                createUploadSection("Laporan Keuangan", "laporan_keuangan", true),
                createUploadSection("Laporan Perkembangan", "laporan_perkembangan", false));
        uploadSection.setPadding(new Insets(10, 0, 10, 0));

        VBox content = new VBox(30, infoSection, chartSection, detailBox, uploadSection);
        content.setPadding(new Insets(20, 40, 20, 40));
        content.setAlignment(Pos.TOP_LEFT);

        // Tambahkan ScrollPane
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white;");

        // Root dengan header dan ScrollPane
        VBox root = new VBox(headerBar, scrollPane);
        root.setStyle("-fx-background-color: white;");

        scene = new Scene(root, 1100, 800);

    }

    private Label makeDetailLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.web("#555"));
        return label;
    }

    private VBox createUploadSection(String labelText, String folderName, boolean isKeuangan) {
        Label label = new Label(labelText + ":");
        Button uploadButton = new Button("Upload " + labelText);
        Button reuploadButton = new Button("Hapus / Upload Ulang");
        Label uploadedFileLabel = new Label();
        uploadedFileLabel.setStyle("-fx-font-style: italic;");
        reuploadButton.setVisible(false);

        String uploadColor = isKeuangan ? "#28a745" : "#007bff";
        uploadButton
                .setStyle("-fx-background-color: " + uploadColor + "; -fx-text-fill: white; -fx-font-weight: bold;");
        reuploadButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih " + labelText);
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                    new FileChooser.ExtensionFilter("All Files", "."));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                try {
                    File targetDir = new File(folderName);
                    if (!targetDir.exists())
                        targetDir.mkdirs();
                    File targetFile = new File(targetDir, selectedFile.getName());
                    Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    if (isKeuangan)
                        uploadedFileKeuangan = targetFile;
                    else
                        uploadedFilePerkembangan = targetFile;

                    uploadedFileLabel.setText("📄 " + targetFile.getName());
                    uploadedFileLabel.setTextFill(isKeuangan ? Color.GREEN : Color.BLUE);
                    uploadButton.setText("Laporan Terupload");
                    uploadButton.setDisable(true);
                    reuploadButton.setVisible(true);
                    new Alert(Alert.AlertType.INFORMATION,
                            labelText + " berhasil disimpan ke: " + targetFile.getAbsolutePath()).showAndWait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Gagal upload " + labelText + ".").showAndWait();
                }
            }
        });

        reuploadButton.setOnAction(e -> {
            File file = isKeuangan ? uploadedFileKeuangan : uploadedFilePerkembangan;
            if (file != null && file.exists())
                file.delete();

            if (isKeuangan)
                uploadedFileKeuangan = null;
            else
                uploadedFilePerkembangan = null;

            uploadedFileLabel.setText("");
            uploadButton.setText("Upload " + labelText);
            uploadButton.setDisable(false);
            reuploadButton.setVisible(false);
        });

        VBox box = new VBox(5, label, uploadButton, uploadedFileLabel, reuploadButton);
        box.setPadding(new Insets(10));
        box.setStyle(
                "-fx-background-color: #F9F9F9; -fx-background-radius: 8; -fx-border-color: #DDD; -fx-border-radius: 8;");
        return box;
    }

    public Scene getScene() {
        return scene;
    }
}