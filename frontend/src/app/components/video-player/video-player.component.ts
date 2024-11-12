import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnInit {
  @Input() movieId: string = '';
  videoUrl: SafeUrl | undefined;

  constructor(private movieService: MovieService, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.loadVideo();
  }

  loadVideo(): void {
    this.movieService['getMovieStreamUrl'](this.movieId).subscribe(
      (      url: string) => this.videoUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url),
      (      error: any) => console.error('Error loading video:', error)
    );
  }
}
