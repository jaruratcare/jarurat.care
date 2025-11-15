package care.jarurat.hope.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PdfUploader2 {

    public static String generateAndUploadPdf(String content, String userId) throws IOException {
        // Load the Devanagari font from resources
        InputStream fontStream = PdfUploader2.class.getClassLoader()
                .getResourceAsStream("fonts/NotoSansDevanagari_Condensed-Black.ttf");
        if (fontStream == null) {
            throw new IOException("Font file not found in resources folder");
        }

        // Create PDF in memory
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Create a PdfFont with proper embedding
        PdfFont font = PdfFontFactory.createFont(
                fontStream.readAllBytes(),
                PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED
        );

        // Title updated for cancer hospitals list
        document.add(new Paragraph("List Of Nearby Cancer Hospitals")
                .setFont(font)
                .setBold()
                .setFontSize(16));

        document.add(new Paragraph("\n"));
        document.add(new Paragraph(content).setFont(font).setFontSize(12));

        document.close();

        // Firebase upload
        String bucketName = "jaruratcare-hopebot-1a52e.firebasestorage.app";
        String fileName = "nutrition_plans/" + userId + ".pdf";

        StorageClient.getInstance()
                .bucket(bucketName)
                .create(fileName, new ByteArrayInputStream(outputStream.toByteArray()), "application/pdf");

        // Load credentials for signed URL
        InputStream serviceAccountStream = PdfUploader2.class.getClassLoader()
                .getResourceAsStream("firebase-service-account.json");
        if (serviceAccountStream == null) {
            throw new IOException("firebase-service-account.json not found in resources folder");
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("jaruratcare-hopebot-1a52e")
                .build()
                .getService();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();

        // Signed URL valid 15 minutes
        return storage.signUrl(blobInfo, 15, TimeUnit.MINUTES).toString();
    }

    /** Delete a PDF given its URL (signed or gs://). */
    public static void deletePdfFromUrl(String url) {
        if (url == null || url.isBlank()) {
            log.warn("deletePdfFromUrl: url is null/blank, nothing to delete.");
            return;
        }
        try {
            String bucket = "jaruratcare-hopebot-1a52e.firebasestorage.app";
            String object;

            if (url.contains("/o/")) {
                int oIndex = url.indexOf("/o/");
                int qIndex = url.indexOf("?", oIndex);
                String encObject = (qIndex > 0) 
                        ? url.substring(oIndex + 3, qIndex)
                        : url.substring(oIndex + 3);

                object = URLDecoder.decode(encObject, StandardCharsets.UTF_8);

            } else if (url.startsWith("gs://")) {
                String rest = url.substring(5 + bucket.length());
                object = rest.startsWith("/") ? rest.substring(1) : rest;

            } else {
                log.error("Unsupported URL format: {}", url);
                return;
            }

            InputStream serviceAccountStream = PdfUploader2.class.getClassLoader()
                    .getResourceAsStream("firebase-service-account.json");
            if (serviceAccountStream == null) {
                throw new IOException("firebase-service-account.json not found in resources folder");
            }

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .setProjectId("jaruratcare-hopebot-1a52e")
                    .build()
                    .getService();

            boolean deleted = storage.delete(BlobId.of(bucket, object));
            if (deleted) {
                log.info("Deleted PDF: {}/{}", bucket, object);
            } else {
                log.warn("No file found for deletion: {}/{}", bucket, object);
            }

        } catch (Exception e) {
            log.error("Error deleting PDF for url {}: {}", url, e.getMessage(), e);
        }
    }
}
