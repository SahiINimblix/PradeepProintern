//package prointern.ProinternApplication.PlacementAssistance.entities;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable=false)
//    private String username;
//
//    @Column(nullable=false)
//    private String passwordHash;
//
//    // simple token for demo; real app should use JWT with expiry
//    @Column(unique = true)
//    private String token;
//
//    public User() {}
//
//    public User(String username, String passwordHash) {
//        this.username = username;
//        this.passwordHash = passwordHash;
//    }
//
//    // getters & setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//
//    public String getPasswordHash() { return passwordHash; }
//    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
//
//    public String getToken() { return token; }
//    public void setToken(String token) { this.token = token; }
//}
