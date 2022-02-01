package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SongService {

    private List<Song> songs = new ArrayList<>();

    public void addSong (Song song) {
        songs.add(song);
    }

    public Optional<Song> shortestSong() {
        return songs.stream().min(Comparator.comparing(Song::getLength));
    }

    public List<Song> findSongByTitle(String title) {
        return songs.stream().filter(s -> s.getTitle().equals(title)).collect(Collectors.toList());
    }

    public boolean isPerformerInSong(Song song, String performer) {
        return song.getPerformers().stream().anyMatch(f -> f.equals(performer));
    }

    public List<String> titlesBeforeDate (LocalDate date) {
        return songs.stream().filter(s -> s.getRelease().isBefore(date)).map(p -> p.getTitle()).collect(Collectors.toList());
    }

    public List<Song> getSongs() {
        return songs;
    }
}
