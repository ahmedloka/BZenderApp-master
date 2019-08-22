package apps.sharabash.bzender.activities.webViewContactUs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "webViewActivity";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Log.d(TAG, "WebViewActivity: " + "OPENED");
        initViews();
    }

    private void initViews() {
        mWebView = findViewById(R.id.mWebView);

        Intent intent = getIntent();
        try {
            String url = intent.getExtras().get(Constant.URL_KEY).toString();
            Log.d(TAG, "initViews: " + url);
            openWebSite(url);
        } catch (NullPointerException e) {
            Constant.showErrorDialog(this, getString(R.string.please_downlaod_whats_app));
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void openWebSite(String url) {
        mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true); // enable javascript
        final Activity activity = this;

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebView.loadUrl(url);
        setContentView(mWebView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
        Animatoo.animateSlideLeft(this);
    }
}
