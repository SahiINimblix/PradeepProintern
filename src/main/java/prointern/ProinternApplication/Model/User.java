package prointern.ProinternApplication.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Username is required")
	@Column(unique = true, nullable=false)
	@Size(min = 2, max = 50, message = " Username must be between 2 and 50 characters")
    private String username;
	
	@NotBlank(message=" Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    
	@NotBlank(message = "Password is required")
	@Pattern(
			regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#%?&])[A-Za-z\\d@$!%*?&]{4,}$", // check this 
			message= " Password must be at least 8 characters and include uppercase, lowercase, number")
	private String password;
	

    @Column(nullable=false)
    private String passwordHash;

    // simple token for demo; real app should use JWT with expiry
    @Column(unique = true)
    private String token;
    
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'UNVERIFIED'")
    private String status;
    
    private int otp;

    public User(String username, String passwordHash) {
      this.username = username;
      this.passwordHash = passwordHash;
  }

//	public String getUsername() {
//		return this.username;
//	}
//
//	public Object getId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//		
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//		
//	}
//
//	public void setPasswordHash(String hashpw) {
//		this.passwordHash = hashpw;
//		
//	}
//
//	public String getPasswordHash() {
//		
//		return this.passwordHash;
//	}
//
//	public String getEmail() {
//		return this.email;
//	}
//
//	public void setPassword(String password2) {
//		this.password = password2;
//		
//	}
//
//	public Object getPassword() {
//		return this.password;
//	}
//
//	public void setToken(String token2) {
//		// TODO Auto-generated method stub
//		
//	}
	
    
}
