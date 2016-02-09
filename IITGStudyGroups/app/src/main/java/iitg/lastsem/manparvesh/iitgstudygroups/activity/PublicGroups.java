package iitg.lastsem.manparvesh.iitgstudygroups.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


//        getActionBar().show();
  //      getActionBar().setTitle("Groups");

        setTitle("Study Groups");

        //set navigation bar and status bar colors!
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        ref = new Firebase(FIREBASE_URL).child("groups");


        progressDialog = new ProgressDialog(PublicGroups.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Groups..");
        progressDialog.show();




        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Toast.makeText(getBaseContext(), "no. of users: " + dataSnapshot.getChildrenCount() + "", Toast.LENGTH_SHORT).show();

                groups.clear();

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

    private ProgressDialog progressDialog;

    private void agge() {

        String[] gr = groups.toArray(new String[groups.size()]);

        //Toast.makeText(getBaseContext(), String.valueOf(groups.size()), Toast.LENGTH_SHORT).show();

        this.setListAdapter(new ArrayAdapter<>(this, R.layout.list_item, R.id.label, gr));

        progressDialog.dismiss();


        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected button
                String groupName = ((TextView) view).getText().toString().replaceAll("[\\-\\+\\.\\^:,@]", "").toLowerCase();

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
