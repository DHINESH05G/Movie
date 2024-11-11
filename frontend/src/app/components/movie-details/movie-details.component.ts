import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  movie: any = null;

  constructor(
    private route: ActivatedRoute,
    private movieService: MovieService
  ) {}

  ngOnInit(): void {
    const movieId = this.route.snapshot.paramMap.get('id');
    if (movieId) {
      this.movieService.getMovieById(movieId).subscribe(
        data => this.movie = data,
        error => console.error('Error fetching movie details:', error)
      );
    }
  }

  playMovie(): void {
    alert("Playing movie: " + this.movie.title);
    // Here you would handle the video player logic or route to a player page
  }
  addToWatchlist(): void {
    this.movieService.addToWatchlist(this.movie.id).subscribe(
      () => alert(`${this.movie.title} has been added to your watchlist!`),
      error => console.error('Error adding to watchlist:', error)
    );
  }
  
}
