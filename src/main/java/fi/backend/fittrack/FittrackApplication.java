package fi.backend.fittrack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.backend.fittrack.domain.AppUser;
import fi.backend.fittrack.domain.AppUserRepository;

@SpringBootApplication
public class FittrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FittrackApplication.class, args);
	}

	@Bean
	public CommandLineRunner workoutDemo(AppUserRepository urepository) {
		return (args) -> {
			AppUser user1 = new AppUser("customer", "$2a$12$LBIzBYOuG7IccohqUZz0EuM1VPQqz/mnYhQsZe.u9fJnkRTuy27oa", "USER");
			AppUser user2 = new AppUser("trainer", "$2a$12$lKx5n19UZ9ry0enJNMJun.YO4FTMc34fkGEr50BQpSfFakinYz4z.", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			//customer password = password
			// trainer password = adminpassword
		};
	}
}