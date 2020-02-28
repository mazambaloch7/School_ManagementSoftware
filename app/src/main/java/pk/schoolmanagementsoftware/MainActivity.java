package pk.schoolmanagementsoftware;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.veyo.autorefreshnetworkconnection.AutoRefreshNetworkUtil;
import com.veyo.autorefreshnetworkconnection.CheckNetworkConnectionHelper;
import com.veyo.autorefreshnetworkconnection.listener.OnNetworkConnectionChangeListener;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;
    WebView webView;
    TextView tv_network;
    ImageView imag_network;

    LinearLayout linearLayout;
    private static final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar =findViewById(R.id.sprogressBar2);
        webView=findViewById(R.id.swebview);
//        webView.loadUrl("http://neweramagazine.com/UrduNovelsDashboard/Home#");
//        webView.loadUrl("http://neweramagazine.com/map/index/");
//        webView.loadUrl("http://my.schoolmanagementsoftware.pk/map/index");
        webView.loadUrl(AppURL.url);
//        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.clearSslPreferences();
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearMatches();
        webView.clearFormData();
        webView.getOriginalUrl();
        webView.isPrivateBrowsingEnabled();
        webView.invokeZoomPicker();
//        webView.setNetworkAvailable(true);
//        webView.documentHasImages(true);
        webView.clearCache(false);
        webView.requestFocus();
        webView.requestFocusFromTouch();

        webView.setWebViewClient(new std_ourViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
//        webView.setWebChromeClient(new );
//        setContentView(webView);

//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient());
//
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        // Load the webpage
//        webView.loadUrl("http://google.com/");
//        WebSettings webSettings = appWebView.getSettings();
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDefaultTextEncodingName("utf-8");
//        webSettings.setPluginState(WebSettings.PluginState.ON);
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        appWebView.clearCache(false);
//        appWebView.requestFocus();
//        appWebView.requestFocusFromTouch();
//        appWebView.setWebViewClient(new WebViewClient());
//        appWebView.setWebChromeClient(new WebChromeClient());

//----------------------interNET PART----------------

        tv_network = findViewById(R.id.tv_network);
        imag_network = findViewById(R.id.net_img);
        linearLayout = findViewById(R.id._layout_internet);
        imag_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoRefreshNetworkUtil.removeAllRegisterNetworkListener();
                Intent intent=new Intent(MainActivity.this, std_OfflineMode_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        CheckNetworkConnectionHelper
                .getInstance()
                .registerNetworkChangeListener(new OnNetworkConnectionChangeListener() {
                    @Override
                    public void onConnected() {
                        linearLayout.setVisibility(View.INVISIBLE);
//
                    }

                    @Override
                    public void onDisconnected() {
                        linearLayout.setVisibility(View.VISIBLE);
                        Intent intent=new Intent(MainActivity.this, std_OfflineMode_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }

                    @Override
                    public Context getContext() {
                        return MainActivity.this;
                    }
                });
    }


    @Override
    public void onBackPressed() {
//        if (webView.canGoBack()){
//            webView.canGoBack();
//    } else {
//            super.onBackPressed();
//        }


        if(webView.canGoBack()){
            // If web view have back history, then go to the web view back history
            webView.goBack();
//            Snackbar.make(MainActivity.this,"Go to back history", Snackbar.LENGTH_LONG).show();
        }else {
            // Ask the user to exit the app or stay in here
            showAppExitDialog();
        }
    }




    protected void showAppExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle("Please confirm");
        builder.setMessage("Are you sure to close \'School Management Software\' ?");
        builder.setCancelable(true);


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when user want to exit the app
                // Let allow the system to handle the event, such as exit the app
                MainActivity.super.onBackPressed();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when want to stay in the app
                Toast.makeText(MainActivity.this,"thank you",Toast.LENGTH_LONG).show();
            }
        });

        // Create the alert dialog using alert dialog builder
        AlertDialog dialog = builder.create();

        // Finally, display the dialog when user press back button
//        dialog.getWindow().setBackgroundDrawableResource(R.color.colorAccent);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
