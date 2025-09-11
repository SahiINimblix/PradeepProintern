package prointern.ProinternApplication.Model;

import jakarta.persistence.*; // JPA

@Entity
public class Notifier
{
    @Id
    String email;

    String name;

    public Notifier() { }

    public Notifier(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
