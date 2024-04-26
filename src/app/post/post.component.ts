import { Component } from '@angular/core';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  newPostText: string = '';
  imagePreview: string | ArrayBuffer | null = null;

  constructor() {}

  onTextChange(event: Event): void {
    const element = event.target as HTMLTextAreaElement;
    this.newPostText = element.value;
  }

  onImageChange(event: Event): void {
    const element = event.target as HTMLInputElement;
    if (element.files && element.files.length > 0) {
        const file = element.files[0];
        const reader = new FileReader();
        reader.onload = (e) => {
            if (e.target?.result) {
                this.imagePreview = e.target.result;
            } else {
                this.imagePreview = null;
            }
        };
        reader.readAsDataURL(file);
    } else {
        this.imagePreview = null;
    }
  }

  submitPost(): void {
    // submission logic here.
    console.log('Submitting Post:', this.newPostText);
    if (this.imagePreview) {
      console.log('With Image:', this.imagePreview);
    }

    this.newPostText = '';
    this.imagePreview = null;
  }
}
