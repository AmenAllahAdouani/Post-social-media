package com.example.mission.RestControllers;


import com.example.mission.Entities.Publication;
import com.example.mission.Repositories.PublicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Publication")
@AllArgsConstructor
public class PublicationController {
    @Autowired
    private PublicationRepository publicationRepository;

    @GetMapping("/")
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publication getPublicationById(@PathVariable Long id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Publication createPublication(@RequestBody Publication publication) {
        return publicationRepository.save(publication);
    }

    @PutMapping("/{id}")
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication newPublication) {
        return publicationRepository.findById(id)
                .map(publication -> {
                    publication.setTitre(newPublication.getTitre());
                    publication.setContenu(newPublication.getContenu());
                    publication.setDateCreation(newPublication.getDateCreation());
                    return publicationRepository.save(publication);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePublication(@PathVariable Long id) {
        publicationRepository.deleteById(id);
    }
}
