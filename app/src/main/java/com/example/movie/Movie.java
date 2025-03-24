package com.example.movie;

import java.util.Calendar;

public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private String posterResource;

    public Movie(String title, Integer year, String genre, String posterResource) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year == null || year < 1888 || year > currentYear) {
            this.year = null;
        } else {
            this.year = year;
        }

        this.genre = (genre == null || genre.isEmpty()) ? "N/A" : genre;
        this.posterResource = (posterResource == null) ? "default_poster" : posterResource;
    }

    // Getters
    public String getTitle() { return title; }
    public Integer getYear() { return year; }
    public String getGenre() { return genre; }
    public String getPosterResource() { return posterResource; }
}
