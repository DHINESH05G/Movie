import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  movies: any[] = [];

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.movieService.getMovies().subscribe(
      data => this.movies = data,
      error => console.error('Error fetching movies:', error)
    );
  }

  onSearch(searchTerm: string): void {
    if (searchTerm) {
      this.movieService['searchMovies'](searchTerm).subscribe(
        (data: any[]) => this.movies = data,
        (error: any) => console.error('Error searching movies:', error)
      );
    } else {
      this.loadMovies();
    }
  }
}
