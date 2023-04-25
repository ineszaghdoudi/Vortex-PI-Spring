package pi.vortex.rescuethestray.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AdoptionApplications")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdoptionApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_adoptionapp;



    /*
    String applicantName;


    String applicantEmail;


    String applicantPhone;


    String applicantAddress;


     */

    String applicantOccupation;


    String applicantHousingType;


    Integer applicantHouseholdSize;


    String applicantExperience;

    Boolean otherPets;

    String otherPetsDescription;

    String vetName;

    String vetPhone;

    String referenceName;

    String referencePhone;

    String referenceRelationship;

    @Enumerated(EnumType.STRING)
    ApplicationStatus status_adoptionapp;

    @CreationTimestamp
    LocalDateTime createdDate_adoptionapp;

    @UpdateTimestamp
    LocalDateTime modifiedDate_adoptionapp;

    @ManyToOne
    AdoptionPost adoptionPost;
}
