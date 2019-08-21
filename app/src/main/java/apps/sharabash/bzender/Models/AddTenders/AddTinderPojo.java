package apps.sharabash.bzender.Models.AddTenders;

public class AddTinderPojo {
    private String CategoryID;

    private String TenderName;

    private String CityId;

    private String Address;

    private String StartDateTender;

    private String TenderDescrioption;

    private String EndDateTender;

    public String getCategoryID ()
    {
        return CategoryID;
    }

    public void setCategoryID (String CategoryID)
    {
        this.CategoryID = CategoryID;
    }

    public String getTenderName ()
    {
        return TenderName;
    }

    public void setTenderName (String TenderName)
    {
        this.TenderName = TenderName;
    }

    public String getCityId ()
    {
        return CityId;
    }

    public void setCityId (String CityId)
    {
        this.CityId = CityId;
    }

    public String getAddress ()
    {
        return Address;
    }

    public void setAddress (String Address)
    {
        this.Address = Address;
    }

    public String getStartDateTender ()
    {
        return StartDateTender;
    }

    public void setStartDateTender (String StartDateTender)
    {
        this.StartDateTender = StartDateTender;
    }

    public String getTenderDescrioption ()
    {
        return TenderDescrioption;
    }

    public void setTenderDescrioption (String TenderDescrioption)
    {
        this.TenderDescrioption = TenderDescrioption;
    }

    public String getEndDateTender ()
    {
        return EndDateTender;
    }

    public void setEndDateTender (String EndDateTender)
    {
        this.EndDateTender = EndDateTender;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CategoryID = "+CategoryID+", TenderName = "+TenderName+", CityId = "+CityId+", Address = "+Address+", StartDateTender = "+StartDateTender+", TenderDescrioption = "+TenderDescrioption+", EndDateTender = "+EndDateTender+"]";
    }
}

