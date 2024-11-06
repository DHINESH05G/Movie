import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: any = null;

  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    this.profileService.getUserProfile().subscribe({
      next: data => this.user = data,
      error: err => console.log('Error fetching profile', err)
    });
  }

  onSubmit(): void {
    this.profileService.updateUserProfile(this.user).subscribe({
      next: response => alert('Profile updated successfully!'),
      error: err => console.log('Error updating profile', err)
    });
  }
}