package care.jarurat.hope.service;

import care.jarurat.hope.dto.DoctorDto;
import care.jarurat.hope.mapper.DoctorMapper;
import care.jarurat.hope.model.Doctor;
import care.jarurat.hope.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

  private final DoctorRepository repo;

  public List<DoctorDto> search(String city, String specialty) {
    try {
      if (city == null || city.trim().isEmpty())
        return List.of();

      List<Doctor> rawList = repo.findByCity(city.trim(), 50);

      if (rawList.isEmpty())
        return List.of();

      String spec = specialty.trim().toLowerCase(Locale.ROOT);

      return rawList.stream()
          .filter(d -> matchSpecialty(d, spec))
          .map(DoctorMapper::toDto)
          .collect(Collectors.toList());

    } catch (Exception e) {
      return List.of();
    }
  }

  private boolean matchSpecialty(Doctor d, String spec) {
    if (spec.isBlank() || spec.equals("other"))
      return true;
    if (d.getSpecialty() == null)
      return false;

    return d.getSpecialty().toLowerCase().contains(spec);
  }

  public DoctorDto details(String id) {
    try {
      return repo.findById(id)
          .map(DoctorMapper::toDto)
          .orElse(null);
    } catch (Exception e) {
      return null;
    }
  }
}
