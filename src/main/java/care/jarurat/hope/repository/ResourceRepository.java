package care.jarurat.hope.repository;

import care.jarurat.hope.model.SupportModule;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class ResourceRepository {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME = "resources";

    public SupportModule getModule(String key) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore.collection(COLLECTION_NAME)
                    .document(key)
                    .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(SupportModule.class);
            } else {
                System.out.println("⚠️ Resource not found for key: " + key);
                return null;
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
    public SupportModule getSupportModule(String key, String lang) {
        String docId = key + "_" + lang; // e.g., financial_en
        try {
            DocumentSnapshot document = firestore.collection(COLLECTION_NAME)
                    .document(docId)
                    .get()
                    .get();

            if (document.exists()) {
                return document.toObject(SupportModule.class);
            } else {
                System.out.println("⚠️ Resource not found for key: " + docId);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


}
