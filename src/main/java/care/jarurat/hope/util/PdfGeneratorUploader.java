package care.jarurat.hope.util;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class PdfGeneratorUploader {

    public static String generateAndUploadPdf(String content, String userId) throws IOException {
        // Create PDF in memory
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Tailored Nutrition Meal Plan").setBold().setFontSize(16));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph(content));
        document.close();

        String bucketName = "whatsbot-b61c8.firebasestorage.app";
        String fileName = "nutrition_plans/" + userId + ".pdf";

        // Upload PDF using Firebase StorageClient
        Blob blob = StorageClient.getInstance()
                .bucket(bucketName)
                .create(fileName, new ByteArrayInputStream(outputStream.toByteArray()), "application/pdf");

        // Load credentials from resource JSON
        InputStream serviceAccountStream = PdfGeneratorUploader.class.getClassLoader()
                .getResourceAsStream("firebase-service-account.json");

        if (serviceAccountStream == null) {
            throw new IOException("firebase-service-account.json not found in resources folder");
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("whatsbot-b61c8")  // Replace with your project id
                .build()
                .getService();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();

        // Generate signed URL valid for 15 minutes
        return storage.signUrl(blobInfo, 15, TimeUnit.MINUTES).toString();
    }
}
