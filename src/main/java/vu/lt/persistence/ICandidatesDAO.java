package vu.lt.persistence;

import vu.lt.entities.ICandidate;

import javax.persistence.EntityManager;
import java.util.List;

public interface ICandidatesDAO {

    void persist(ICandidate candidate);
    void updateAndFlush(ICandidate candidate);
    List<ICandidate> loadAll();
    ICandidate loadOne(int candidateId);
    void setEm(EntityManager em);
    void delete(ICandidate candidate);
    ICandidate findOne(Integer id);
    void merge(ICandidate candidate);

}

