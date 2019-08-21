package apps.sharabash.bzender.activities.Home;

import java.util.List;

import apps.sharabash.bzender.Models.GetOffers;
import apps.sharabash.bzender.Models.home.getCategoryResponse;

public interface homeInterface {
    void handleCategoryList(List<getCategoryResponse> getCategoryResponses);

    void getAllImages(GetOffers getOffers);

}
