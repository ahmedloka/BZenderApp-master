package apps.sharabash.bzender.activities.myBooking;

import java.util.List;

import apps.sharabash.bzender.Models.my_tenders.MyBookingBody;
import apps.sharabash.bzender.Models.my_tenders.MyTendersBody;

public interface MyTenderInterface {
    void getMyooking(MyBookingBody myBookingBody);

    void getMyTender(List<MyTendersBody> myTendersBodyList);


}
