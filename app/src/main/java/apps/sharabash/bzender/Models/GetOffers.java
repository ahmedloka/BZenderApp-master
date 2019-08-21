package apps.sharabash.bzender.Models;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetOffers {
    private List<Offers> Offers;

    public List<Offers> getOffers() {
        return Offers;
    }

    public void setOffers(List<Offers> Offers) {
        this.Offers = Offers;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [Offers = " + Offers + "]";
    }
}
