package care.jarurat.hope.controller;

import care.jarurat.hope.service.CsvDoctorUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CsvDoctorUploadService uploadService;

    @GetMapping("/upload-doctors")
    public String upload() {
        String path = "C:/Users/srishti/Desktop/jaruratcare-hopebot/doctors.csv";
        return uploadService.uploadDoctorsCSV(path);
    }
}
