package pi.vortex.rescuethestray.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToOne
    AnimalProfile animalProfile;

    @OneToMany(mappedBy = "adoptionPost", cascade = CascadeType.ALL)
    List<AdoptionApplication> adoptionApplications = new ArrayList<>();

    String description_adoptionpost;

    @ElementCollection
    List<String> photoUrls_adoptionpost;

    @Enumerated(EnumType.STRING)
    AdoptionStatus status_adoptionpost;

    @CreationTimestamp
    LocalDateTime createdDate_adoptionpost;

    @UpdateTimestamp
    LocalDateTime modifiedDate_adoptionpost;

}
