import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuard } from './guards/auth.guard';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';

const routes: Routes = [
  { path: '**', redirectTo: '' },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'movies/:id', component: MovieDetailsComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
