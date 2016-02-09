package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import iitg.lastsem.manparvesh.iitgstudygroups.DBentries.Group;
import iitg.lastsem.manparvesh.iitgstudygroups.R;
import iitg.lastsem.manparvesh.iitgstudygroups.firebase.GroupDIscussion;

public class CreateGroup extends AppCompatActivity {

    private EditText nameET, descriptionET;
    private static final String FIREBASE_URL = "https://iitg-study-groups.firebaseio.com";
    private Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_create_group);

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        //TODO add datepicker and timepicker for weekly meeting schedule
        //nahi hoga abhi


        nameET = (EditText) findViewById(R.id.inputNewGroupName);
        descriptionET = (EditText) findViewById(R.id.inputGroupDescription);



        Button newGroup = (Button)findViewById(R.id.btnCreateNewGroupCreateNewGroup);
        newGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = nameET.getText().toString(), description = descriptionET.getText().toString();

                //Toast.makeText(getBaseContext(), name + "\n" + description, Toast.LENGTH_SHORT).show();

                String nm = name.replaceAll("[\\-\\+\\.\\^:,@]", "").toLowerCase();
                ref = new Firebase(FIREBASE_URL + "/groups/"+nm).child(nm);

                Group group = new Group(name, description);

                ref.setValue(group);

                final String gn = nm;

                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), GroupDIscussion.class);
                intent.putExtra("groupName", gn);
                startActivity(intent);
                finish();
            }
        });

    }
}
