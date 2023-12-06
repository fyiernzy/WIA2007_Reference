package com.example.firstactivity;

import android.app.Activity;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
//        activities.stream()
//                .filter(activity -> !activity.isFinishing())
//                .forEach(Activity::finish);

        for(Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
