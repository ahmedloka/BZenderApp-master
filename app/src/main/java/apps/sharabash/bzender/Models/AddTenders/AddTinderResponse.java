package apps.sharabash.bzender.Models.AddTenders;

import org.jetbrains.annotations.NotNull;

public class AddTinderResponse {

    private int TenderId;

    public int getTenderId() {
        return TenderId;
    }

    public void setTenderId(int tenderId) {
        TenderId = tenderId;
    }

    @NotNull
    @Override
    public String toString() {
        return "AddTinderResponse{" +
                "TenderId=" + TenderId +
                '}';
    }
}
