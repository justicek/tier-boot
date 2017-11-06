package toptier.model.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import toptier.model.common.Auditable;

import javax.validation.constraints.NotNull;

public class User extends Auditable {

    @NotNull @Length(min = 2, max = 20)
    private String username;

    @NotNull @Email
    private String email;

    @NotNull @Length(min = 6, max = 32)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
