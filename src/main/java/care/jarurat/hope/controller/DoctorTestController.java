package care.jarurat.hope.controller;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class DoctorTestController {

    @Autowired
    private Firestore firestore;

    @GetMapping("/doctors")
public Object getDoctors() throws Exception {
    QuerySnapshot snap = firestore.collection("doctors").get().get();

    return snap.getDocuments()
            .stream()
            .map(doc -> doc.getData()) // RETURN ONLY THE DATA MAP
            .toList();
}
@GetMapping("/doctors/spec")
public Object getDoctorsBySpecialization(@RequestParam String name) throws Exception {

    QuerySnapshot snap = firestore.collection("doctors").get().get();

    return snap.getDocuments()
            .stream()
            .map(doc -> doc.getData())
            .filter(data -> {
                Object spec = data.get("specialization");
                if (spec == null) return false;

                // case-insensitive contains match
                return spec.toString().toLowerCase().contains(name.toLowerCase());
            })
            .toList();
}
@GetMapping("/doctors/spec-exact")
public Object getDoctorsBySpecExact(@RequestParam String name) throws Exception {

    QuerySnapshot snap = firestore.collection("doctors")
            .whereEqualTo("specialization", name)
            .get()
            .get();

    return snap.getDocuments()
            .stream()
            .map(doc -> doc.getData())
            .toList();
}


}

