package apps.sharabash.bzender.Models.my_tenders;


import org.jetbrains.annotations.NotNull;

public class BookingList
{
    private String CategoryId;

    private String TenderName;

    private String StartDateTender;

    private int StatusId;

    private String Id;

    private String EndDateTender;



    public String getCategoryId ()
    {
        return CategoryId;
    }

    public void setCategoryId (String CategoryId)
    {
        this.CategoryId = CategoryId;
    }

    public String getTenderName ()
    {
        return TenderName;
    }

    public void setTenderName (String TenderName)
    {
        this.TenderName = TenderName;
    }

    public String getStartDateTender ()
    {
        return StartDateTender;
    }

    public void setStartDateTender (String StartDateTender)
    {
        this.StartDateTender = StartDateTender;
    }

    public int getStatusId ()
    {
        return StatusId;
    }

    public void setStatusId (int StatusId)
    {
        this.StatusId = StatusId;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getEndDateTender ()
    {
        return EndDateTender;
    }

    public void setEndDateTender (String EndDateTender)
    {
        this.EndDateTender = EndDateTender;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [CategoryId = "+CategoryId+", TenderName = "+TenderName+", StartDateTender = "+StartDateTender+", StatusId = "+StatusId+", Id = "+Id+", EndDateTender = "+EndDateTender+"]";
    }
}

