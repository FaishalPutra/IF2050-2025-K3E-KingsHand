package model;

public class Project {
    private String name;
    private String status;
    private String description;

    public Project(String name, String status) {
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
