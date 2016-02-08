package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import iitg.lastsem.manparvesh.iitgstudygroups.R;

public class Home extends AppCompatActivity implements View.OnClickListener {

    Button newGroup, publicGroups, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        //todo: add my groups
//        myGroups = (Button) findViewById(R.id.btnHomeMyGroups);
        newGroup = (Button) findViewById(R.id.btnHomeCreateNewGroup);
        publicGroups = (Button) findViewById(R.id.btnHomePublicGroups);
        about = (Button) findViewById(R.id.btnHomeAbout);

  //      myGroups.setOnClickListener(this);
        newGroup.setOnClickListener(this);
        publicGroups.setOnClickListener(this);
        about.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
     /*       case R.id.btnHomeMyGroups:
                Intent intent1 = new Intent(getApplicationContext(), MyGroups.class);
                startActivity(intent1);
                break;
                */
            case R.id.btnHomeCreateNewGroup:
                Intent intent2 = new Intent(getApplicationContext(), CreateGroup.class);
                startActivity(intent2);
                break;
            case R.id.btnHomePublicGroups:
                Intent intent3 = new Intent(getApplicationContext(), PublicGroups.class);
                startActivity(intent3);
                break;
            case R.id.btnHomeAbout:
                Intent intent4 = new Intent(getApplicationContext(), About.class);
                startActivity(intent4);
                break;
        }

        // @TODO done everything!
    }
}
