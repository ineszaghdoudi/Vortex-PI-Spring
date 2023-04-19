package pi.vortex.rescuethestray.entities;

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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_animal;

    @OneToOne(mappedBy = "animalProfile")
    private AdoptionPost adoptionPost;

    String name_animal;

    @Enumerated(EnumType.STRING)
    TypeAnimal type_animal;

    Integer age_animal;

    String breed_animal;

    String gender_animal;

    String size_animal;

    Integer weight_animal;

    String color_animal;

    String temperament_animal;

    String medicalNeeds_animal;

    String behavioralNeeds_animal;

    String history_animal;

    String photoUrl_animal;

}

