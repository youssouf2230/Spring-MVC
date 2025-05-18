# Spring-MVC
Activité 3 SYSTEME DISTRIBUE.

## Partie 1 : 
Créer une application Web JEE basée sur Spring MVC, Thylemeaf et Spring Data JPA qui permet de gérer les patients.\n
L'application doit permettre les fonctionnalités suivantes :
# Afficher les patients

![image](https://github.com/user-attachments/assets/1d141df2-51de-4326-ad23-544ac4778245)

![image](https://github.com/user-attachments/assets/92c08764-dfcf-4ae2-ba2c-bc06f7d7cb7e)

![image](https://github.com/user-attachments/assets/9da947e5-4adb-48b5-839f-b19dc0e7c7ad)

## Faire la pagination
page 0:
![image](https://github.com/user-attachments/assets/c2e18a57-8926-48a4-a618-e2a7a241966a)
page 1 :
![image](https://github.com/user-attachments/assets/2e7cfbdb-04c8-42e9-b886-7cee11ffd0b0)

default page :
![image](https://github.com/user-attachments/assets/7f16aecb-b84a-4c47-b9e7-37dcf847929b)
En utilisant modèle :
![image](https://github.com/user-attachments/assets/6e7c5576-14df-4cde-b906-9348e3068f57)

# Chercher les patients
![image](https://github.com/user-attachments/assets/9564ddc3-8afe-4ce3-9856-cb5abdd81b13)

# Supprimer un patient
![image](https://github.com/user-attachments/assets/f18a2963-713d-435c-8d26-e7fb5423082d)

# Faire des améliorations supplémentaires
![image](https://github.com/user-attachments/assets/4bff12c8-2302-4e7c-86ed-71863419e6ac)

## Partie 2  : https://www.youtube.com/watch?v=eoBE745lDE0
Créer une page template
Faire la validation des formulaires
Partie 3 : Sécurité avec Spring security  : 
   - InMemomy Authentication:  https://www.youtube.com/watch?v=7VqpC8UD1zM
   - JDBC Authentication : https://www.youtube.com/watch?v=Haz3wLiQ5-0
   - UserDetails Service : https://www.youtube.com/watch?v=RTiS9ygyYs4

Mise à jour du code pour Spring Boot 3.2 :

package ma.enset.hospitalapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
String encodedPassword = passwordEncoder.encode("1234");
System.out.println(encodedPassword);
return new InMemoryUserDetailsManager(
User.withUsername("user1").password(encodedPassword).roles("USER").build(),
User.withUsername("user2").password(encodedPassword).roles("USER").build(),
User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
);
}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
return httpSecurity
.formLogin(Customizer.withDefaults())
.authorizeHttpRequests(ar->ar.requestMatchers("/deletePatient/**").hasRole("ADMIN"))
.authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
.authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
.authorizeHttpRequests(ar->ar.anyRequest().authenticated())
.build();
}
}

