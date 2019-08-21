package apps.sharabash.bzender.activities.Tenders;

import java.util.List;

import apps.sharabash.bzender.Models.AddTenders.TendersModelResponse;
import apps.sharabash.bzender.Models.allTinders.car.AllTender;
import apps.sharabash.bzender.Models.allTinders.electrical.AllTenderElectrical;

interface TendersInterface {
    void handleCategoryList(List<TendersModelResponse> tendersModelResponses);

    void getAllTenderList(List<AllTender> allTenders);

    void getAllTenderElectrical(List<AllTenderElectrical> allTenderElectricals);

}
