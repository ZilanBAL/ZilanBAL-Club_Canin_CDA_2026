package edu.ban7.club_canin_cda_2026.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.dao.RaceDao;
import edu.ban7.club_canin_cda_2026.model.Race;
import edu.ban7.club_canin_cda_2026.view.ChienView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/race")
@CrossOrigin
public class RaceController {

    private final RaceDao raceDao;

    @GetMapping("/list")
    @JsonView(ChienView.class)
    public List<Race> getAll() {
        return raceDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ChienView.class)
    public ResponseEntity<Race> get(@PathVariable int id) {
        Optional<Race> optional = raceDao.findById(id);
        if (optional.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }
    @PostMapping
    @JsonView(ChienView.class)
    public ResponseEntity<Race> create(@RequestBody @Valid Race race) {
        race.setId(null);
        raceDao.save(race);
        return new ResponseEntity<>(race, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody @Valid Race race) {
        if (raceDao.findById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        race.setId(id);
        raceDao.save(race);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (raceDao.findById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        raceDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}