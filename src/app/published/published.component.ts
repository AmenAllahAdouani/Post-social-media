import { Component } from '@angular/core';

interface Post {
  profilePicture: string;
  text: string;
  image?: string;
}

interface Comment {
  author: string;
  content: string;
}

@Component({
  selector: 'app-published',
  standalone: true,
  imports: [],
  templateUrl: './published.component.html',
  styleUrls: ['./published.component.css']
})
export class PublishedComponent {
  post: Post = {
    profilePicture: 'path/to/profile-picture.jpg',
    text: 'This is a sample social media post.',
    image: 'path/to/post-image.jpg'
  };
  comments: Comment[] = [
    { author: "Jane Doe", content: "Wow, that’s amazing!" },
    { author: "John Smith", content: "Can’t wait to see more!" }
  ];
  showCommentBox = false;
  commentText = '';
  showDropdown = false;
  showUpdateModal = false;
  editableText = this.post.text;
  editableImage = this.post.image;

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
      this.comments.push({ author: "You", content: this.commentText });
      this.commentText = ''; // Reset comment text after submission
      this.showCommentBox = false;
    }
  }

  toggleDropdown() {
    this.showDropdown = !this.showDropdown;
  }

  deletePost() {
    console.log('Delete the post');
    // Logic to delete the post can be added here
  }

  openUpdateModal() {
    this.showDropdown = false; // Close the dropdown when opening the modal
    this.showUpdateModal = true;
  }

  closeUpdateModal() {
    this.showUpdateModal = false;
  }

  onImageSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.editableImage = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  updatePost() {
    this.post.text = this.editableText;
    if (this.editableImage) {
      this.post.image = this.editableImage;
    }
    this.closeUpdateModal();
    console.log('Post updated with new text and possibly new image');
  }
}
