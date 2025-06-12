import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectData {

    private final StringProperty no;
    private final StringProperty judul;
    private final StringProperty pj;
    private final StringProperty status;

    public ProjectData(String no, String judul, String pj, String status) {
        this.no = new SimpleStringProperty(no);
        this.judul = new SimpleStringProperty(judul);
        this.pj = new SimpleStringProperty(pj);
        this.status = new SimpleStringProperty(status);
    }

    public String getNo() {
        return no.get();
    }

    public void setNo(String value) {
        no.set(value);
    }

    public StringProperty noProperty() {
        return no;
    }

    public String getJudul() {
        return judul.get();
    }

    public void setJudul(String value) {
        judul.set(value);
    }

    public StringProperty judulProperty() {
        return judul;
    }

    public String getPj() {
        return pj.get();
    }

    public void setPj(String value) {
        pj.set(value);
    }

    public StringProperty pjProperty() {
        return pj;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
