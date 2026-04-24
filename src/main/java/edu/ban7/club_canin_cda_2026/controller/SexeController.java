package edu.ban7.club_canin_cda_2026.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.dao.SexeDao;
import edu.ban7.club_canin_cda_2026.model.Sexe;
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
@RequestMapping("/sexe")
@CrossOrigin
public class SexeController {

    private final SexeDao sexeDao;

    @GetMapping("/list")
    @JsonView(ChienView.class)
    public List<Sexe> getAll() {
        return sexeDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ChienView.class)
    public ResponseEntity<Sexe> get(@PathVariable int id) {
        Optional<Sexe> optional = sexeDao.findById(id);
        if (optional.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }

    @PostMapping
    @JsonView(ChienView.class)
    public ResponseEntity<Sexe> create(@RequestBody @Valid Sexe sexe) {
        sexe.setId(null);
        sexeDao.save(sexe);
        return new ResponseEntity<>(sexe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody @Valid Sexe sexe) {
        if (sexeDao.findById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        sexe.setId(id);
        sexeDao.save(sexe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (sexeDao.findById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        sexeDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}