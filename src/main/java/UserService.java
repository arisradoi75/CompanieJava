import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import role.Role;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final String USER_FILE_PATH = "src/main/resources/users.json";
    private ObjectMapper objectMapper;
    private List<User>users;

    public UserService(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.users = citire();
    }

    public List<User> citire(){
        try{
            File file = new File(USER_FILE_PATH);
            users = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            return users;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void scriere(List<User> users){
        try{
            File file = new File(USER_FILE_PATH);
            objectMapper.writeValue(file, users);
        }catch (Exception e){
            System.out.println("eroare");
        }
    }

    public Optional<User> login(String username, String password){
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    public boolean register(String username , String password){
        boolean exista = users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
        if(exista){
            return false;
        }
        User newUser = new User(username , password , Role.USER);
        this.users.add(newUser);

        scriere(users);
        return true;
    }



}
