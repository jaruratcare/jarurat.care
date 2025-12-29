package care.jarurat.hope.controller;

import care.jarurat.hope.model.financials.NgoAndTrust;
import care.jarurat.hope.repository.financials.NgoAndTrustRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-ngos")
public class NgoTestController {

    private final NgoAndTrustRepository ngoRepo;

    public NgoTestController(NgoAndTrustRepository ngoRepo) {
        this.ngoRepo = ngoRepo;
    }

    @GetMapping
    public List<NgoAndTrust> getAllNgos() {
        List<NgoAndTrust> ngos = ngoRepo.findAll();

        System.out.println("Total NGOs fetched: " + ngos.size());
        ngos.forEach(ngo -> System.out.println(ngo.getNgoName())); // ✅ FIXED

        return ngos;
    }
}