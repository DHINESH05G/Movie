package com.dk.backend.controller;

import com.dk.backend.entity.Watchlist;
import com.dk.backend.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    // Get the user's watchlist
    @GetMapping
    public ResponseEntity<List<Watchlist>> getUserWatchlist(Principal principal) {
        List<Watchlist> watchlist = watchlistService.getUserWatchlist(principal.getName());
        return ResponseEntity.ok(watchlist);
    }

    // Add a movie to the watchlist
    @PostMapping("/{movieId}")
    public ResponseEntity<Watchlist> addMovieToWatchlist(@PathVariable Long movieId, Principal principal) {
        Watchlist watchlist = watchlistService.addMovieToWatchlist(principal.getName(), movieId);
        return ResponseEntity.status(HttpStatus.CREATED).body(watchlist);
    }

    // Remove a movie from the watchlist
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> removeMovieFromWatchlist(@PathVariable Long movieId, Principal principal) {
        watchlistService.removeMovieFromWatchlist(principal.getName(), movieId);
        return ResponseEntity.noContent().build();
    }
}