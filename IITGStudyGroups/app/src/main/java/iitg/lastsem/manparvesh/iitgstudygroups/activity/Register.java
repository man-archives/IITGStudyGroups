package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import iitg.lastsem.manparvesh.iitgstudygroups.R;

public class Register extends AppCompatActivity {

    TextView linkLogin;
    private static Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hiding the action bar!
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        //using custom font for logo
        TextView tx = (TextView)findViewById(R.id.splashtext3);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Trocchi-Bold.otf");
        tx.setTypeface(custom_font);

        linkLogin = (TextView) findViewById(R.id.link_login);
        linkLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), LoginRegister.class);
                startActivity(intent);
            }
        });

        goHome = (Button) findViewById(R.id.btn_register);
        goHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Home activity
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        //TODO: connection with the database!
    }
}
