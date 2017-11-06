package toptier.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FBInit {

    public static void init() {
        FileInputStream serviceAccount;

        try {
            // move string(s) to properties | todo
            serviceAccount = new FileInputStream("src/resources/config/firebase/service-account-key.json");
        } catch (FileNotFoundException e) {
            System.out.println("unable to find serviceAccountKey ... firebase initialization failure");
            throw new RuntimeException(e);
        }

        FirebaseOptions options;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://toptier-cbd05.firebaseio.com/")
                    .build();
        } catch (IOException e) {
            System.out.println("service account and/or db url invalid ... firebase initialization failure");
            throw new RuntimeException(e);
        }


        FirebaseApp.initializeApp(options);
        System.out.println("... firebase successfully initialized ...");
    }
}


