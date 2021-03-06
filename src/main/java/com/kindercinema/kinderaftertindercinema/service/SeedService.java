package com.kindercinema.kinderaftertindercinema.service;

import com.kindercinema.kinderaftertindercinema.entity.*;
import com.kindercinema.kinderaftertindercinema.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SeedService {

    private final UserRepository userRepository;
    private final KinderUserRepository kinderUserRepository;
    private final MovieRepository movieRepository;
    private final SessionRepository sessionRepository;
    private final HallRepository hallRepository;
    private final RowRepository rowRepository;
    private final SeatRepository seatRepository;
    private final GenreRepository genreRepository;
    private final TicketRepository ticketRepository;
    private final Random random;

    public SeedService(UserRepository userRepository, KinderUserRepository kinderUserRepository, MovieRepository movieRepository, SessionRepository sessionRepository, HallRepository hallRepository, RowRepository rowRepository, SeatRepository seatRepository, GenreRepository genreRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.kinderUserRepository = kinderUserRepository;
        this.movieRepository = movieRepository;
        this.sessionRepository = sessionRepository;
        this.hallRepository = hallRepository;
        this.rowRepository = rowRepository;
        this.seatRepository = seatRepository;
        this.genreRepository = genreRepository;
        this.ticketRepository = ticketRepository;
        this.random = new Random();
    }

    public void seed() {
        if (needsSeeding()) {
            addGenders();
            addUsers();
            addKinderUsers();
            addHalls();
            addMovies();
            addTickets();
        }
    }

    private boolean needsSeeding() {
        return userRepository.count() < 10;
    }

    private void addUsers() {
        User manager = new User();
        manager.setEmail("m");
        manager.setFirstName("Manager");
        manager.setLastName("Manager");
        manager.setPassword("m");
        manager.setRole(User.ROLE_MANAGER);
        userRepository.save(manager);

        User client = new User();
        client.setEmail("c");
        client.setFirstName("Client");
        client.setLastName("Client");
        client.setPassword("c");
        client.setRole(User.ROLE_CLIENT);
        userRepository.save(client);

        User cashier = new User();
        cashier.setEmail("ca");
        cashier.setFirstName("Cashier");
        cashier.setLastName("Cashier");
        cashier.setPassword("ca");
        cashier.setRole(User.ROLE_CASHIER);
        userRepository.save(cashier);
        userRepository.flush();
    }

    private void addKinderUsers() {
        List<Genre> genres = genreRepository.findAll();
        for (int i = 1; i <= 50; i++) {
            User user = new User();
            user.setEmail("kinder" + i);
            user.setFirstName("Kinder");
            user.setLastName("" + i);
            user.setPassword("kinder" + i);
            user.setRole(User.ROLE_CLIENT);
            userRepository.save(user);

            KinderUser kinderUser = new KinderUser();
            kinderUser.setGenre(genres.get(random.nextInt(genres.size())));
            kinderUser.setGender((byte) random.nextInt(3));
            kinderUser.setInterestedIn((byte) random.nextInt(3));
            kinderUser.setPhoneNumber("+" + random.nextInt(Integer.MAX_VALUE));
            kinderUser.setUser(user);
            kinderUser.setDescription("");
            kinderUser.setInterestedAge(random.nextInt(7) + 18);
            kinderUser.setAge(random.nextInt(7) + 18);
            kinderUser.setInterestedGenre(genres.get(random.nextInt(genres.size())));
            kinderUserRepository.save(kinderUser);
        }
        kinderUserRepository.flush();
    }

    private void addHalls() {
        for (int i = 1; i <= 5; i++) {
            Hall hall = new Hall();
            hall.setName("Hall" + i);
            hallRepository.saveAndFlush(hall);
            hall.setRows(generateRowsInHall(hall));
        }
        hallRepository.flush();
    }

    private List<Row> generateRowsInHall(Hall hall) {
        List<Row> rows = new ArrayList<>();
        int randomInt = random.nextInt(20) + 5;
        for (int i = 1; i <= randomInt; i++) {
            Row row = new Row();
            row.setNumber(i);
            row.setHall(hall);
            rowRepository.saveAndFlush(row);
            row.setSeats(generateSeatsInRow(row));
            rows.add(row);
        }
        rowRepository.flush();
        return rows;
    }

    private List<Seat> generateSeatsInRow(Row row) {
        List<Seat> seats = new ArrayList<>();
        int randomInt = random.nextInt(30) + 5;
        for (int i = 1; i <= randomInt; i++) {
            Seat seat = new Seat();
            seat.setNumber(i);
            seat.setRow(row);
            seatRepository.save(seat);
            seats.add(seat);
        }
        seatRepository.flush();
        return seats;
    }

    private void addMovies() {
        for (int i = 1; i <= 5; i++) {
            Movie movie = new Movie();
            movie.set3D(random.nextBoolean());
            movie.setDescription("");
            movie.setName("Movie" + i);
            movieRepository.saveAndFlush(movie);
            movie.setSessions(generateMovieSessions(movie));
        }
        movieRepository.flush();
    }

    private List<Session> generateMovieSessions(Movie movie) {
        List<Session> sessions = new ArrayList<>();
        List<Hall> halls = hallRepository.findAll();
        int randomInt = random.nextInt(10) + 20;
        for (int i = 0; i < randomInt; i++) {
            Session session = new Session();
            session.setStartDate(new Date(random.nextInt(Integer.MAX_VALUE) * 1000L));
            session.setEndDate(new Date(random.nextInt(Integer.MAX_VALUE) * 1000L));
            session.setMovie(movie);
            session.setHall(halls.get(random.nextInt(halls.size())));
            session.setPrice(random.nextDouble() * 10);
            sessionRepository.save(session);
            sessions.add(session);
        }
        sessionRepository.flush();
        return sessions;
    }

    private void addGenders() {
        Genre genre1 = new Genre();
        genre1.setName("Horror");
        genreRepository.save(genre1);

        Genre genre2 = new Genre();
        genre2.setName("Comedy");
        genreRepository.save(genre2);

        Genre genre3 = new Genre();
        genre3.setName("Action");
        genreRepository.save(genre3);

        Genre genre4 = new Genre();
        genre4.setName("Thriller");
        genreRepository.save(genre4);

        Genre genre5 = new Genre();
        genre5.setName("Romantic");
        genreRepository.save(genre5);

        Genre genre6 = new Genre();
        genre6.setName("Drama");
        genreRepository.save(genre6);

        Genre genre7 = new Genre();
        genre7.setName("Fantastic");
        genreRepository.save(genre7);
        genreRepository.flush();
    }

    private void addTickets() {
        List<Seat> seats = seatRepository.findAll();
        List<Session> sessions = sessionRepository.findAll();
        for (int i = 1; i <= 20; i++) {
            Ticket ticket = new Ticket();
            ticket.setCanceled(random.nextBoolean());
            ticket.setPayDate(new Date(random.nextInt(Integer.MAX_VALUE) * 1000L));
            ticket.setReserved(random.nextBoolean());
            ticket.setSeat(seats.get(random.nextInt(seats.size())));
            ticket.setSession(sessions.get(random.nextInt(sessions.size())));
            ticketRepository.save(ticket);
        }
        ticketRepository.flush();
    }
}
