package model;

public class Notification {
    private String message;
    private String type; // e.g., "PROPOSAL", "LAPORAN"

    public Notification(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
