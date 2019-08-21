package apps.sharabash.bzender.Models.notification;

import org.jetbrains.annotations.NotNull;

public class Notifications
{
    private String CreationDate;

    private String Message;

    private String UserId;

    private String Id;

    public String getCreationDate ()
    {
        return CreationDate;
    }

    public void setCreationDate (String CreationDate)
    {
        this.CreationDate = CreationDate;
    }

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public String getUserId ()
    {
        return UserId;
    }

    public void setUserId (String UserId)
    {
        this.UserId = UserId;
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
        return "ClassPojo [CreationDate = "+CreationDate+", Message = "+Message+", UserId = "+UserId+", Id = "+Id+"]";
    }
}