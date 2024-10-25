package com.dk.backend.service;

import com.dk.backend.entity.*;
import com.dk.backend.repository.*;
import com.dk.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieRepository movieRepository;

    public List<Watchlist> getUserWatchlist(String username) {
        User user = userService.getUserByUsername(username);
        return watchlistRepository.findByUser(user);
    }

    public Watchlist addMovieToWatchlist(String username, Long movieId) {
        User user = userService.getUserByUsername(username);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        if (watchlistRepository.findByUserAndMovie(user, movie).isPresent()) {
            throw new IllegalStateException("Movie already in watchlist");
        }

        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setMovie(movie);
        return watchlistRepository.save(watchlist);
    }

    public void removeMovieFromWatchlist(String username, Long movieId) {
        User user = userService.getUserByUsername(username);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        Watchlist watchlist = watchlistRepository.findByUserAndMovie(user, movie)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not in watchlist"));

        watchlistRepository.deleteByUserAndMovie(user, movie);
    }
}

