import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.ArrayList;

public class Subreddit {
    private String  title;
    private String owner;
    private int karma = 0;

    ArrayList<String> admins = new ArrayList<String>();
    ArrayList<String> posts = new ArrayList<String>();
    ArrayList<String> subscribers = new ArrayList<String>();

    public Subreddit(String title, String owner){
        this.title = title;
        this.owner = owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setKarma(int karma) {
        this.karma = karma;
    }
    public int getKarma() {
        return karma;
    }
    public void addPost(String post){
        this.posts.add(post);
    }
    public void addAdmin(String admin){
        admins.add(admin);
    }

}
