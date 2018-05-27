package com.kindercinema.kinderaftertindercinema.controller.client;

import com.kindercinema.kinderaftertindercinema.entity.Movie;
import com.kindercinema.kinderaftertindercinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String openMoviesPage() {
        return "client/moviesPage";
    }

    @GetMapping("/{date}")
    @ResponseBody
    public List<Movie> getMoviesByDay(@PathVariable String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = format.parse(date);
        Date nextDate = getNextDay(currentDate);
        return movieRepository.getMoviesByDay(currentDate, nextDate);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    private Date getNextDay(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
}
