package firebase;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class FirebaseAuthService {

    public static class AuthResponse {
        public String idToken;
        public String uid;

        public AuthResponse(String idToken, String uid) {
            this.idToken = idToken;
            this.uid = uid;
        }
    }

    public static AuthResponse login(String email, String password) throws Exception {
        URL url = URI.create(FirebaseConfig.AUTH_URL).toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String requestBody = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                email, password);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(requestBody.getBytes());
        }

        if (conn.getResponseCode() != 200) {
            BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            StringBuilder errorResponse = new StringBuilder();
            String line;
            while ((line = err.readLine()) != null) {
                errorResponse.append(line);
            }
            throw new Exception("HTTP Error " + conn.getResponseCode() + ": " + errorResponse);
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }

        JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
        String idToken = jsonResponse.get("idToken").getAsString();
        String uid = jsonResponse.get("localId").getAsString();

        return new AuthResponse(idToken, uid);
    }
}
