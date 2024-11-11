import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  watchlist: any[] = [];

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.loadWatchlist();
  }

  loadWatchlist(): void {
    this.movieService.getWatchlist().subscribe(
      data => this.watchlist = data,
      error => console.error('Error loading watchlist:', error)
    );
  }

  removeFromWatchlist(movieId: string): void {
    this.movieService.removeFromWatchlist(movieId).subscribe(
      () => this.loadWatchlist(), // Reload watchlist after removing item
      error => console.error('Error removing from watchlist:', error)
    );
  }
}
