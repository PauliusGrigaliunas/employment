package vu.lt.entities.Employee;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Interview;
import vu.lt.entities.Position;

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
@Getter @Setter
@XmlRootElement
@Alternative
public class Candidate implements IWorker {

    public Candidate(){ }

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
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void setInterviews(List Interviews) {

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
