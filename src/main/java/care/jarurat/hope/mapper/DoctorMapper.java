package care.jarurat.hope.mapper;

import care.jarurat.hope.dto.DoctorDto;
import care.jarurat.hope.model.Doctor;

public class DoctorMapper {

  public static DoctorDto toDto(Doctor d) {

    DoctorDto dto = new DoctorDto();

    dto.id = d.getId();
    dto.name = d.getName();
    dto.specialty = d.getSpecialty();
    dto.hospitalName = d.getHospitalName();
    dto.city = d.getCity();
    dto.address = d.getAddress();
    dto.opdTimings = d.getOpdTimings();
    dto.experience = d.getExperience();
    dto.phone = d.getPhone();
    dto.description = d.getDescription();
    dto.website = d.getWebsite();

    return dto;
  }
}
