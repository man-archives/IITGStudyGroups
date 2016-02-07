package iitg.lastsem.manparvesh.iitgstudygroups;

/**
 * Created by Man Parvesh on 2/6/2016.
 */

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "iGbORPN8H57DDhZgIVOZcyDTNAaZPerMhRU4pylt",
                "ko4eO0Y1pz778X2hvPDuLxHBKfluV6sQQbjzChxr"); // Your Application ID and Client Key are defined elsewhere

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
