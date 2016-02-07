package iitg.lastsem.manparvesh.iitgstudygroups;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import iitg.lastsem.manparvesh.iitgstudygroups.activity.LoginRegister;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        //set a timer and open Main activity after it stops.
        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 2000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    }
                    startActivity(new Intent(getApplicationContext(),LoginRegister.class));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };
        logoTimer.start();

        //using custom font for logo
        TextView tx = (TextView)findViewById(R.id.splashtext);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Trocchi-Bold.otf");
        tx.setTypeface(custom_font);


        //hiding the action bar!
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
