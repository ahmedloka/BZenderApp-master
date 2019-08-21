package apps.sharabash.bzender.Models.changePassword;

import org.jetbrains.annotations.NotNull;

public class ChangePasswordModel {

    private String NewPassword;

    private String OldPassword;

    private String ConfirmPassword;

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    @NotNull
    @Override
    public String toString() {
        return "ChangePasswordModel{" +
                "NewPassword='" + NewPassword + '\'' +
                ", OldPassword='" + OldPassword + '\'' +
                ", ConfirmPassword='" + ConfirmPassword + '\'' +
                '}';
    }
}
