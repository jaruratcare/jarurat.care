package care.jarurat.hope.repository;

import care.jarurat.hope.model.User;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    @Autowired
    private Firestore firestore;
    private static final String COLLECTION_NAME="users";

    public void saveUser(User user) throws ExecutionException, InterruptedException {
        firestore.collection(COLLECTION_NAME).document(user.getUserId()).set(user).get();
    }

}
