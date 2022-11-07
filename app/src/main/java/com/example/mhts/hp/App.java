package com.example.mhts.hp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String Submit_Id_1 = "submit Crime Report";
    public static final String Submit_Id_2 = "Login Successfull";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();

    }

    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel(
                    Submit_Id_1,
                    "Report is Successfully Sent",
                    NotificationManager.IMPORTANCE_HIGH
            ); channel.setDescription("This is Channel 1");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationChannel channe2 = new NotificationChannel(
                    Submit_Id_2,
                    "Login is Successfull",
                    NotificationManager.IMPORTANCE_HIGH
            ); channel.setDescription("This is Channel 2");
            NotificationManager manager1 = getSystemService(NotificationManager.class);
            manager1.createNotificationChannel(channe2);

        }
    }
}
