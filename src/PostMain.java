import exception.PostNotFoundException;

import java.util.Date;
import java.util.Scanner;

public class PostMain implements PostStorage {
    private static Scanner scanner = new Scanner(System.in);
    private static PostStorageImpl postStorageImpl = new PostStorageImpl();
    private static Date date = new Date();

    public static void main(String[] args) {

        outer:
        try {
            boolean isRun = true;
            while (isRun) {
                printCommands();
                int commands = Integer.parseInt(scanner.nextLine());
                switch (commands) {
                    case EXIT:
                        isRun = false;
                        break;
                    case ADD_POST:
                        addPost();
                        break;
                    case SEARCH_POST:
                        searchPost();
                        break;
                    case POST_BY_CATEGORY:
                        postByCategory();
                        break;
                    case ALL_POSTS:
                        postStorageImpl.printAllPosts();
                        break;
                    default:
                        System.out.println("Wrong command!");
                }
            }


        } catch (NumberFormatException e) {
            System.out.println("Please input number " + e);

        }

    }

    private static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_POST + " for ADD_POST");
        System.out.println("Please input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please input " + POST_BY_CATEGORY + " for POST_BY_CATEGORY");
        System.out.println("Please input " + ALL_POSTS + " for ALL_POSTS");

    }

    private static void addPost() {
        try {
            System.out.println("Please input Post data: title, text, category,");

            String postDataStr = scanner.nextLine();
            String[] postData = postDataStr.split(",");
            Post post = new Post();
            post.setTitle(postData[0]);
            post.setText(postData[1]);
            post.setCategory(postData[2]);
            post.setCreatedDate(date);
            postStorageImpl.add(post);
            System.out.println("Post was added!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Data! please try again");
            addPost();

        }
    }


    private static void searchPost() {
        if (postStorageImpl.isEmpty())
        { System.out.println("Please add post first");}
        System.out.println("Please input Post title or text for search");
        try {
            outer:
            System.out.println("press:1-search by title  pres:-2 for search by keyword pres:3-to go back ");

            boolean isRun = true;

            while (isRun) {
                int command = Integer.parseInt(scanner.nextLine());

                switch (command) {
                    case 1:
                        System.out.println("input title name");
                        String title = scanner.nextLine();
                        System.out.println(postStorageImpl.getPostByTitle(title));
                        break;
                    case 2:
                        System.out.println("input keyword name");
                        String keyword = scanner.nextLine();
                        postStorageImpl.searchPostsByKeyword(keyword);
                        break;
                    case 3:
                        isRun = false;
                        break;
                    default:
                        System.out.println("Wrong command!");

                }
            }
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect value! Please try again " + e);
        }


    }

    private static void postByCategory() {
        System.out.println("Please input category name for search");
        String searchedName = scanner.nextLine();
        postStorageImpl.printPostsByCategory(searchedName);


    }
}

