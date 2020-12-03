package dam.android.angelvilaplana.u3t4event;

import android.app.Application;
import android.content.res.Resources;

/**
 * Activity 1.3
 * Obtain the resources of our project to work
 * with "months_array" in the new model class "EventData"
 */
public class App extends Application {

    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
        resources = getResources();
    }

    public static Resources getRes(){
        return resources;
    }

}
