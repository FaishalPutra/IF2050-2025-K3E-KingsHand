package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Project;
import model.Notification;

public class DashboardController {
    public ObservableList<Project> getProjectList() {
        return FXCollections.observableArrayList(
                new Project("Cafe Le’ de Jainero - Bandung", "Active"),
                new Project("Project membangun sekolah luar biasa di Papua", "Active"),
                new Project("Project membangun sekolah luar biasa di Papua", "Pending"),
                new Project("Project membangun sekolah luar biasa di Papua", "Inactive"));
    }

    public ObservableList<Notification> getNotifications() {
        return FXCollections.observableArrayList(
                new Notification("PENGAJUAN PROPOSAL", "green"),
                new Notification("PENGAJUAN PROPOSAL", "red"),
                new Notification("LAPORAN PERKEMBANGAN", "purple"),
                new Notification("LAPORAN PERKEMBANGAN", "orange"));
    }
}
