package bany.events.repositories.jpa;

import bany.events.entities.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueJpa extends JpaRepository<VenueEntity,Long> {
}
