package pi.vortex.rescuethestray.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
    @GeneratedValue
    long id_compaign;
    String title_compaign;
    String desc_compaign;
    long target_amount_compaign;
    LocalDate end_date_compaign;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "compaign")
    List<Donation> LDonations;

}
