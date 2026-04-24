package edu.ban7.club_canin_cda_2026.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.dao.TypeCoursDao;
import edu.ban7.club_canin_cda_2026.model.TypeCours;
import edu.ban7.club_canin_cda_2026.view.SeanceView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/typecours")
@CrossOrigin
public class TypeCoursController {

    private final TypeCoursDao typeCoursDao;

    // GET /typecours/list
    @GetMapping("/list")
    @JsonView(SeanceView.class)
    public List<TypeCours> getAll() {
        return typeCoursDao.findAll();
    }

    // GET /typecours/{id}
    @GetMapping("/{id}")
    @JsonView(SeanceView.class)
    public ResponseEntity<TypeCours> get(@PathVariable int id) {
        Optional<TypeCours> optional = typeCoursDao.findById(id);
        if (optional.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }

    // POST /typecours
    @PostMapping
    @JsonView(SeanceView.class)
    public ResponseEntity<TypeCours> create(@RequestBody @Valid TypeCours typeCours) {
        typeCours.setId(null);
        typeCoursDao.save(typeCours);
        return new ResponseEntity<>(typeCours, HttpStatus.CREATED);
    }

    // PUT /typecours/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody @Valid TypeCours typeCours) {
        if (typeCoursDao.findById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        typeCours.setId(id);
        typeCoursDao.save(typeCours);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE /typecours/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (typeCoursDao.findById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        typeCoursDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}