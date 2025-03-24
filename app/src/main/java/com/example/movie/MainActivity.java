package com.example.movie;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        errorMessage = findViewById(R.id.errorMessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Movie> movies = JsonUtils.loadMoviesFromJson(this);
        if (movies == null || movies.isEmpty()) {
            showError("Loading failed, please check the data file");
            return;
        }

        MovieAdapter adapter = new MovieAdapter(movies);
        recyclerView.setAdapter(adapter);
    }

    private void showError(String message) {
        recyclerView.setVisibility(View.GONE);
        errorMessage.setVisibility(View.VISIBLE);
        errorMessage.setText(message);
    }
}