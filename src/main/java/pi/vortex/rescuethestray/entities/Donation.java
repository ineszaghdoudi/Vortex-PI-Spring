package pi.vortex.rescuethestray.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Donation implements Serializable {
    @Id
    @GeneratedValue
    long id_donation;
    long amount_donation;
    LocalDate date_donation;
    String desc_donation;

    @ManyToOne
    Compaign compaign;
}
