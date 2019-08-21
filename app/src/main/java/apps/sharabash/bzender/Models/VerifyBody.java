package apps.sharabash.bzender.Models;

import org.jetbrains.annotations.NotNull;

public class VerifyBody {

    private String Points;

    private String Email;

    private String IsImgProfileUploaded;

    private String FullName;

    private String ImageUrl;

    private String Token;

    public String getPoints ()
    {
        return Points;
    }

    public void setPoints (String Points)
    {
        this.Points = Points;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getIsImgProfileUploaded ()
    {
        return IsImgProfileUploaded;
    }

    public void setIsImgProfileUploaded (String IsImgProfileUploaded)
    {
        this.IsImgProfileUploaded = IsImgProfileUploaded;
    }

    public String getFullName ()
    {
        return FullName;
    }

    public void setFullName (String FullName)
    {
        this.FullName = FullName;
    }

    public String getImageUrl ()
    {
        return ImageUrl;
    }

    public void setImageUrl (String ImageUrl)
    {
        this.ImageUrl = ImageUrl;
    }

    public String getToken ()
    {
        return Token;
    }

    public void setToken (String Token)
    {
        this.Token = Token;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [Points = "+Points+", Email = "+Email+", IsImgProfileUploaded = "+IsImgProfileUploaded+", FullName = "+FullName+", ImageUrl = "+ImageUrl+", Token = "+Token+"]";
    }
}

