package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import iitg.lastsem.manparvesh.iitgstudygroups.DBentries.User;
import iitg.lastsem.manparvesh.iitgstudygroups.R;
import iitg.lastsem.manparvesh.iitgstudygroups.helper.PrefManager;

public class Register extends AppCompatActivity {

    TextView linkLogin;
    private static Button goHome;

    private EditText nameET, passwordET, emailET;

    private Firebase ref;
    private static final String FIREBASE_URL = "https://iitg-study-groups.firebaseio.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_register);

        // if already logged in, go to Home
        PrefManager pref = new PrefManager(getApplicationContext());
        if (pref.isLoggedIn()) {
            Intent intent = new Intent(Register.this, Home.class);
            startActivity(intent);

            finish();
        }
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




        nameET = (EditText) findViewById(R.id.registerName);
        emailET = (EditText) findViewById(R.id.registerEmail);
        passwordET = (EditText) findViewById(R.id.registerPassword);

        linkLogin = (TextView) findViewById(R.id.link_login);
        linkLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), LoginRegister.class);
                startActivity(intent);
                finish();
            }
        });

        goHome = (Button) findViewById(R.id.btn_register);
        goHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                register();
            }
        });

    }
    public void register(){

        if (!validate()) {
            onSignupFailed();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(Register.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account..");
        progressDialog.show();

        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        String username = email.replaceAll("[\\-\\+\\.\\^:,@]","");

        ref = new Firebase(FIREBASE_URL).child("users").child(username);

        User user = new User(name, password, email, username);

        ref.setValue(user);

        PrefManager pref = new PrefManager(getApplicationContext());
        pref.createLoginSession(email, password, username, name);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

        //khatam!
    }

    public void onSignupSuccess() {
        setResult(RESULT_OK, null);
        // At last, Start the Home activity
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }

    public boolean validate(){
        boolean valid = true;

        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameET.setError("At least 3 characters");
            valid = false;
        } else {
            nameET.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
                !(email.endsWith("@iitg.ernet.in")||email.endsWith("@iitg.ac.in")) ) {
            emailET.setError("Enter a valid IITG webmail ID");
            valid = false;
        } else {
            emailET.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordET.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordET.setError(null);
        }

        return valid;
    }

    public void  onSignupFailed(){
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        goHome.setEnabled(true);
    }
}
