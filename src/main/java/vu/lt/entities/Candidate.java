package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Candidate.findAll", query = "select t from Candidate as t")
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


    @ManyToMany
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
}
