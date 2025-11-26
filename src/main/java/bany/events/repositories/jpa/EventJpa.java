package bany.events.repositories.jpa;

import bany.events.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventJpa extends JpaRepository<Event,Long> {

    @Query("SELECT e FROM Event e " +
            "WHERE (:name IS NULL OR e.name LIKE %:name%) " +
            "AND (:location IS NULL OR e.venue.location LIKE %:location%) " +
            "AND (:date IS NULL OR e.date = :date)"
    )
    Page<Event> findByFilters(
            @Param("name") String name,
            @Param("location") String location,
            @Param("date") String date,
            Pageable pageable);

}
