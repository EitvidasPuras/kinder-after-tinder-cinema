package com.kindercinema.kinderaftertindercinema.repository;

import com.kindercinema.kinderaftertindercinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select m from Movie m join m.sessions s where s.startDate >= ?1 and s.startDate <= ?2 group by m.id")
    List<Movie> getMoviesByDay(Date currentDay, Date nextDay);
}