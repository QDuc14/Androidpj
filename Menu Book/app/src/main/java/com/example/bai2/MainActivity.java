package com.example.bai2;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private ScrollView scrollView;
    private ImageView image_1, image_2, image_3, image_4, image_5, image_6, image_7, image_8, image_9, image_10;
    private ImageView sub_image_1, sub_image_2, sub_image_3, sub_image_4, sub_image_5, sub_image_6, sub_image_7, sub_image_8, sub_image_9, sub_image_10;
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10;
    private WebView webView;
    private String main_url = "";
    private boolean clearHistory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateVariables();

        SetOnClickEvent(
                image_1,
                tv_1,
                sub_image_1,
                "https://www.cookingchanneltv.com/recipes/kelsey-nixon/slow-cooker-shredded-pork-1961553",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/7/7/0/CCKEL210_Slow-Cooker-Shredded-Pork_s4x3.jpg.rend.hgtvcom.826.620.suffix/1416333647975.jpeg"
        );

        SetOnClickEvent(
                image_2,
                tv_2,
                sub_image_2,
                "https://www.cookingchanneltv.com/recipes/chicken-or-steak-with-balsamic-bbq-sauce-1953568",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/9/3/0/GC_chicken-with-balsamic-bbq-sauce_s4x3.jpg.rend.hgtvcom.826.620.suffix/1373920597169.jpeg"
        );

        SetOnClickEvent(
                image_3,
                tv_3,
                sub_image_3,
                "https://www.cookingchanneltv.com/recipes/berry-trifle-1946422",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/9/16/0/L52029F4_Berry-Trifle_s4x3.jpg.rend.hgtvcom.826.620.suffix/1358413665402.jpeg"
        );

        SetOnClickEvent(
                image_4,
                tv_4,
                sub_image_4,
                "https://www.cookingchanneltv.com/recipes/chocolate-diablo-cookies-1961386",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/6/8/0/CCEAT109_Tacofino-Chocolate-Diablo-Cookies_s4x3.jpg.rend.hgtvcom.826.620.suffix/1380837375253.jpeg"
        );

        SetOnClickEvent(
                image_5,
                tv_5,
                sub_image_5,
                "https://www.cookingchanneltv.com/recipes/miyoko-mcphersons-salt-grilled-mackerel-shioyaki-2235012",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/video/0/02/023/0233/0233564.jpg.rend.hgtvcom.826.620.suffix/1481904081087.jpeg"
        );

        SetOnClickEvent(
                image_6,
                tv_6,
                sub_image_6,
                "https://www.cookingchanneltv.com/recipes/picnic-perfect-fried-chicken-3416099",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2014/6/2/0/CC_top-summer-picnic-perfect-fried-chicken-recipe_s4x3.jpg.rend.hgtvcom.826.620.suffix/1401719863117.jpeg"
        );

        SetOnClickEvent(
                image_7,
                tv_7,
                sub_image_7,
                "https://www.cookingchanneltv.com/recipes/eden-grinshpan/pistachio-dusted-rose-glazed-yeast-doughnuts-3415285",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2014/7/3/0/CCDON200_Pistachio-Rose-glazed-donuts-recipe_s4x3.jpg.rend.hgtvcom.826.620.suffix/1404410834141.jpeg"
        );

        SetOnClickEvent(
                image_8,
                tv_8,
                sub_image_8,
                "https://www.cookingchanneltv.com/recipes/smoked-rib-eye-roasts-2109557",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/11/5/0/CCMFF106_Jalapeno-and-garlic-stuffed-smoked-rib-roast-recipe_s4x3.jpg.rend.hgtvcom.826.620.suffix/1357781068398.jpeg"
        );

        SetOnClickEvent(
                image_9,
                tv_9,
                sub_image_9,
                "https://www.cookingchanneltv.com/recipes/spicy-hoisin-glazed-eggplant-1950448",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/9/16/0/GL0804_Spicy_Hoisin_Glazed_Eggplant.jpg.rend.hgtvcom.826.620.suffix/1397238648468.jpeg"
        );

        SetOnClickEvent(
                image_10,
                tv_10,
                sub_image_10,
                "https://www.cookingchanneltv.com/recipes/giada-de-laurentiis/warm-chocolate-cakes-with-berries-1914071",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/3/7/11/ei1a03_chocolate_cake.jpg.rend.hgtvcom.826.620.suffix/1386281589487.jpeg"
        );

    }

    private void SetOnClickEvent(ImageView image, TextView text, ImageView sub_image, String url, String sub_url){
        image.setOnClickListener(v -> {
            loadURL(url);
            main_url = url;
        });
        text.setOnClickListener(v -> {
            loadURL(url);
            main_url = url;
        });
        sub_image.setOnClickListener(v -> {
            loadURL(sub_url);
            main_url = url;
        });
    }

    private void loadURL(String url){
        if(webView.getVisibility() == View.GONE){
            webView.setVisibility(View.VISIBLE);
            webView.onResume();
        }
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                if(clearHistory){
                    clearHistory = false;
                    webView.clearHistory();
                }
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl(url);
        clearHistory = true;
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        scrollView.setVisibility(View.GONE);
    }

    private void CreateVariables(){
        scrollView = findViewById(R.id.scroll_view);

        webView = findViewById(R.id.webview);

        image_1 = findViewById(R.id.image_1);
        tv_1 = findViewById(R.id.tv_1);
        sub_image_1 = findViewById(R.id.sub_image_1);

        image_2 = findViewById(R.id.image_2);
        tv_2 = findViewById(R.id.tv_2);
        sub_image_2 = findViewById(R.id.sub_image_2);

        image_3 = findViewById(R.id.image_3);
        tv_3 = findViewById(R.id.tv_3);
        sub_image_3 = findViewById(R.id.sub_image_3);

        image_4 = findViewById(R.id.image_4);
        tv_4 = findViewById(R.id.tv_4);
        sub_image_4 = findViewById(R.id.sub_image_4);

        image_5 = findViewById(R.id.image_5);
        tv_5 = findViewById(R.id.tv_5);
        sub_image_5 = findViewById(R.id.sub_image_5);

        image_6 = findViewById(R.id.image_6);
        tv_6 = findViewById(R.id.tv_6);
        sub_image_6 = findViewById(R.id.sub_image_6);

        image_7 = findViewById(R.id.image_7);
        tv_7 = findViewById(R.id.tv_7);
        sub_image_7 = findViewById(R.id.sub_image_7);

        image_8 = findViewById(R.id.image_8);
        tv_8 = findViewById(R.id.tv_8);
        sub_image_8 = findViewById(R.id.sub_image_8);

        image_9 = findViewById(R.id.image_9);
        tv_9 = findViewById(R.id.tv_9);
        sub_image_9 = findViewById(R.id.sub_image_9);

        image_10 = findViewById(R.id.image_10);
        tv_10 = findViewById(R.id.tv_10);
        sub_image_10 = findViewById(R.id.sub_image_10);

    }

    @Override
    public void onBackPressed(){
        if(webView.getVisibility() == View.GONE){
            super.onBackPressed();
        }

        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            webView.clearHistory();
            webView.clearCache(true);
            webView.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }


}
