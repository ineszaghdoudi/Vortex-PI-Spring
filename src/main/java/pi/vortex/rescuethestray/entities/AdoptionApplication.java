package pi.vortex.rescuethestray.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    public enum HousingType{
        COUNTRYHOUSE,HOUSE,APPARTEMENT,OTHER
    }

    public enum HouseholdSize{
        VERYBIG,BIG,MEDIUM,SMALL
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_adoptionapp;

    String applicantName;

    String applicantEmail;

    String applicantPhone;

    String applicantAddress;

    String applicantOccupation;

    @Enumerated(EnumType.STRING)
    HousingType applicantHousingType;

    @Enumerated(EnumType.STRING)
    HouseholdSize applicantHouseholdSize;

    Boolean hasExperience;

    String applicantExperience;

    Boolean otherPets;

    String otherPetsDescription;

    String vetName;

    String vetPhone;

    String referenceName;

    String referencePhone;

    String referenceRelationship;

    String motive;

    @Enumerated(EnumType.STRING)
    ApplicationStatus status_adoptionapp= ApplicationStatus.PENDING;

    @CreationTimestamp
    LocalDate createdDate_adoptionapp;


    @ManyToOne
    AdoptionPost adoptionPost;

    @ManyToOne
    @JsonIgnore
    User user;
}
