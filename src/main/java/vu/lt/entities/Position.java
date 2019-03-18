package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Position.findAll", query = "select t from Position as t")
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

    private String name;

    /*@OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    private List<Interview> interviews = new ArrayList<>();*/

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
}