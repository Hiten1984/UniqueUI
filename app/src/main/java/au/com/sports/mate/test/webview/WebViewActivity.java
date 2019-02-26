package au.com.sports.mate.test.webview;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import au.com.sports.mate.test.R;
import au.com.sports.mate.test.util.Utila;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView webview;

    boolean insideView = true;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        setupViews();
    }

    private void setupViews() {
        String url = "https://sportsmate.typeform.com/to/xDChFS";
//        String url = "https://www.google.com";

        if (TextUtils.isEmpty(url))
            return;

        height = Utila.getCurrentScreenHeight(this) - Utila.getPixelsForDip(this, insideView ? 136 : 20);

        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setAllowContentAccess(true);

        if (Build.VERSION.SDK_INT >= 16) {
            webview.getSettings().setAllowFileAccessFromFileURLs(true);
            webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }

        webview.setWebViewClient(new SMWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
//        webview.loadUrl(Utila.replaceUrlParams(url));
        webview.loadUrl("file:///android_asset/test.html");
    }

    private class SMWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            try {
                if (url.contains("market://")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                else
                    return false;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            webview.getLayoutParams().height = height;
//            webview.loadUrl("javascript:document.body.style.margin=\"8%\"; void 0");
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }
    }
}
