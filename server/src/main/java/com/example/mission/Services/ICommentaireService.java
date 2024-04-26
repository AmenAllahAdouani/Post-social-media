package com.example.mission.Services;

import com.example.mission.Entities.Commentaire;

import java.util.List;

public interface ICommentaireService {
    List<Commentaire> getAllCommentaires();
    Commentaire getCommentaireById(Long id);
    Commentaire createCommentaire(Commentaire commentaire);
    Commentaire updateCommentaire(Long id, Commentaire newCommentaire);
    void deleteCommentaire(Long id);
}
