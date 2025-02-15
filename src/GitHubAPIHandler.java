import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GitHubAPIHandler {

    public static void fetchAndDisplayUserActivities(String username) throws GitHubAPIException {
        String urlString = "https://api.github.com/users/" + username + "/events";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                    System.out.println(line+"\n");
                }
                reader.close();

                filterRecentEvents(response.toString());

            } else {
                throw new GitHubAPIException("Failed to fetch data. Response Code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            throw new GitHubAPIException("Error fetching user activities: " + e.getMessage());
        }
    }

    private static void filterRecentEvents(String jsonResponse) {
        LocalDate threeMonthsAgo = LocalDate.now().minus(3, ChronoUnit.MONTHS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Extract only relevant event details using basic JSON parsing
        String[] events = jsonResponse.split("\\},\\{"); // Splitting at each event block

        System.out.println("Recent GitHub Activities (Last 3 Months):");

        for (String event : events) {
            try {
                // Extract "created_at"
                String createdAt = extractValue(event, "\"created_at\":\"");
                if (createdAt == null) continue;
                LocalDate eventDate = LocalDate.parse(createdAt.substring(0, 10), formatter);

                // Only process events within the last 3 months
                if (!eventDate.isBefore(threeMonthsAgo)) {
                    String eventType = extractValue(event, "\"type\":\"");
                    System.out.println("- " + (eventType != null ? eventType : "Unknown Event") + " on " + eventDate);
                }
            } catch (Exception e) {
                System.out.println("Skipping an invalid event entry.");
            }
        }
    }

    // Extract a value from JSON manually without using libraries
    private static String extractValue(String json, String key) {
        if (!json.contains(key)) return null;
        try {
            return json.split(key)[1].split("\"")[0];
        } catch (Exception e) {
            return null;
        }
    }
}
