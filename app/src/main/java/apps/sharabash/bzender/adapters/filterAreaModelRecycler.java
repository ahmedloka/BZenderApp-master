package apps.sharabash.bzender.adapters;

public class filterAreaModelRecycler {


    public static final int TEXT_TYPE=0;




//    public String MerchantLogo;
    public final String CityName;
    public final String flag;
    private final int data;
    public int id;
//    public String categoryname;
//    public String fristDesc;
   // public String getLocation;




    public filterAreaModelRecycler(String CityName, String flag, int data)
    {
        this.CityName       =      CityName;
        this.flag              =      flag;
        this.data              =      data;
//        this.MerchantLogo        =      MerchantLogo;
//        this.categoryLogo        =      categoryLogo;
//        this.categoryname        =      categoryname;
//        this.fristDesc           =      fristDesc;
      //  this.getLocation          =      getLocation;
    }
    public filterAreaModelRecycler(String CityName, String flag, int data,int id)
    {
        this.CityName       =      CityName;
        this.flag              =      flag;
        this.data              =      data;
        this.id              =      id;
//        this.MerchantLogo        =      MerchantLogo;
//        this.categoryLogo        =      categoryLogo;
//        this.categoryname        =      categoryname;
//        this.fristDesc           =      fristDesc;
        //  this.getLocation          =      getLocation;
    }


}
