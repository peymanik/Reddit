import java.sql.Struct;
import java.util.ArrayList;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private String username;
    String password;
    String email;

    //profile
    String name = "";
    String nickname = "";

    ArrayList<Comment> userComments = new ArrayList<Comment>();
    ArrayList<Post> userPost  = new ArrayList<Post>();
    ArrayList<Post> timeline = new ArrayList<Post>();



    //-------------------------------------------------------------------

    public Account(String username, String password, String email){
        this.username = username;
        this.password = hashPassword(password);
        this.email = email;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return nickname;
    }
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(11);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return(hashed_password);
    }
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        return BCrypt.checkpw(password_plaintext, stored_hash);
    }
    public static boolean validateEmail(String email) {
        String regex = "(www)?.*@gmail.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
