package pi.vortex.rescuethestray.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AnimalProfiles")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnimalProfile {

    public enum AnimalSize{
        BIG,MEDIUM,SMALL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_animal;


    String name_animal;

    @Enumerated(EnumType.STRING)
    TypeAnimal type_animal;

    Integer age_animal;

    String breed_animal;

    String gender_animal;

    @Enumerated(EnumType.STRING)
    AnimalSize size_animal;

    Integer weight_animal;

    String color_animal;

    String temperament_animal;

    Boolean isVaccinated;

    Boolean isTrained;

    Boolean medicalNeeds_animal;

    String medicalNeeds_description;

    Boolean behavioralNeeds_animal;

    String behavioralNeeds_description;

    String history_animal;
    @Column(length = 70000)
    String photoUrl_animal;
    /*
    @OneToOne(mappedBy = "animalProfile", cascade = CascadeType.ALL)
    @JsonIgnore
    AdoptionPost adoptionPost;
    */
    @ManyToOne
    @JsonIgnore
    User user;

}

