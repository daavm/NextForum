package com.daavm.nextbit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceActivity;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import android.widget.ViewSwitcher;

import com.pushbots.push.Pushbots;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.start;
import static android.R.attr.x;
import static android.R.attr.y;
import static android.R.id.input;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, WebView.FindListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private int myColor = Color.parseColor("#8fd6bd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final Boolean loged = preferences.getBoolean("loged", false);
        ////////////////
        //Choose Theme//
        ////////////////
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
        if (!loged) {
            Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
            startActivity(logedOut);
        }
        Pushbots.sharedInstance().init(this);
        final Activity activity = this;
        boolean desktopMode = preferences.getBoolean("desktopMode", false);
        boolean noImages = preferences.getBoolean("noImages", false);
        //TODO añadir a los dos, el activity layout de Login, en caso de que el usuario haya elegido usarlo
        if (desktopMode) {
            if (loged) {
                setContentView(R.layout.activity_main2_out);
            } else {
                setContentView(R.layout.activity_main2);
            }
        } else {
            if (loged) {
                setContentView(R.layout.activity_main_out);
            } else {
                setContentView(R.layout.activity_main);
            }
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
        if (Build.VERSION.SDK_INT >= 23) {
            myWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                final AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar);

                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > 1200) {
                        if (scrollY > oldScrollY) {
                            appbar.animate().translationY(-appbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
                        }
                    }
                    if (scrollY < oldScrollY) {
                        if (scrollY < 1200) {
                            appbar.setVisibility(View.VISIBLE);
                        } else {
                            appbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                        }
                    }
                }
            });
        }
        View hView =  navigationView.getHeaderView(0);
        String usernameV = preferences.getString("usernameWV", "");
        TextView usernameText = (TextView)hView.findViewById(R.id.user);
        usernameText.setText("Hello " + usernameV + "!");
        myWebView.setVisibility(View.VISIBLE);
        final WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (noImages) {
            webSettings.setLoadsImagesAutomatically(false);
        } else {
            webSettings.setLoadsImagesAutomatically(true);
        }
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
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                myWebView.loadUrl("javascript:if (typeof(document.getElementsByClassName('nav-links-wrapper')[0]) != 'undefined' && document.getElementsByClassName('nav-links-wrapper')[0] != null){document.getElementsByClassName('nav-links-wrapper')[0].style.display = 'none';} void 0");
                myWebView.findAllAsync("Sign In");
                myWebView.setFindListener(new WebView.FindListener() {
                    @Override
                    public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {
                        if (numberOfMatches > 0) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.remove("loged" + Boolean.valueOf(loged));
                            editor.apply();
                            editor.putBoolean("loged", false);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                myWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if (url.equals("https://community.nextbit.com/t5/community/page.logoutpage?t:cp=authentication/contributions/unticketedauthenticationactions&dest_url=https%3A%2F%2Fcommunity.nextbit.com%2F")) {
                    myWebView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Loging out...", Toast.LENGTH_LONG).show();
                    android.webkit.CookieManager.getInstance().removeAllCookie();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("loged" + Boolean.valueOf(loged));
                    editor.apply();
                    editor.putBoolean("loged", false);
                    editor.commit();
                    Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
                    startActivity(logedOut);
                }
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
                myWebView.setVisibility(View.GONE);
                android.webkit.CookieManager.getInstance().removeAllCookie();
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("loged" + Boolean.valueOf(loged));
                editor.apply();
                editor.putBoolean("loged", false);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Loging out...", Toast.LENGTH_LONG).show();
                Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(logedOut);
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
        if (this.getLocalClassName().equals("MainActivity")) {
            myWebView.loadUrl("https://community.nextbit.com/");
        } else if (this.getLocalClassName().equals("Notifications")) {
            myWebView.loadUrl("https://community.nextbit.com/t5/notificationfeed/page");
        } else if (this.getLocalClassName().equals("appthread")) {
            myWebView.loadUrl("https://community.nextbit.com/t5/General-Q-A/Nextbit-Forum-App-NextForum/m-p/20063#M10860");
        } else if (this.getLocalClassName().equals("discover")) {
            myWebView.loadUrl("https://www.nextbit.com");
        } else if (this.getLocalClassName().equals("forumsettings")) {
            myWebView.loadUrl("https://community.nextbit.com/t5/user/myprofilepage/tab/personal-profile");
        } else if (this.getLocalClassName().equals("India")) {
            myWebView.loadUrl("https://community.nextbit.com/t5/India/bd-p/India");
        } else if (this.getLocalClassName().equals("Login")) {
            myWebView.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Loging in...", Toast.LENGTH_LONG).show();
            myWebView.loadUrl("https://community.nextbit.com/t5/user/userloginpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F");
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("user");
            String password = bundle.getString("password");
            //TODO settings, dejar elegir al usuario si quiere pantalla de logeo o no
            final String js = "javascript:document.getElementById('lia-login').value = '" + username + "';document.getElementById('lia-password').value='" + password + "';";
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {

                    super.onPageFinished(view, "https://community.nextbit.com/t5/user/userloginpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F");
                    myWebView.loadUrl("javascript:if (typeof(document.getElementsByClassName('nav-links-wrapper')[0]) != 'undefined' && document.getElementsByClassName('nav-links-wrapper')[0] != null){document.getElementsByClassName('nav-links-wrapper')[0].style.display = 'none';} void 0");
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                    if (myWebView.getUrl().equals("https://community.nextbit.com/t5/user/userloginpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F")) {
                        myWebView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onLoadResource(WebView view, String url) {
                                if (url.equals("https://community.nextbit.com/t5/community/page.logoutpage?t:cp=authentication/contributions/unticketedauthenticationactions&dest_url=https%3A%2F%2Fcommunity.nextbit.com%2F")) {
                                    myWebView.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Loging out...", Toast.LENGTH_LONG).show();
                                    android.webkit.CookieManager.getInstance().removeAllCookie();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.remove("loged" + Boolean.valueOf(loged));
                                    editor.apply();
                                    editor.putBoolean("loged", false);
                                    editor.commit();
                                    Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
                                    startActivity(logedOut);
                                }
                            }

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                myWebView.loadUrl("javascript:if (typeof(document.getElementsByClassName('nav-links-wrapper')[0]) != 'undefined' && document.getElementsByClassName('nav-links-wrapper')[0] != null){document.getElementsByClassName('nav-links-wrapper')[0].style.display = 'none';} void 0");
                                super.onPageFinished(view, "javascript:document.getElementById('form').submit();");
                                if (myWebView.getTitle().contains("Sign In")) {
                                    Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.remove("loged" + Boolean.valueOf(loged));
                                    editor.apply();
                                    editor.putBoolean("loged", false);
                                    editor.commit();
                                    Toast.makeText(getApplicationContext(), "Login failed, please review your login details and try again", Toast.LENGTH_LONG).show();
                                    startActivity(logedOut);
                                } else {
                                    myWebView.setVisibility(View.VISIBLE);
                                    final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.remove("loged" + Boolean.valueOf(loged));
                                    editor.apply();
                                    editor.putBoolean("loged", true);
                                    editor.commit();
                                }
                            }
                        });
                        myWebView.loadUrl("javascript:document.getElementById('form').submit();");
                    }
                }
            });
        } else if (this.getLocalClassName().equals("Messages")) {
            myWebView.loadUrl("https://community.nextbit.com/t5/notes/privatenotespage");
        } else if (this.getLocalClassName().equals("signup")) {
            myWebView.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Signing up...", Toast.LENGTH_LONG).show();
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("user");
            String password = bundle.getString("pass");
            String name = bundle.getString("name");
            String lastname = bundle.getString("lastname");
            String email = bundle.getString("email");
            myWebView.loadUrl("https://community.nextbit.com/t5/user/userregistrationpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F");
            final String js = "javascript:document.getElementById('lia-login').value = '" + username + "';document.getElementById('lia-password').value='" + password + "';document.getElementById('lia-passwordConfirm').value='" + password + "';document.getElementById('lia-email').value='" + email + "';document.getElementById('lia-emailConfirm').value='" + email + "';document.getElementById('lia-profileFirstName').value='" + name + "';document.getElementById('lia-profileLastName').value='" + lastname + "';document.getElementById('lia-userAcceptsTermsOfService').checked='" + true + "';";
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onLoadResource(WebView view, String url) {
                    if (url.equals("https://community.nextbit.com/t5/community/page.logoutpage?t:cp=authentication/contributions/unticketedauthenticationactions&dest_url=https%3A%2F%2Fcommunity.nextbit.com%2F")) {
                        myWebView.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Loging out...", Toast.LENGTH_LONG).show();
                        android.webkit.CookieManager.getInstance().removeAllCookie();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("loged" + Boolean.valueOf(loged));
                        editor.apply();
                        editor.putBoolean("loged", false);
                        editor.commit();
                        Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(logedOut);
                    }
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    myWebView.loadUrl("javascript:if (typeof(document.getElementsByClassName('nav-links-wrapper')[0]) != 'undefined' && document.getElementsByClassName('nav-links-wrapper')[0] != null){document.getElementsByClassName('nav-links-wrapper')[0].style.display = 'none';} void 0");
                    super.onPageFinished(view, "https://community.nextbit.com/t5/user/userregistrationpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F");
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                    if (myWebView.getUrl().equals("https://community.nextbit.com/t5/user/userregistrationpage?dest_url=https:%2F%2Fcommunity.nextbit.com%2F")) {
                        myWebView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                myWebView.loadUrl("javascript:if (typeof(document.getElementsByClassName('nav-links-wrapper')[0]) != 'undefined' && document.getElementsByClassName('nav-links-wrapper')[0] != null){document.getElementsByClassName('nav-links-wrapper')[0].style.display = 'none';} void 0");
                                super.onPageFinished(view, "javascript:document.getElementById('form_0').submit();");
                                if (myWebView.getTitle().contains("Registration")) {
                                    Intent logedOut = new Intent(getApplicationContext(), signupPage.class);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.remove("loged" + Boolean.valueOf(loged));
                                    editor.apply();
                                    editor.putBoolean("loged", false);
                                    editor.commit();
                                    startActivity(logedOut);
                                    Toast.makeText(getApplicationContext(), "Signup failed, please review your details!", Toast.LENGTH_LONG).show();
                                } else {
                                    final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.remove("loged" + Boolean.valueOf(loged));
                                    editor.apply();
                                    editor.putBoolean("loged", true);
                                    editor.commit();
                                    myWebView.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        myWebView.loadUrl("javascript:document.getElementById('form_0').submit();");
                    }
                }
            });
        } else if (this.getLocalClassName().equals("forgotPasswordWV")) {
            Toast.makeText(getApplicationContext(), "Working...", Toast.LENGTH_LONG).show();
            Bundle bundle = getIntent().getExtras();
            String email = bundle.getString("email");
            myWebView.loadUrl("https://community.nextbit.com/t5/authentication/forgotpasswordpage?dest_url=%2F");
            final String js = "javascript:document.getElementById('lia-userEmail').value='" + email + "';";
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onLoadResource(WebView view, String url) {
                    if (url.equals("https://community.nextbit.com/t5/community/page.logoutpage?t:cp=authentication/contributions/unticketedauthenticationactions&dest_url=https%3A%2F%2Fcommunity.nextbit.com%2F")) {
                        myWebView.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Loging out...", Toast.LENGTH_LONG).show();
                        android.webkit.CookieManager.getInstance().removeAllCookie();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("loged" + Boolean.valueOf(loged));
                        editor.apply();
                        editor.putBoolean("loged", false);
                        editor.commit();
                        Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(logedOut);
                    }
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, "https://community.nextbit.com/t5/authentication/forgotpasswordpage?dest_url=%2F");
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                    if (myWebView.getUrl().equals("https://community.nextbit.com/t5/authentication/forgotpasswordpage?dest_url=%2F")) {
                        myWebView.loadUrl("javascript:document.getElementById('form').submit();");
                        myWebView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, "javascript:document.getElementById('form').submit();");
                                Toast.makeText(getApplicationContext(), "Follow the steps that have been sent to your email to recover your forum account", Toast.LENGTH_LONG).show();
                                Intent logedOut = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(logedOut);
                            }
                        });
                    }
                }
            });
        }
        ///////////////
        //WebView End//
        ///////////////


    }
    @Override
    public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {
        Toast.makeText(getApplicationContext(), "Matches: " + numberOfMatches, Toast.LENGTH_LONG).show();
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
            Intent intent2 = new Intent(this,about.class);
            startActivity(intent2);
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
        }  else if (id == R.id.signout) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Boolean loged = preferences.getBoolean("loged", false);
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.setVisibility(View.GONE);
            android.webkit.CookieManager.getInstance().removeAllCookie();
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("loged" + Boolean.valueOf(loged));
            editor.apply();
            editor.putBoolean("loged", false);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Loging out...", Toast.LENGTH_LONG).show();
            Intent logedOut = new Intent(getApplicationContext(), LoginPage.class);
            startActivity(logedOut);
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
