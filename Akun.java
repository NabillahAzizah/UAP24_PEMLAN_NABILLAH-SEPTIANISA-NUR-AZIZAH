package UAP24_PEMLAN_NAbillahSeptianisa;

public class Akun {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void login(User user) {
        currentUser.set(user);
    }

    public static void logout() {
        currentUser.remove();
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }
}