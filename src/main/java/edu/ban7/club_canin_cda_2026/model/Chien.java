package edu.ban7.club_canin_cda_2026.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.view.AppPersonneView;
import edu.ban7.club_canin_cda_2026.view.ChienView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Chien {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(ChienView.class)
    protected Integer id;

    @Column(length = 50, nullable = false)
    @NotBlank
    @Length(min=3, max=50)
    @JsonView({ChienView.class, AppPersonneView.class})
    protected String nom;

    //@Column(nullable = false)
    //@NotNull(message = "La date de naissance est obligatoire")
    @JsonView(ChienView.class)
    protected LocalDate dateNaissance;

    @Column(unique = true)
    @JsonView({ChienView.class, AppPersonneView.class})
    protected String matricule;

    @JsonView(ChienView.class)
    protected Double taille;

    @JsonView(ChienView.class)
    protected Double poids;

//Tant que false : le chien peut uniquement s'inscrire aux séances d'inscription.
    @JsonView(ChienView.class)
    private boolean seanceObligatoireFaite = false;

    // Sexe du chien (Mâle / Femelle). Obligatoire.
    @ManyToOne
    @NotNull(message = "Le sexe est obligatoire")
    @JsonView(ChienView.class)
    private Sexe sexe;

    // Race du chien. Obligatoire.
    @ManyToOne
    @NotNull(message = "La race est obligatoire")
    @JsonView(ChienView.class)
    private Race race;

    // plusieurs chiens peuvent appartenir au même propriétaire,mais un chien n'a qu'un seul propriétaire.
    @ManyToOne
    @NotNull(message = "Le propriétaire est obligatoire")
    @JsonView(ChienView.class)
    private AppPersonne proprietaire;

    //Un coach peut en ajouter à tout moment lors d'une séance.

    @ManyToMany
    @JoinTable(
            name = "chien_competence",
            joinColumns = @JoinColumn(name = "chien_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    @JsonView(ChienView.class)
    private List<Competence> competences = new ArrayList<>();
}

