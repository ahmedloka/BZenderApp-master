package apps.sharabash.bzender.adapters;

class ItemModel {

    private String Id;
    private String Name;
    private boolean favourites;
    private String startDate;
    private String EndDate;
    private String count;

    public ItemModel(String id, String name, boolean favourites, String startDate, String endDate, String count) {
        Id = id;
        Name = name;
        this.favourites = favourites;
        this.startDate = startDate;
        EndDate = endDate;
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public boolean isFavourites() {
        return favourites;
    }

    public void setFavourites(boolean favourites) {
        this.favourites = favourites;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
