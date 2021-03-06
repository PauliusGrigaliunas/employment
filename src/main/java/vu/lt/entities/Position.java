package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Position.findAll", query = "select t from Position as t"),
        @NamedQuery(name = "Position.findById", query = "select t from Position as t where t.id = :positionId")
})
@Table(name = "POSITION")
@Getter
@Setter
public class Position {

    public Position(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @ManyToMany(mappedBy = "positionsList")
    private List<ICandidate> candidatesList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public void addCandidate(ICandidate candidate) {
        candidatesList.add(candidate);
        candidate.getPositionsList().add(this);
    }

    public void removeCandidate(ICandidate candidate) {
        candidatesList.remove(candidate);
        candidate.getPositionsList().remove(this);
    }
}