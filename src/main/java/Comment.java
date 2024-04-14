import java.util.ArrayList;

public class Comment{
    String owner;
    String body;
    String belongedPost;
    String belongedSubreddit;
    int karma = 0;

    ArrayList<String> votedUsers = new ArrayList<String>();


    public Comment(String owner, String body, String belongedPost, String belongedSubreddit){
        this.owner = owner;
        this.body = body;
        this.belongedPost = belongedPost;
        this.belongedSubreddit = belongedSubreddit;
    }


    void setOwner(String owner){
        this.owner = owner;
    }
    String getOwner(){
        return owner;
    }
    void setBody(String body){
        this.body = body;
    }
    String getBody(){
        return body;
    }
    public void setBelongedPost(String belongedPost) {
        this.belongedPost = belongedPost;
    }
    public String getBelongedPost() {
        return belongedPost;
    }

    public String getBelongedSubreddit() {
        return belongedSubreddit;
    }

    void upVote(){
        karma++;
    }

    void downVote(){
        karma--;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }
}