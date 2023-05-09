package pi.vortex.rescuethestray.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "learningResource")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LearningResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_learningr;

    private String title_learningr;

    private String desc_learningr;

    @Enumerated(EnumType.STRING)
    private TypeResource type_learningr;

    private String url_learningr;

    @Enumerated(EnumType.STRING)
    private TypeLRInterest theme;

    @CreationTimestamp
    private LocalDate creationdate_learningr;



}
