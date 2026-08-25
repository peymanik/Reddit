## Introduction

This project is a simplified, and command line based version of Reddit. Here's an overview of its features:

## Features

1. Subreddits and Posts:
    - Reddit consists of many subreddits, each containing posts.
    - Users can create new subreddits or subscribe to existing ones.
    - Creating and interacting with posts within subreddits is straightforward, everything you need can be done by menu.

2. Voting System:
    - Users can upVote or downVote posts and comments.
    - Each user can vote only once for each post or comment.

3. Administration:
    - Every post, subreddit, and comment has its own owner (creator).
    - Subreddits can have additional admins, added by the subreddit owner.
    - Only owners or subreddit admins can remove posts and comments.

4. User Profiles:
    - Users can edit their profiles by setting or changing their name, nickname, email and password.

5. Search:
    - Users can search for specific phrases to find other users or subreddits.

6. Timeline:
    - When a new post is created in a subreddit that a user subscribes to, it will be added to the user's timeline.

## Project Structure

The project contains several classes, each in a separate file:

- Account: defines a user account.
- Post: Defines the structure of posts.
- Subreddit: subreddit attributes.
- Comment: defines Object of comments on posts.
- Main: Contains the main statements to run the program.

### Security

- All attributes of each class are defined as private and can only be changed using setter methods.
- The project uses a password hashing to securely store account passwords.

## Getting Started

1. Clone this repository to your local machine.
2. Compile and run the Main file.
3. Explore the features and interact with the program via the menu.