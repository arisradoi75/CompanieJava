import role.Role;
public class User {
    private String username;
    private String password; // ATENȚIE: Într-o aplicație reală, parola ar trebui stocată ca un hash!
    private Role role;

    // Constructor gol necesar pentru Jackson
    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getteri și Setteri necesari pentru Jackson
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
