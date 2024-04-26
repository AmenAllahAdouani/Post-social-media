package com.example.mission.Services;

import com.example.mission.Entities.Publication;
import com.example.mission.Repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService implements IPublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication getPublicationById(Long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        return optionalPublication.orElse(null);
    }

    @Override
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication updatePublication(Long id, Publication newPublication) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();
            publication.setTitre(newPublication.getTitre());
            publication.setContenu(newPublication.getContenu());
            publication.setDateCreation(newPublication.getDateCreation());
            return publicationRepository.save(publication);
        }
        return null;
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }
}
