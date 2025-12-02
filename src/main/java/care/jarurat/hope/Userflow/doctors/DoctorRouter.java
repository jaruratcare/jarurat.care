package care.jarurat.hope.Userflow.doctors;

import care.jarurat.hope.model.User;
import org.springframework.stereotype.Component;

@Component
public class DoctorRouter {

  private final DoctorHandler doctorHandler;

  public DoctorRouter(DoctorHandler doctorHandler) {
    this.doctorHandler = doctorHandler;
  }

  public Object handle(User user, String input) {
    return doctorHandler.handle(user, input);
  }
}
