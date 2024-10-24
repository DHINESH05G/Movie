package com.dk.backend.controller;

import com.dk.backend.entity.Movie;
import com.dk.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Get all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // Search movies by title
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam("title") String title) {
        List<Movie> movies = movieService.searchMovies(title);
        return ResponseEntity.ok(movies);
    }

    // Get movies by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable("genre") String genre) {
        List<Movie> movies = movieService.getMoviesByGenre(genre);
        return ResponseEntity.ok(movies);
    }

    // Add new movie (admin only)
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    // Update movie (admin only)
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        return ResponseEntity.ok(updatedMovie);
    }

    // Delete movie (admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}

