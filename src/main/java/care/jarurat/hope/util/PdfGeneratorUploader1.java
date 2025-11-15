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

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PdfGeneratorUploader1 {

    public static String generateAndUploadPdf(String content, String userId) throws IOException {
        InputStream fontStream = PdfGeneratorUploader1.class.getClassLoader()
                .getResourceAsStream("fonts/NotoSansDevanagari_Condensed-Black.ttf");
        if (fontStream == null) throw new IOException("Font file not found in resources folder");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        PdfFont font = PdfFontFactory.createFont(
                fontStream.readAllBytes(),
                PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED
        );

        document.add(new Paragraph(content).setFont(font).setFontSize(12));
        document.close();

        String bucketName = "jaruratcare-hopebot-1a52e.firebasestorage.app";
        String fileName = "emotional_care/" + userId + ".pdf";

        StorageClient.getInstance()
                .bucket(bucketName)
                .create(fileName, new ByteArrayInputStream(outputStream.toByteArray()), "application/pdf");

        InputStream serviceAccountStream = PdfGeneratorUploader1.class.getClassLoader()
                .getResourceAsStream("firebase-service-account.json");
        if (serviceAccountStream == null) throw new IOException("firebase-service-account.json not found");

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("jaruratcare-hopebot-1a52e")
                .build()
                .getService();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();
        return storage.signUrl(blobInfo, 15, TimeUnit.MINUTES).toString();
    }

    public static void deletePdfFromUrl(String url) {
        if (url == null || url.isBlank()) return;
        try {
            String bucket = "jaruratcare-hopebot-1a52e.firebasestorage.app";
            String object;

            if (url.contains("/o/")) {
                int oIndex = url.indexOf("/o/");
                int qIndex = url.indexOf("?", oIndex);
                String encObject = (qIndex > 0) ? url.substring(oIndex + 3, qIndex) : url.substring(oIndex + 3);
                object = URLDecoder.decode(encObject, StandardCharsets.UTF_8);
            } else if (url.startsWith("gs://")) {
                String rest = url.substring(5 + bucket.length());
                object = rest.startsWith("/") ? rest.substring(1) : rest;
            } else return;

            InputStream serviceAccountStream = PdfGeneratorUploader1.class.getClassLoader()
                    .getResourceAsStream("firebase-service-account.json");
            if (serviceAccountStream == null) throw new IOException("firebase-service-account.json not found");

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .setProjectId("jaruratcare-hopebot-1a52e")
                    .build()
                    .getService();

            boolean deleted = storage.delete(BlobId.of(bucket, object));
            if (deleted) log.info("Deleted PDF: {}/{}", bucket, object);
            else log.warn("No file found for deletion: {}/{}", bucket, object);
        } catch (Exception e) {
            log.error("Error deleting PDF for url {}: {}", url, e.getMessage(), e);
        }
    }
}
