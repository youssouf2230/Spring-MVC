package net.youssouf.hopital;

import net.youssouf.hopital.entities.Patient;
import net.youssouf.hopital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {

		SpringApplication.run(HopitalApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		/**
		// 1ere manière
		Patient patient = new Patient();
		patient.setName("Koné Aliou");
		patient.setBirthdate(new Date());
		patient.setHealth(false);
		patient.setScore(200);

		// 2eme manière
		Patient patient1 = new Patient(0,"Yassine",new Date(),false,123);

		// Manière
		Patient patient2 = Patient.builder()
							.name("Badara")
							.birthdate(new Date())
							.health(false)
							.score(142)
							.build();
		patientRepository.save(patient);
		patientRepository.save(patient1);
		patientRepository.save(patient2);
		List<Patient> patients = patientRepository.findAll();
		for (Patient p : patients) {
			System.out.println(p.toString());
		}
		 */
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
