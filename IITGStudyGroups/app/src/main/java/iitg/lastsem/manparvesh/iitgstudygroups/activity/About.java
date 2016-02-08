package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebView;

import iitg.lastsem.manparvesh.iitgstudygroups.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        WebView myWebView = (WebView) findViewById(R.id.webVIEW);
        myWebView.loadUrl("http://manparvesh.github.io/IITGStudyGroups/about.html"); //IITGStudyGroups/About
    }
}
