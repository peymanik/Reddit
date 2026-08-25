import java.util.ArrayList;

public class Post {
    private String body = "";
    private String owner;
    private int karma = 0;
    private String subreddit;
    private String title;

    ArrayList<String> votedUsers = new ArrayList<String>();

    public Post(String owner , String title , String subreddit, String body) {
        this.owner = owner;
        this.title = title;
        this.subreddit = subreddit;
        this.body = body;
    }


    public void upVote(){
        karma++;
    }
    public void downVote() {
        karma--;
    }
    public int getKarma() {return karma;}
    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return body;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }
    public void setSubredit(Subreddit subrediit) {
        this.subreddit = subreddit;
    }
    public String getSubreddit(){
        return subreddit;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

}
