package apps.sharabash.bzender.Models.metadataCar;

import org.jetbrains.annotations.NotNull;

public class TermsAndCondition
{
    private String Description;

    private String Id;

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", Id = "+Id+"]";
    }
}

