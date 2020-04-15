import exception.PostNotFoundException;

public class PostStorageImpl {
    private Post[] posts;
    int size = 0;

    private void extend() {
        Post[] tmp = new Post[posts.length + 10];
        System.arraycopy(posts, 0, tmp, 0, posts.length);
        posts = tmp;
    }

    public PostStorageImpl() {
        posts = new Post[16];
    }

    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    public Post getPostByTitle(String title) throws PostNotFoundException {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equalsIgnoreCase(title)) {
                return posts[i];
            } else throw new PostNotFoundException("Your" + title + " nothing was found in the survey");

        }
        return null;

    }

    void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
                break;
            } else System.out.println(keyword + " Nothing was found in the survey");


        }
    }

    void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);

        }

    }

    void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getCategory().equalsIgnoreCase(category)) {
                System.out.println(posts[i]);
                break;
            } else System.out.println(category + " Nothing was found in the survey");


        }
    }

    public boolean isEmpty() {
        return size == 0;

    }
}
