package com.example.mission.Services;

import com.example.mission.Entities.Publication;

import java.util.List;

public interface IPublicationService {
    List<Publication> getAllPublications();
    Publication getPublicationById(Long id);
    Publication createPublication(Publication publication);
    Publication updatePublication(Long id, Publication newPublication);
    void deletePublication(Long id);
}
