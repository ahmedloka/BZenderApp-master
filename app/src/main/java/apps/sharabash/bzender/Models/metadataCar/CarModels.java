package apps.sharabash.bzender.Models.metadataCar;

import org.jetbrains.annotations.NotNull;

public class CarModels
{
    private String NameLT;

    private String Id;

    private String Name;

    public String getNameLT ()
    {
        return NameLT;
    }

    public void setNameLT (String NameLT)
    {
        this.NameLT = NameLT;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [NameLT = "+NameLT+", Id = "+Id+", Name = "+Name+"]";
    }
}