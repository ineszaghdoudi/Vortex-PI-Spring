package pi.vortex.rescuethestray.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compaign")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Compaign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    long id_compaign;

    String title_compaign;
    String desc_compaign;
    double target_amount_compaign;
    LocalDate end_date_compaign;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "compaign")
    List<Donation> LDonations;


}
