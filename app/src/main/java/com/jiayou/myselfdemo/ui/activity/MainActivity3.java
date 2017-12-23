package com.jiayou.myselfdemo.ui.activity;

import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jiayou.myselfdemo.R;
import com.myself.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity3 extends BaseActivity {


    @BindView(R.id.expandableList_view)
    ExpandableListView mExpandableListView;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.activity_add_recording)
    LinearLayout mActivityAddRecording;

    @Override
    public int initRootLayout() {
        return R.layout.activity_add_recording;
    }

    @Override
    public void initViews() {
//        mWeb.getSettings().setJavaScriptEnabled(true);
//        mWeb.setWebChromeClient(new WebChromeClient());
//        mWeb.requestFocus();
////              webview.loadUrl("file:///android_asset/risktest.html");
//        mWeb.loadUrl("http://ow365.cn/?i=14433&furl=http://img.hdkt100.com/img/upload/2017/12/18/5-168-20071621490.rar");
//        mWeb.setWebViewClient(new WebViewClient());
//        mWeb.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                Toast.makeText(mActivitySelf, "xxxxxx", Toast.LENGTH_SHORT).show();
//            }
//        });
        WebSettings settings = mWeb.getSettings();
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.requestFocus();
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Toast.makeText(mActivitySelf, "xx", Toast.LENGTH_SHORT).show();
            }
        });

        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        String cacheDirPath = mActivitySelf.getFilesDir().getAbsolutePath() + "/webcache";
//        设置数据库缓存路径
        mWeb.getSettings().setDatabasePath(cacheDirPath);
//        设置  Application Caches 缓存目录
        mWeb.getSettings().setAppCachePath(cacheDirPath);
        settings.setAppCacheEnabled(true);//是否使用缓存
        settings.setDomStorageEnabled(true);//DOM Storage


        settings.setDatabaseEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);//是否使用内置缩放机制，默认false
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件


//        settings.setSupportMultipleWindows(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);


//        // 打开页面时， 自适应屏幕
        settings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        settings.setLoadWithOverviewMode(true);
//        // 支持获取手势焦点
        mWeb.loadUrl("http://ow365.cn/?i=14433&furl=http://img.hdkt100.com/img/upload/2017/12/18/5-168-20071621490.rar");
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initOthers() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
