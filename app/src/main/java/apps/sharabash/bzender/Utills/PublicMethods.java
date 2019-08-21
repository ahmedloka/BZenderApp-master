package apps.sharabash.bzender.Utills;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import apps.sharabash.bzender.R;


class PublicMethods {

    public static void setImg(String url, ImageView image, Context context) {

        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.gry_image)
                .error(R.drawable.gry_image)
                .into(image);


    }
}
