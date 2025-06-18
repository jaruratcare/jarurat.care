package care.jarurat.hope.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore getFireStore() throws IOException {
        if(FirebaseApp.getApps().isEmpty()){
            String envCredentials = System.getenv("FIREBASE_CREDENTIALS");

            InputStream serviceAccount;
            if(envCredentials != null && !envCredentials.isEmpty()){
                 serviceAccount = new ByteArrayInputStream(envCredentials.getBytes(StandardCharsets.UTF_8));

            } else {
                serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");
                if (serviceAccount == null) {
                    throw new FileNotFoundException("firebase-service-account.json not found in classpath");
                }
            }
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            FirebaseApp.initializeApp(options);

        }
        return FirestoreClient.getFirestore();

    }
}
