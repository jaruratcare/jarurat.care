package care.jarurat.hope.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class CsvDoctorUploadService {

    public String uploadDoctorsCSV(String path) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            CSVReader reader = new CSVReader(new FileReader(path));

            String[] headers = reader.readNext(); // skip header
            String[] row;
            int count = 0;

            while ((row = reader.readNext()) != null) {

                Map<String, Object> data = new HashMap<>();
                data.put("city", row[0]);
                data.put("specialization", row[1]);
                data.put("doctorName", row[2]);
                data.put("description", row[3]);
                data.put("hospital", row[4]);
                data.put("experienceYears", row[5]); 
                data.put("contact", row[6]);
                data.put("opdTimings", row[7]);
                data.put("address", row[8]);
                data.put("website", row[9]);

                db.collection("doctors").add(data);
                count++;
            }

            reader.close();
            return "CSV Upload Completed. Total records = " + count;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}

