package apps.sharabash.bzender.Models.notification;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationResponse {
    private List<Notifications> Notifications;

    public List<Notifications> getNotifications() {
        return Notifications;
    }

    public void setNotifications(List<Notifications> Notifications) {
        this.Notifications = Notifications;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [Notifications = " + Notifications + "]";
    }
}
