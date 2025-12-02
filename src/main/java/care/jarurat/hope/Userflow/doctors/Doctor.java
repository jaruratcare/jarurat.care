package care.jarurat.hope.Userflow.doctors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
  private String name;
  private String hospital;
  private String specialization;
  private String opdTimings;
  private int experienceYears;
  private String description;
  private String contact;
  private String city;
  private String website;
}
