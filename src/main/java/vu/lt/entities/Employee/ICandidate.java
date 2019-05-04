package vu.lt.entities.Employee;

import vu.lt.entities.Interview;
import vu.lt.entities.Position;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "ICandidate.findAll", query = "select c from Candidate as c"),
        @NamedQuery(name = "ICandidate.findById", query = "select p from Position as p where p.id = :candidateId")
})
@Table(name = "CANDIDATE")
public interface ICandidate {

    Integer getId();
    void setId(Integer id);
    String getName();
    void setName(String name);
    Integer getOptLockVersion();
    void setOptLockVersion(Integer optLockVersion);
    List<Position> getPositionsList();
    void setPositionsList(List<Position> positionsList);
    List<Interview> getInterviews();
    void setInterviews(List Interviews);
    void addPosition(Position position);
    void removePosition(Position position);
}
