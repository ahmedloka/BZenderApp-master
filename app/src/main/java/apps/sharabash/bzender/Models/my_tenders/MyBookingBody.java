package apps.sharabash.bzender.Models.my_tenders;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyBookingBody {
    private List<BookingList> BookingList;

    public List<BookingList> getBookingList ()
    {
        return BookingList;
    }

    public void setBookingList (List<BookingList> BookingList)
    {
        this.BookingList = BookingList;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [BookingList = "+BookingList+"]";
    }
}

