package com.daavm.nextbit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;import android.view.View.OnClickListener;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.x;
import static android.R.attr.y;
import static android.R.id.input;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private int myColor = Color.parseColor("#8fd6bd");

    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    private String notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ////////////////
        //Choose Theme//
        ////////////////
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean midnightTheme = preferences.getBoolean("midnightTheme", false);
        boolean mintTheme = preferences.getBoolean("mintTheme", false);
        boolean electricTheme = preferences.getBoolean("electricTheme", false);

        if (midnightTheme) {
            setTheme(R.style.Midnight);
        } else if (mintTheme) {
            setTheme(R.style.Mint);
        } else if (electricTheme) {
            setTheme(R.style.Electric);
        } else {
            setTheme(R.style.Electric);
        }
        ////////////////
        //Choose Theme//
        ////////////////
        super.onCreate(savedInstanceState);
        Pushbots.sharedInstance().init(this);
        final Activity activity = this;
            if (isFirstTime()) {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Welcome to my app!");
                alertDialog.setMessage("Hi! Thanks for testing my app. I'm still working on it, and more things have to be added and fixed. All feedback is welcomed!");
                alertDialog.setButton("I got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.setIcon(R.drawable.sheepalert);
                alertDialog.show();
                final String hello = "hello";
            }

        boolean desktopMode = preferences.getBoolean("desktopMode", false);
        //TODO añadir a los dos, el activity layout de Login, en caso de que el usuario haya elegido usarlo
        if (desktopMode) {
            setContentView(R.layout.activity_main2);
        } else {
            setContentView(R.layout.activity_main);
        }
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewOffset(false,
                getResources().getDimensionPixelSize(R.dimen.refresher_offset),
                getResources().getDimensionPixelSize(R.dimen.refresher_offset_end));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(myColor);
        final FloatingActionButton floatingActionButton = (FloatingActionButton) this.findViewById(R.id.fab);
        /////////////////
        //WebView Start//
        /////////////////
        final WebView myWebView = (WebView) this.findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1");
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        if (desktopMode) {
            myWebView.setInitialScale(140);
            myWebView.loadUrl("javascript:if (typeof(document.getElementsByClassName('nav-links-wrapper')[0]) != 'undefined' && document.getElementsByClassName('nav-links-wrapper')[0] != null){document.getElementsByClassName('nav-links-wrapper')[0].style.display = 'none';} void 0");
        } else {
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    myWebView.loadUrl("javascript:if (typeof(document.getElementsByClassName('nav-links-wrapper')[0]) != 'undefined' && document.getElementsByClassName('nav-links-wrapper')[0] != null){document.getElementsByClassName('nav-links-wrapper')[0].style.display = 'none';} void 0");
                }
            });
        }
        activity.setTitle("");
        final AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar);
        myWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                final AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar);
                appbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                activity.setProgress(progress * 100);
                TextView title = (TextView) findViewById(R.id.toolbar_title);
                title.setText("Loading... " + progress + "%");
                if (progress > 65)
                    swipeRefreshLayout.setRefreshing(false);
                if (progress == 100)
                    title.setText(myWebView.getTitle());
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                myWebView.scrollTo(0, 0);
            }
        });
        floatingActionButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                    return true;
            }
        });
        TextView txtView = (TextView) findViewById(R.id.toolbar_title);
        txtView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                String stringYouExtracted = myWebView.getUrl();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", stringYouExtracted);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Copied URL to clipboard", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        if (this.getLocalClassName().equals("MainActivity")){
            myWebView.loadUrl("https://community.nextbit.com/");
        }
        else if (this.getLocalClassName().equals("Notifications")){
            myWebView.loadUrl("https://community.nextbit.com/t5/notificationfeed/page");
        }
        else if (this.getLocalClassName().equals("appthread")){
            myWebView.loadUrl("https://community.nextbit.com/t5/General-Q-A/Nextbit-Forum-App-NextForum/m-p/20063#M10860");
        }
        else if (this.getLocalClassName().equals("discover")){
            myWebView.loadUrl("https://www.nextbit.com");
        }
        else if (this.getLocalClassName().equals("forumsettings")){
            myWebView.loadUrl("https://community.nextbit.com/t5/user/myprofilepage/tab/personal-profile");
        }
        else if (this.getLocalClassName().equals("India")){
            myWebView.loadUrl("https://community.nextbit.com/t5/India/bd-p/India");
        }
        else if (this.getLocalClassName().equals("Login")){
                String user = "david";
            //TODO settings, dejar elegir al usuario si quiere pantalla de logeo o no
               String pass = "ruben22798";
                myWebView.loadUrl("https://community.nextbit.com/t5/user/userloginpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F");
                   final String js = "javascript:document.getElementById('lia-login').value = '" + user + "';document.getElementById('lia-password').value='" + pass + "';";
                    myWebView.setWebViewClient(new WebViewClient() {
                               @Override
                     public void onPageFinished(WebView view, String url) {
                           super.onPageFinished(view, "https://community.nextbit.com/t5/user/userloginpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F");
                           view.evaluateJavascript(js, new ValueCallback<String>() {
                               @Override
                                public void onReceiveValue(String s) {
                                }
                           });
                                   if (myWebView.getUrl().equals("https://community.nextbit.com/t5/user/userloginpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F")) {
                                       myWebView.loadUrl("javascript:document.getElementById('form').submit();");
                                   }
                         }
                 });
        }
        else if (this.getLocalClassName().equals("Messages")){
            myWebView.loadUrl("https://community.nextbit.com/t5/notes/privatenotespage");
        }

        ///////////////
        //WebView End//
        ///////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setVerticalScrollBarEnabled(false);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.NavigationView);
        navigationView.setScrollBarSize(0);
        navigationView.setNavigationItemSelectedListener(this);
        myWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            final AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar);
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 1200){
                    if (scrollY > oldScrollY) {
                        appbar.animate().translationY(-appbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
                    }
                }
                if (scrollY < oldScrollY){
                    if (scrollY < 1200){
                        appbar.setVisibility(View.VISIBLE);
                    }
                    else {
                        appbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                    }
                }
            }
        });

    }
    @Override
    public void onRefresh() {
        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.reload();
    }
    @Override
    public void onBackPressed() {
        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (myWebView.canGoBack()) {
                myWebView.goBack();
            }
            else {
                drawer.openDrawer(GravityCompat.START);}
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                WebView myWebView = (WebView) this.findViewById(R.id.webView);
                myWebView.reload();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/General-Q-A/Nextbit-Forum-App-NextForum/m-p/16977");
        }
        else if (id == R.id.india) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/India/bd-p/India");
        }
        else  if (id == R.id.donate) {
            Intent intent2 = new Intent(this,donationsScreen.class);
            startActivity(intent2);
        }
        else if (id == R.id.store) {
            Intent intent2 = new Intent(this,store.class);
            startActivity(intent2);
        } else if (id == R.id.nav_wiki) {
            Intent intent = new Intent(this,wiki.class);
            startActivity(intent);
        } else if (id == R.id.nav_discover) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://www.nextbit.com/pages/meet-robin");
        } else if (id == R.id.nav_themes) {
            Intent intent2 = new Intent(MainActivity.this,Preferences.class);
            startActivity(intent2);
        } else if (id == R.id.Community) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com");
        }  else if (id == R.id.messages) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/notes/privatenotespage");
        }  else if (id == R.id.notifications) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/notificationfeed/page");
        }  else if (id == R.id.signin) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/user/userloginpage?dest_url=https%3A%2F%2Fcommunity.nextbit.com%2F");
        }   else if (id == R.id.forumsettings) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/user/myprofilepage/tab/personal-profile");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }


    }


}
