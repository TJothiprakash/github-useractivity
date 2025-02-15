import java.util.Scanner;

public class GitHubUserActivitiesCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for GitHub username
        System.out.print("Enter GitHub Username: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("Error: Username cannot be empty. Please restart and enter a valid username.");
            return;
        }

        try {
            GitHubAPIHandler.fetchAndDisplayUserActivities(username);
        } catch (GitHubAPIException e) {
            System.err.println("API Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        } finally {
            scanner.close(); // Close scanner to prevent resource leak
        }
    }
}
