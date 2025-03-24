package com.example.movie;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("movies.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray jsonArray = new JSONArray(jsonString.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String title = obj.optString("title");
                    Integer year = parseYear(obj.opt("year"));
                    String genre = obj.optString("genre");
                    String poster = obj.optString("poster");
                    if (title.isEmpty() && obj.length() == 0) continue;
                    movies.add(new Movie(title, year, genre, poster));
                } catch (JSONException | IllegalArgumentException e) {
                    Log.e("JsonUtils", "Invalid entry: " + e.getMessage());
                }
            }
        } catch (IOException | JSONException e) {
            Log.e("JsonUtils", "Error: " + e.getMessage());
            return null;
        }
        return movies;
    }

    private static Integer parseYear(Object yearObj) {
        try {
            if (yearObj instanceof Integer) return (Integer) yearObj;
            if (yearObj instanceof String) return Integer.parseInt((String) yearObj);
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}