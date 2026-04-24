package edu.ban7.club_canin_cda_2026.controller;

import edu.ban7.club_canin_cda_2026.dao.RoleDao;
import edu.ban7.club_canin_cda_2026.model.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    private final RoleDao roleDao;

    @GetMapping("/list")
    public List<Role> getAll() {
        return roleDao.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Role> get(@PathVariable int id) {

        Optional<Role> optionalRole = roleDao.findById(id);

        if (optionalRole.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> create(
            @RequestBody
            @Valid
            Role roleToInsert) {

        roleToInsert.setId(null);
        roleDao.save(roleToInsert);

        return new ResponseEntity<>(roleToInsert, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Role roleToUpdate) {

        Optional<Role> optionalRole = roleDao.findById(id);

        if (optionalRole.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        roleToUpdate.setId(id);
        roleDao.save(roleToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Role> optionalRole = roleDao.findById(id);

        if (optionalRole.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        roleDao.deleteById(id);

       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}