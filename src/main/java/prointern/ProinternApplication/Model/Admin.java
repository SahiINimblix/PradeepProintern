package prointern.ProinternApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp="^[A-Z][a-z]{2,11}( [A-Z][a-z]{2,11})*$")
	private String name;
	
	@Pattern(regexp = "[a-z0-9+._]+@[a-z]+[0-9]*[a-z.]*\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	@Pattern(regexp="[a-z]+[A-z]+[0-9]+[!@#$%^&*.]+",message = "Password should contain atleast one uppcase,lowercase,digit,special character(!@#$%^&*.).")
	@Size(min = 6, max = 12, message = "Password length should be between 6 to 12 letters.")
	private String password;
}
