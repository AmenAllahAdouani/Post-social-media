package com.example.mission.Services;

import com.example.mission.Entities.Commentaire;
import com.example.mission.Repositories.CommentaireRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireService implements ICommentaireService {
    @Autowired
     private CommentaireRepositry commentaireRepository;

        @Override
        public List<Commentaire> getAllCommentaires() {
            return commentaireRepository.findAll();
        }

        @Override
        public Commentaire getCommentaireById(Long id) {
            Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(id);
            return optionalCommentaire.orElse(null);
        }

        @Override
        public Commentaire createCommentaire(Commentaire commentaire) {
            return commentaireRepository.save(commentaire);
        }

        @Override
        public Commentaire updateCommentaire(Long id, Commentaire newCommentaire) {
            Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(id);
            if (optionalCommentaire.isPresent()) {
                Commentaire commentaire = optionalCommentaire.get();
                commentaire.setContenu(newCommentaire.getContenu());
                commentaire.setDateCreation(newCommentaire.getDateCreation());
                commentaire.setPublication(newCommentaire.getPublication());
                return commentaireRepository.save(commentaire);
            }
            return null;
        }

        @Override
        public void deleteCommentaire(Long id) {
            commentaireRepository.deleteById(id);
        }
    }