package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Candidate.findAll", query = "select c from Candidate as c"),
        @NamedQuery(name = "Candidate.findById", query = "select p from Position as p where p.id = :candidateId")
})
@Table(name = "CANDIDATE")
@Getter
@Setter
@XmlRootElement
@Alternative
public abstract class AbsCandidate {

    public AbsCandidate(){ }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Named
    @Column(name = "NAME", nullable = false)
    private String name;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @ManyToMany
    @JoinTable(name="CANDIDATE_POSITION")
    private List<Position> positionsList = new ArrayList<>();

    @OneToMany(mappedBy="candidate", cascade = CascadeType.ALL)
    private List<Interview> interviews = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsCandidate absCandidate = (AbsCandidate) o;
        return Objects.equals(name, absCandidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    public void addPosition(Position position) {
        positionsList.add(position);
        position.getCandidatesList().add(this);
    }

    public void removePosition(Position position) {
        positionsList.add(position);
        position.getCandidatesList().add(this);
    }
}
