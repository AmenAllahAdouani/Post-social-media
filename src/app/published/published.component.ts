import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Post {
  id: string; 
  text: string;
}

interface Comment {
  author: string;
  content: string;
}

@Component({
  selector: 'app-published',
  standalone: true,
  imports: [HttpClient],
  templateUrl: './published.component.html',
  styleUrls: ['./published.component.css']
})
export class PublishedComponent {
  post: Post = {
    id: '1',
    text: 'This is a sample social media post.'
  };
  comments: Comment[] = [
    { author: "Flen Fouleni", content: "hhhhhhhhhhhhhhhh" },
    { author: "Fleena Foulenia", content: "hahahahahahahahahahah" }
  ];
  showCommentBox = false;
  commentText = '';
  showDropdown = false;
  showUpdateModal = false;
  editableText = this.post.text;
  private http = inject(HttpClient);

  onReactionClick() {
    console.log('Reaction button clicked');
  }

  toggleCommentBox() {
    this.showCommentBox = !this.showCommentBox;
  }

  onShareClick() {
    console.log('Share button clicked');
  }

  submitComment() {
    if (this.commentText.trim() !== '') {
      const newComment = { author: "You", content: this.commentText };
      this.http.post<Comment>('http://localhost:8000/api/comments', newComment)
        .subscribe({
          next: (response) => {
            this.comments.push(response);
            this.commentText = '';
            this.showCommentBox = false;
          },
          error: (error) => console.error('Error submitting comment', error)
        });
    }
  }

  toggleDropdown() {
    this.showDropdown = !this.showDropdown;
  }

  deletePost() {
    this.http.delete(`http://localhost:8000/api/posts/${this.post.id}`)
      .subscribe({
        next: () => {
          console.log('Post deleted successfully');
        },
        error: (error) => console.error('Error deleting post', error)
      });
  }

  openUpdateModal() {
    this.showDropdown = false;
    this.showUpdateModal = true;
  }

  closeUpdateModal() {
    this.showUpdateModal = false;
  }

  updatePost() {
    this.http.put<Post>(`http://localhost:8000/api/posts/${this.post.id}`, { text: this.editableText })
      .subscribe({
        next: (response) => {
          this.post = response;
          this.closeUpdateModal();
          console.log('Post updated with new text');
        },
        error: (error) => console.error('Error updating post', error)
      });
  }
}
