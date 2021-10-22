package nz.ac.wgtn.ecs.CarbonFootprint;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = findViewById(R.id.web_view);
        String url = getIntent().getStringExtra("URL");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}