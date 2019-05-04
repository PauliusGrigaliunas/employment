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
@Getter @Setter
@XmlRootElement
@Alternative
public class Worker implements ICandidate {

    public Worker(){ }

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
        Worker worker = (Worker) o;
        return Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }



    public void addPosition(Position position) {

    }

    public void removePosition(Position position) {

    }
}
