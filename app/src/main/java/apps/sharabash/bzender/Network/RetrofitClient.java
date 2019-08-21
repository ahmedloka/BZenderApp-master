package apps.sharabash.bzender.Network;



import retrofit2.Retrofit;

import static apps.sharabash.bzender.Utills.Constant.BASE_URL_HTTP;


class RetrofitClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_HTTP)
                    .build();
        }
        return retrofit;
    }
}