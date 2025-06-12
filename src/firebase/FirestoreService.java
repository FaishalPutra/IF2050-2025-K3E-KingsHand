package firebase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FirestoreService {

    public static String getUserName(String idToken, String uid) throws Exception {
        String documentPath = FirebaseConfig.DATABASE_URL + "/users/" + uid;
        URL url = new URL(documentPath);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + idToken);
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new Exception("Gagal ambil data user: " + conn.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
        JsonObject fields = json.getAsJsonObject("fields");

        if (fields != null && fields.has("Nama")) {
            return fields.getAsJsonObject("Nama").get("stringValue").getAsString();
        } else {
            return "(nama tidak ditemukan)";
        }
    }
}
