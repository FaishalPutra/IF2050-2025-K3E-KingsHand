import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.print.PrinterJob;

public class PenanggungJawabView {

    public static void show(Stage stage) {
        String isiProposal = "PENANGGUNG JAWAB KEGIATAN\n\n" +
                "Dengan ini saya menyatakan bahwa saya bertanggung jawab atas kegiatan yang tercantum dalam proposal ini, "
                +
                "dan akan melaksanakan kegiatan tersebut dengan sebaik-baiknya.\n\n" +
                "Apabila di kemudian hari terdapat hal-hal yang tidak sesuai dengan isi proposal ini atau terdapat penyalahgunaan dana, "
                +
                "saya bersedia bertanggung jawab sepenuhnya sesuai dengan ketentuan yang berlaku.\n\n" +
                "Bandar Lampung, 4 Juni 2024\nPenanggung Jawab Kegiatan,\n\n(tanda tangan)\n\nM. Rivaldi Mahari\nNIM 18222028";

        Label label = new Label(isiProposal);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        label.setFont(new Font("Arial", 14));

        Button kembaliBtn = new Button("Kembali");
        kembaliBtn.setOnAction(e -> {
            new ProposalView().start(stage); // Kembali ke ProposalView
        });

        Button cetakBtn = new Button("Cetak");
        cetakBtn.setOnAction(e -> {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(stage)) {
                boolean success = job.printPage(label);
                if (success) {
                    job.endJob();
                }
            }
        });

        VBox root = new VBox(20, label, cetakBtn, kembaliBtn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Penanggung Jawab Kegiatan");
        stage.setScene(scene);
        stage.show();
    }
}
