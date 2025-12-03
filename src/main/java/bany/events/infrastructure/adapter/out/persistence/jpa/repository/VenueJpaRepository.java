package bany.events.infrastructure.adapter.out.persistence.jpa.repository;

import bany.events.infrastructure.adapter.out.persistence.jpa.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueJpaRepository extends JpaRepository<VenueEntity, Long> {
}
