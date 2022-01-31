package movietheatres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class MovieTheatreService {

    private Movie movie = new Movie("",LocalTime.now());
    private Map<String, List<Movie>> shows = new HashMap<>();

    public Map<String, List<Movie>> getShows() {
        return shows;
    }

    public Movie getMovie() {
        return movie;
    }

    public void readFromFile(Path path) {

        try {
            List<String> movies = Files.readAllLines(path);
            for(int i = 0; i < movies.size(); i++){
                String cinema = movies.get(i).split("-")[0];
                String moviedatas = movies.get(i).split("-")[1];

                String title = moviedatas.split(";")[0];
                movie.setTitle(title);
                String time = moviedatas.split(";")[1];
                int timeHour = Integer.parseInt(time.split(":")[0]);
                int timeMinutes = Integer.parseInt(time.split(":")[1]);
                movie.setStartTime(LocalTime.of(timeHour,timeMinutes));

                if(shows.containsKey(cinema)){
                    List<Movie> cinemaname = shows.get(cinema);
                    cinemaname.add(movie);
                    //List<Movie> movieLis = cinemaname.stream().sorted(Comparator.comparing(Movie::getStartTime)).toList();
                    shows.put(cinema,cinemaname);

                }
                if(!shows.containsKey(cinema)){
                    List<Movie> cinemaname = new ArrayList<>();
                    cinemaname.add(movie);
                    shows.put(cinema,cinemaname);
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public List<String> findMovie(String title){
        List<String> movies = new ArrayList<>();
        for(Map.Entry<String,List<Movie>> entries: shows.entrySet()){
            if((entries.getValue().contains(title))) {
                movies.add(entries.getKey());
            }
        }
        return movies;
    }

    public LocalTime findLatestShow(String title){
        List<Movie> moviesLatest = new ArrayList<>();
        for(Map.Entry<String,List<Movie>> entries: shows.entrySet()){
            if((entries.getValue().contains(title))){
               moviesLatest.add(entries.getValue().stream().map(p->p.getTitle()).filter(s->s.equals(title)).findFirst().orElse());
            } else {
                findLatestShowWithWrongName();
            }
        }
        return null;
    }

    public void findLatestShowWithWrongName(){
        throw  new IllegalArgumentException("Can't find title!");
    }
}
