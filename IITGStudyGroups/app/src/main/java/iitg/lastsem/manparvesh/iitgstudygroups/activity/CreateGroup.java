package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import iitg.lastsem.manparvesh.iitgstudygroups.R;
import iitg.lastsem.manparvesh.iitgstudygroups.firebase.GroupDIscussion;

public class CreateGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        //TODO add datepicker and timepicker for weekly meeting schedule

        Button newGroup = (Button)findViewById(R.id.btnCreateNewGroupCreateNewGroup);
        newGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), GroupDIscussion.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
