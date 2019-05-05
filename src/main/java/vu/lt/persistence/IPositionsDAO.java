package vu.lt.persistence;

import vu.lt.entities.Candidate;
import vu.lt.entities.Position;

import javax.persistence.EntityManager;
import java.util.List;

public interface IPositionsDAO {

    List<Position> loadAll();
    Position loadOne(int positionId);
    void setEm(EntityManager em);
    void persist(Position position);
    Position findOne(Integer id);
    List<Candidate> getQualifiedCandidates(int positionId) ;
    void merge(Position position);

}
