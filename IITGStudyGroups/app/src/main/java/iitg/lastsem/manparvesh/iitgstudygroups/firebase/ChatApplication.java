package iitg.lastsem.manparvesh.iitgstudygroups.firebase;

import com.firebase.client.Firebase;

/**
 * Created by Man Parvesh on 2/8/2016.
 */
public class ChatApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
