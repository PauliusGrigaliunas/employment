package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
public class Candidate {

    public Candidate(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
    private List<Interview> interviews = new ArrayList<>();

    @Getter
    @ManyToMany
    @JoinTable(name="CANDIDATE_POSITION")
    private List<Position> positionsList = new ArrayList<>();

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

    public void addPosition(Position position) {
        positionsList.add(position);
        position.getCandidatesList().add(this);
    }

    public void removePosition(Position position) {
        positionsList.add(position);
        position.getCandidatesList().add(this);
    }
}
