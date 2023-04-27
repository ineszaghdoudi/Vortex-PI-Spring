package pi.vortex.rescuethestray.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long PostId ;

    String Content;

    LocalDate date_post;


    @OneToMany(mappedBy = "post")
    List<Reaction>reactionList;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comment> comments;
}
