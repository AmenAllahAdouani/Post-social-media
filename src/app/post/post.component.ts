import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  newPostText: string = '';

  constructor(private http: HttpClient) {}

  onTextChange(event: Event): void {
    const element = event.target as HTMLTextAreaElement;
    this.newPostText = element.value;
  }

  submitPost(): void {
    if (this.newPostText.trim()) {
      this.http.post('http://localhost:8000/api/posts/{id}', { text: this.newPostText })
        .subscribe({
          next: (response) => {
            console.log('Post submitted', response);
            this.newPostText = '';
          },
          error: (error) => {
            console.error('Error submitting post', error);
          }
        });
    }
  }
}
