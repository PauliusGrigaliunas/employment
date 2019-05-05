package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Inteview.findAll", query = "select a from Interview as a")
})
@Table(name = "INTERVIEW")
@Getter @Setter
public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @ManyToOne
    @JoinColumn(name="CANDIDATE_ID")
    private ICandidate candidate;

    public Interview() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview interview = (Interview) o;
        return Objects.equals(id, interview.id) &&
                Objects.equals(name, interview.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
