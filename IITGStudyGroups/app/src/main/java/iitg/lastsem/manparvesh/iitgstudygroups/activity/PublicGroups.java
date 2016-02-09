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






        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Toast.makeText(getBaseContext(), "no. of users: " + dataSnapshot.getChildrenCount() + "", Toast.LENGTH_SHORT).show();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //Toast.makeText(getBaseContext(), child.getValue(String.class), Toast.LENGTH_SHORT).show();

                    String key = child.getKey();

                    Group group = child.child(key).getValue(Group.class);

                    groups.add(group.getName());

                    //Toast.makeText(getBaseContext(), group.getName(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getBaseContext(), String.valueOf(groups.size()), Toast.LENGTH_SHORT).show();

                }

                agge();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }

    private void agge() {

        String[] gr = groups.toArray(new String[groups.size()]);

        //Toast.makeText(getBaseContext(), String.valueOf(groups.size()), Toast.LENGTH_SHORT).show();

        this.setListAdapter(new ArrayAdapter<>(this, R.layout.group_button, R.id.btnOpenGroup, gr));


        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected button
                String groupName = ((Button) view).getText().toString();

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), GroupDIscussion.class);
                // sending data to new activity
                i.putExtra("groupName", groupName);
                startActivity(i);

            }
        });

    }

    private ArrayList<String> groups = new ArrayList<>();
}
