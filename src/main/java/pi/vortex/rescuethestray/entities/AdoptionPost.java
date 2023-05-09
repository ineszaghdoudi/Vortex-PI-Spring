package pi.vortex.rescuethestray.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AdoptionPosts")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdoptionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_adoptionpost;

    String description_adoptionpost;
    @Column(length = 70000)
    String photoUrl_adoptionpost;

    @Enumerated(EnumType.STRING)
    AdoptionStatus status_adoptionpost= AdoptionStatus.AVAILABLE;

    @CreationTimestamp
    LocalDate creationdate_adoptionpost;

    Long adopter_id;

    LocalDate adoptionDate;


    @OneToOne
    AnimalProfile animalProfile;

}
