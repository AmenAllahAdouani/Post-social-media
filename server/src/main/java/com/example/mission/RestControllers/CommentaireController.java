package com.example.mission.RestControllers;

import com.example.mission.Entities.Commentaire;
import com.example.mission.Repositories.CommentaireRepositry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Commentaire")
@AllArgsConstructor
public class CommentaireController {
    @Autowired
    private CommentaireRepositry commentaireRepository;

    @GetMapping("/")
    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable Long id) {
        return commentaireRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @PutMapping("/{id}")
    public Commentaire updateCommentaire(@PathVariable Long id, @RequestBody Commentaire newCommentaire) {
        return commentaireRepository.findById(id)
                .map(commentaire -> {
                    commentaire.setContenu(newCommentaire.getContenu());
                    commentaire.setDateCreation(newCommentaire.getDateCreation());
                    commentaire.setPublication(newCommentaire.getPublication());
                    return commentaireRepository.save(commentaire);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentaire(@PathVariable Long id) {
        commentaireRepository.deleteById(id);
    }
}
