package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import iitg.lastsem.manparvesh.iitgstudygroups.DBentries.Group;
import iitg.lastsem.manparvesh.iitgstudygroups.R;
import iitg.lastsem.manparvesh.iitgstudygroups.firebase.GroupDIscussion;

public class PublicGroups extends ListActivity {

    private Firebase ref;
    private static final String FIREBASE_URL = "https://iitg-study-groups.firebaseio.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_public_groups);

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        ref = new Firebase(FIREBASE_URL).child("groups");

        final ArrayList<String> groups = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Toast.makeText(getBaseContext(), "no. of users: " + dataSnapshot.getChildrenCount() + "", Toast.LENGTH_SHORT).show();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //Toast.makeText(getBaseContext(), child.getValue(String.class), Toast.LENGTH_SHORT).show();

                    Group group = child.getValue(Group.class);

                    groups.add(group.getName());


                    //Toast.makeText(getBaseContext(), "Welcome, " + uName + "!", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        String[] gr = groups.toArray(new String[groups.size()]);


        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.group_button, R.id.btnOpenGroup, gr));


        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String groupName = ((Button) view).getText().toString();

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), GroupDIscussion.class);
                // sending data to new activity
                i.putExtra("groupName", groupName);
                startActivity(i);

            }
        });


    }
}
