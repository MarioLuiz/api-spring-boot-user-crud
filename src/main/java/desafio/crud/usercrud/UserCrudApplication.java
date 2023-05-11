package desafio.crud.usercrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserCrudApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(UserCrudApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
    }

}
