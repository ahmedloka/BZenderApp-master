package apps.sharabash.bzender.adapters;

class HomeModel {

    private String Id;
    private String Name;
    private String imageURL;

    public HomeModel(String id, String name, String imageURL) {
        Id = id;
        Name = name;
        this.imageURL = imageURL;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
