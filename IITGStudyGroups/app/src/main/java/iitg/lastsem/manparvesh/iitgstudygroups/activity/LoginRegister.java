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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import iitg.lastsem.manparvesh.iitgstudygroups.DBentries.User;
import iitg.lastsem.manparvesh.iitgstudygroups.R;
import iitg.lastsem.manparvesh.iitgstudygroups.helper.PrefManager;

public class LoginRegister extends AppCompatActivity {

    private static TextView linkRegister;
    private static Button goHome;

    private EditText inputEmail, inputPassword;
    private Button btnLogin;
    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login_register);

        PrefManager pref = new PrefManager(getApplicationContext());
        if (pref.isLoggedIn()) {
            Intent intent = new Intent(LoginRegister.this, Home.class);
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
        TextView tx = (TextView)findViewById(R.id.splashtext2);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Trocchi-Bold.otf");
        tx.setTypeface(custom_font);

        linkRegister = (TextView)findViewById(R.id.link_register);
        linkRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        pref = new PrefManager(getApplicationContext());

        inputPassword = (EditText) findViewById(R.id.login_password);
        inputEmail = (EditText) findViewById(R.id.login_email);

        goHome = (Button) findViewById(R.id.btn_login);
        goHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void onLoginSuccess() {
        // Start the Home activity
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);

        //finish this activity!
        finish();
    }

    private Firebase ref;
    private static final String FIREBASE_URL = "https://iitg-study-groups.firebaseio.com";

    public void login(){

        if (!validate()) {
            onLoginFailed();
            return;
        }

        String email = inputEmail.getText().toString();
        String password =  inputPassword.getText().toString();
        String username = email.replaceAll("[\\-\\+\\.\\^:,@]", "");

        //TODO check if username exists in firebase and get name from there.
        //TODO: still incorrect

        //fbContains(username);
        final String un = username;

        ref = new Firebase(FIREBASE_URL).child("users"); //Root URL



        flag = false;

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Toast.makeText(getBaseContext(), "no. of users: " + dataSnapshot.getChildrenCount() + "", Toast.LENGTH_SHORT).show();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //Toast.makeText(getBaseContext(), child.getValue(String.class), Toast.LENGTH_SHORT).show();

                    User u = child.getValue(User.class);
                    String uName = u.getUsername();
                    if (uName.equals(un)) {
                        flag = true;
                        nameOfUser = u.getName();

                        Toast.makeText(getBaseContext(), "Welcome, " + uName + "!", Toast.LENGTH_SHORT).show();
                    }




                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



            String name = nameOfUser ;

        final ProgressDialog progressDialog = new ProgressDialog(LoginRegister.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        //progressDialog.
        progressDialog.show();

            pref.createLoginSession(email, password, username, name);

            //progress bar wala kamm

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        if(flag){
            Toast.makeText(getBaseContext(),"Login successful!", Toast.LENGTH_SHORT).show();
        }

        if(!flag){
            onLoginFailed();
            return;
        }

    }

    private boolean flag;
    private String nameOfUser;


    public void onLoginFailed(){
        //Toast.makeText(getBaseContext(), "Login failed. Check entries or Internet connection", Toast.LENGTH_LONG).show();

    }

    public boolean validate(){
        boolean valid = true;

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("Enter a valid IITG webmail ID");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            inputPassword.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }
}
