package com.project.appglee.AppGLEE_DB.repository;

import com.project.appglee.AppGLEE_DB.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
    @Query(value = "SELECT E FROM Event E WHERE E.recomended IS 1")
    Iterable<Event> listEventsByRecomended();

    @Query(value = "SELECT E FROM Event E WHERE E.category.id=:idC")
    Iterable<Event> listEventByCategory(int idC);

}
