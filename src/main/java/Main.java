import javax.sound.midi.Soundbank;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Account> accounts = new ArrayList<Account>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        ArrayList<Subreddit> subreddits = new ArrayList<Subreddit>();
        ArrayList<Post> posts = new ArrayList<Post>();

        //read from files:
        File accountsFile = new File("accounts.txt");
        File commentsFile = new File("comments.txt");
        File subredditsFile = new File("subreddits.txt");
        File postsFile = new File("posts.txt");

        Scanner fileReader = new Scanner(accountsFile);
        while (fileReader.hasNextLine()) {
            String usernameData = fileReader.nextLine();
            String passwordData = fileReader.nextLine();
            String emailData = fileReader.nextLine();
            String nameData = fileReader.nextLine();
            String nicknameData = fileReader.nextLine();

            Account account = new Account(usernameData, passwordData, emailData);
            account.setName(nameData);
            account.setNickname(nicknameData);
            accounts.add(account);
        }

        fileReader = new Scanner(commentsFile);
        while (fileReader.hasNextLine()) {
            String ownerData = fileReader.nextLine();
            String bodyData = fileReader.nextLine();
            String belongedPostData = fileReader.nextLine();
            String belongedSubredditData = fileReader.nextLine();
            String karmaData = fileReader.nextLine();

            Comment comment = new Comment(ownerData, bodyData, belongedPostData, belongedSubredditData );
            comment.setKarma(Integer. parseInt(karmaData));
            comments.add(comment);
        }

        fileReader = new Scanner(postsFile);
        while (fileReader.hasNextLine()) {
            String bodyData = fileReader.nextLine();
            String ownerdData = fileReader.nextLine();
            String karmaData = fileReader.nextLine();
            String subredditData = fileReader.nextLine();
            String titleData = fileReader.nextLine();

            Post post = new Post(ownerdData, titleData, subredditData, bodyData);
            post.setKarma(Integer. parseInt(karmaData));
            posts.add(post);
        }

        fileReader = new Scanner(subredditsFile);
        while (fileReader.hasNextLine()) {
            String titleData = fileReader.nextLine();
            String ownerData = fileReader.nextLine();
            String karmaData = fileReader.nextLine();

            Subreddit subreddit = new Subreddit(titleData, ownerData);
            subreddit.setKarma(Integer. parseInt(karmaData));
            subreddits.add(subreddit);
        }

        boolean usernameExists = false;
        String usernameInput = null;
        int usernameIndex = -1;
        int postIndex = -1;
        int commentIndex = -1;
        Scanner scanner = new Scanner(System.in);
        int input;

        //startup users:
        Account user = new Account("peyman", "123","peyman@gmail.com");
        accounts.add(user);
        user = new Account("ali", "123","ali@gmail.com");
        accounts.add(user);
        user = new Account("alireza", "","alireza@gmail.com");
        accounts.add(user);

        //startup subreddits:
        Subreddit subreddit = new Subreddit("peymanSub", "peyman");
        subreddits.add(subreddit);
        subreddit = new Subreddit("aliSub", "Ali");
        subreddits.add(subreddit);

        //startup posts:
        Post post = new Post("peyman", "peymanPost", "peymanSub", "this is body of post");
        posts.add(post);
        subreddits.get(0).addPost("peymanPost");


        while (true) {
            System.out.println("1-Login \n2-Sign up \n3-Exit ");
            input = scanner.nextInt();

            if (input == 1) {
                System.out.print("Username: ");
                usernameInput = scanner.next();

                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUsername().equals(usernameInput)) {
                        usernameExists = true;
                    }
                }
                if (!usernameExists) {
                    System.out.println("Username doesn't exist");
                    continue;
                } else {
                    boolean repeat3 = true;
                    while (repeat3) {
                        System.out.print("Password: ");
                        String passInput = scanner.next();
                        for (int i = 0; i < accounts.size(); i++) {
                            if (accounts.get(i).getUsername().equals(usernameInput)) {
                                if (Account.checkPassword(passInput, accounts.get(i).getPassword())) {
                                    System.out.println("You are logged in");
                                    usernameIndex = i;
                                    repeat3 = false;
                                } else {
                                    System.out.println("Incorrect password");
                                }
                            }
                        }
                    }
                }
            } else if (input == 2) {
                boolean usernameIsCorrect = false;
                while (!usernameIsCorrect) {
                    usernameIsCorrect = true;
                    System.out.print("Username: ");
                    usernameInput = scanner.next();
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getUsername().equals(usernameInput)) {
                            System.out.println("This username has been taken");
                            usernameIsCorrect = false;
                        }
                    }
                }
                boolean emailIsCorrect = false;
                String emailInput = null;
                while (!emailIsCorrect) {
                    emailIsCorrect = true;
                    System.out.print("Email: ");
                    emailInput = scanner.next();
                    if (Account.validateEmail(emailInput)) {
                        for (int i = 0; i < accounts.size(); i++) {
                            if (accounts.get(i).getEmail().equals(emailInput)) {
                                System.out.println("This email has been taken");
                                emailIsCorrect = false;
                            }
                            accounts.get(usernameIndex).setEmail(emailInput);
                        }
                    } else {
                        System.out.println("Invalid email address");
                        emailIsCorrect = false;
                    }
                }
                System.out.print("Password: ");
                String passInput = scanner.next();
                user = new Account(usernameInput, passInput, emailInput);
                accounts.add(user);
                System.out.println("Account successfully created");
            } else if (input == 3) {
                FileWriter writer = new FileWriter(accountsFile);
                for (int i = 0 ; i<accounts.size() ; i++){
                    writer.write(accounts.get(i).getUsername()+"\n");
                    writer.write(accounts.get(i).getPassword()+"\n");
                    writer.write(accounts.get(i).getEmail()+"\n");
                    writer.write(accounts.get(i).getName()+"\n");
                    writer.write(accounts.get(i).getNickname()+"\n");
                }
                writer.close();

                writer = new FileWriter(commentsFile);
                for (int i = 0 ; i<accounts.size() ; i++){
                    writer.write(comments.get(i).getOwner()+"\n");
                    writer.write(comments.get(i).getBody()+"\n");
                    writer.write(comments.get(i).getBelongedPost()+"\n");
                    writer.write(comments.get(i).getBelongedSubreddit()+"\n");
                    writer.write(comments.get(i).getKarma()+"\n");
                }
                writer.close();

                writer = new FileWriter(postsFile);
                for (int i = 0 ; i<accounts.size() ; i++){
                    writer.write(posts.get(i).getBody()+"\n");
                    writer.write(posts.get(i).getOwner()+"\n");
                    writer.write(posts.get(i).getKarma()+"\n");
                    writer.write(posts.get(i).getSubreddit()+"\n");
                    writer.write(posts.get(i).getTitle()+"\n");
                }
                writer.close();

                writer = new FileWriter(subredditsFile);
                for (int i = 0 ; i<accounts.size() ; i++){
                    writer.write(subreddits.get(i).getTitle()+"\n");
                    writer.write(subreddits.get(i).getOwner()+"\n");
                    writer.write(subreddits.get(i).getKarma()+"\n");
                }
                writer.close();
                return;
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////

            while (true) {
                System.out.println("1-Subreddits \n2-Timeline \n3-Profile \n4-Search \n5-Logout");
                input = scanner.nextInt();
                if (input == 1) {
                    for (int i = 0; i < subreddits.size(); i++) {
                        System.out.println(i + 1 + "-" + subreddits.get(i).getTitle() + "    Votes:" + subreddits.get(i).getKarma());
                    }
                    while (true) {
                        System.out.println("1-view subreddit  2-New subreddit  3-Back");
                        input = scanner.nextInt();
                        if (input == 1) {
                            System.out.print("Enter number of subreddit: ");
                            int subredditIndex = scanner.nextInt() - 1;
                            int counter = 0;
                            for (int i = 0; i < subreddits.get(subredditIndex).posts.size(); i++) {
                                for (int j = 0; j < posts.size(); j++) {
                                    if (posts.get(j).getTitle().equals(subreddits.get(subredditIndex).posts.get(i))) {
                                        counter++;
                                        System.out.println(counter+"-"+posts.get(j).getTitle());
                                    }
                                }
                            }

                            System.out.println("1-View post  2-New post  3-Subscribe  4-Back");
                            input = scanner.nextInt();
                            if (input == 1) {
                                System.out.print("Post number:");
                                input = scanner.nextInt();
                                counter = 0;
                                for (int i = 0; i < subreddits.get(subredditIndex).posts.size(); i++) {
                                    for (int j = 0; j < posts.size(); j++) {
                                        if (posts.get(j).getTitle().equals(subreddits.get(subredditIndex).posts.get(i))) {
                                            counter++;
                                            if (counter == input) {
                                                postIndex = j;
                                                System.out.println("Title:"+posts.get(j).getTitle()+"   votes:"+posts.get(j).getKarma());
                                                System.out.println(posts.get(j).getBody());
                                                System.out.println("Comments:");
                                                for (int n = 0; n < comments.size(); n++) {
                                                    if (comments.get(n).getBelongedPost().equals(posts.get(postIndex).getTitle())) {
                                                        System.out.println(comments.get(n).getBody() + "   Votes:"+comments.get(n).getKarma());
                                                    }
                                                }
                                                System.out.println("1-Add comment \n2-Delete comment \n3-upVote post \n4-downVote post \n5-upVote comment \n6-downVote comment \n7-Back");
                                                input = scanner.nextInt();
                                                if (input == 1) {
                                                    System.out.println("Type your comment:");
                                                    String fakeInput = scanner.nextLine(); // correct input
                                                    String commentInput = scanner.nextLine();
                                                    Comment comment = new Comment(accounts.get(usernameIndex).getUsername(), commentInput, posts.get(postIndex).getTitle(), subreddits.get(subredditIndex).getTitle());
                                                    comments.add(comment);
                                                    System.out.println("Your comment added successfully");
                                                    break;
                                                } else if (input == 2) {
                                                    boolean isAdmin = false;
                                                    if (accounts.get(usernameIndex).getUsername().equals(posts.get(postIndex).getOwner())) {
                                                        isAdmin = true;
                                                    }
                                                    for (int n = 0; n < subreddits.get(subredditIndex).admins.size(); n++) {
                                                        if (subreddits.get(subredditIndex).admins.get(n).equals(accounts.get(usernameIndex).getUsername())) {
                                                            isAdmin = true;
                                                        }
                                                    }
                                                    if (isAdmin) {
                                                        System.out.print("Enter number of comment:");
                                                        input = scanner.nextInt();
                                                        counter = 0;
                                                        for (int n = 0; n < comments.size(); n++) {
                                                            if (comments.get(n).getBelongedPost().equals(posts.get(postIndex).getTitle())) {
                                                                counter++;
                                                                if (counter == input) {
                                                                    comments.remove(n);
                                                                    System.out.println("Comment successfully removed");
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("You don't have permission to delete a comment, Only admins can delete comments");
                                                    }
                                                    break;
                                                } else if (input == 3) {
                                                    boolean isVoted = false;
                                                    for (int n = 0; n < posts.get(postIndex).votedUsers.size(); n++) {
                                                        if (posts.get(postIndex).votedUsers.get(n).equals(accounts.get(usernameIndex).getUsername())) {
                                                            isVoted = true;
                                                        }
                                                    }
                                                    if (isVoted) {
                                                        System.out.println("You are already voted this post");
                                                    } else {
                                                        posts.get(postIndex).upVote();
                                                        posts.get(postIndex).votedUsers.add(accounts.get(usernameIndex).getUsername());
                                                        System.out.println("Voted post successfully");
                                                    }
                                                } else if (input == 4) {
                                                    boolean isVoted = false;
                                                    for (int n = 0; n < posts.get(postIndex).votedUsers.size(); n++) {
                                                        if (posts.get(postIndex).votedUsers.get(n).equals(accounts.get(usernameIndex).getUsername())) {
                                                            isVoted = true;
                                                        }
                                                    }
                                                    if (isVoted) {
                                                        posts.get(postIndex).downVote();
                                                        posts.get(postIndex).votedUsers.remove(accounts.get(usernameIndex).getUsername());
                                                        System.out.println("Your vote removed successfully");
                                                    } else {
                                                        System.out.println("You haven't this voted this yet");
                                                    }
                                                    continue;
                                                } else if (input == 5) {
                                                    boolean isVoted = false;
                                                    System.out.print("Enter number of comment:");
                                                    input = scanner.nextInt();
                                                    counter = 0;
                                                    for (int n = 0; n < comments.size(); n++) {
                                                        if (comments.get(n).belongedPost.equals(posts.get(postIndex).getTitle())) {
                                                            counter++;
                                                            if (counter == input) {
                                                                commentIndex = n;
                                                                for (int m = 0; m < comments.get(commentIndex).votedUsers.size(); m++) {
                                                                    if (accounts.get(usernameIndex).getUsername().equals(comments.get(commentIndex).votedUsers.get(m))) {
                                                                        isVoted = true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (isVoted) {
                                                        System.out.println("you are already voted this comment");
                                                    } else {
                                                        comments.get(commentIndex).upVote();
                                                        comments.get(commentIndex).votedUsers.add(accounts.get(usernameIndex).getUsername());
                                                    }
                                                } else if (input == 6) {
                                                    boolean isVoted = false;
                                                    System.out.print("Enter number of comment:");
                                                    input = scanner.nextInt();
                                                    counter = 0;
                                                    for (int n = 0; n < comments.size(); n++) {
                                                        if (comments.get(n).belongedPost.equals(posts.get(postIndex).getTitle())) {
                                                            counter++;
                                                            if (counter == input) {
                                                                commentIndex = n;
                                                                for (int m = 0; m < comments.get(commentIndex).votedUsers.size(); m++) {
                                                                    if (accounts.get(usernameIndex).getUsername().equals(comments.get(commentIndex).votedUsers.get(m))) {
                                                                        isVoted = true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (isVoted) {
                                                        comments.get(commentIndex).downVote();
                                                        comments.get(commentIndex).votedUsers.remove(accounts.get(usernameIndex).getUsername());
                                                    } else {
                                                        System.out.println("You haven't voted this comment yet");
                                                    }
                                                    break;
                                                } else if (input == 7) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }

                            } else if (input == 2) {
                                System.out.println("Enter title of post:");
                                String fakeInput = scanner.nextLine(); // to correct getting input
                                String titleInput = scanner.nextLine();
                                System.out.println("Enter Content of post:");
                                String bodyInput = scanner.nextLine();
                                post = new Post(accounts.get(usernameIndex).getUsername(), titleInput, subreddits.get(subredditIndex).getTitle(), bodyInput);
                                posts.add(post);
                                accounts.get(usernameIndex).userPost.add(post);
                                subreddits.get(subredditIndex).posts.add(titleInput);
                                for (int i = 0; i < subreddits.get(subredditIndex).subscribers.size(); i++) {
                                    for (int j = 0; j < accounts.size(); j++) {
                                        if (subreddits.get(subredditIndex).subscribers.get(i).equals(accounts.get(j).getUsername())) {
                                            accounts.get(j).timeline.add(post);
                                        }
                                    }
                                }
                                System.out.println("Your post created successfuly");
                                break;
                            } else if (input == 3) {
                                subreddits.get(subredditIndex).subscribers.add(accounts.get(usernameIndex).getUsername());
                                System.out.println("Subscribed to " + subreddits.get(subredditIndex).getTitle() + " Successfully");
                                continue;
                            } else if (input == 4) {
                                break;
                            }

                        } else if (input == 2) {
                            System.out.print("Enter title of subreddit:");
                            String get = scanner.nextLine(); // to correct getting input
                            String titleInput = scanner.nextLine();
                            subreddit = new Subreddit(titleInput, accounts.get(usernameIndex).getUsername());
                            subreddits.add(subreddit);
                            System.out.println("Subreddit successfully created");
                            break;
                        } else if (input == 3) {
                            break;
                        }
                    }
                } else if (input == 2) {
                    for (int i = accounts.get(usernameIndex).timeline.size() - 1; i >= 0; i--) {
                        System.out.println(accounts.get(usernameIndex).timeline.get(i).getTitle() + "   " + accounts.get(usernameIndex).timeline.get(i).getSubreddit());
                    }
                } else if (input == 3) {
                    System.out.println("Username: " + usernameInput);
                    System.out.println("Name: " + accounts.get(usernameIndex).getName());
                    System.out.println("Nickname: " + accounts.get(usernameIndex).getNickname());
                    System.out.println("Email: " + accounts.get(usernameIndex).getEmail());
                    System.out.println("1-Edit profile  2-Change password  3-Back");
                    input = scanner.nextInt();
                    if (input == 1) {
                        System.out.println("1-Edit name\n2-Edit nickname \n3-Edit Email 4-back");
                        input = scanner.nextInt();
                        if (input == 1) {
                            System.out.println("Enter new name:");
                            String fakeInput = scanner.nextLine(); // correct input
                            String nameInput = scanner.nextLine();
                            accounts.get(usernameIndex).setName(nameInput);
                            System.out.println("Name changed");
                        } else if (input == 2) {
                            System.out.println("Enter new nickname:");
                            String fakeInput = scanner.nextLine(); // correct input
                            String nicknameInput = scanner.nextLine();
                            accounts.get(usernameIndex).setNickname(nicknameInput);
                            System.out.println("Nickame changed");
                        } else if (input == 3) {
                            System.out.println("Enter new email:");
                            String fakeInput = scanner.nextLine(); // correct input
                            String emailInput = scanner.nextLine();
                            accounts.get(usernameIndex).setName(emailInput);
                            System.out.println("Email changed");
                        } else if (input == 4) {

                        }
                    } else if (input == 2) {
                        System.out.println("Enter new password:");
                        String fakeInput = scanner.nextLine(); // correct input
                        String passwordInput = scanner.nextLine();
                        accounts.get(usernameIndex).setPassword(Account.hashPassword(passwordInput));
                        System.out.println("Your password changed successfully");
                    } else if (input == 3) {
                        break;
                    }
                } else if (input == 4) {  //search in subreddits and users
                    System.out.println("Search:");
                    scanner.nextLine();
                    String searchInput = scanner.nextLine();
                    System.out.println("Users:");
                    for (int i = 0; i < accounts.size(); i++) {

                        Pattern pattern = Pattern.compile(".*" + searchInput + ".*");
                        Matcher matcher = pattern.matcher(accounts.get(i).getUsername());

                        if (matcher.find()) {
                            System.out.println(matcher.group());
                        }
                    }
                    System.out.println(" ");
                    System.out.println("Subreddits:");
                    for (int i = 0; i < subreddits.size(); i++) {

                        Pattern pattern = Pattern.compile(".*" + searchInput + ".*");
                        Matcher matcher = pattern.matcher(subreddits.get(i).getTitle());

                        if (matcher.find()) {
                            System.out.println(matcher.group());
                        }
                    }
                    System.out.println(" ");

                } else if (input == 5) {  // logout
                    break;
                }
            }
        }
    }
}


