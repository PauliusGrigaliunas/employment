package vu.lt.persistence;

import vu.lt.entities.AbsCandidate;

import javax.persistence.EntityManager;
import java.util.List;

public interface ICandidatesDAO {

    void persist(AbsCandidate candidate);
    void updateAndFlush(AbsCandidate candidate);
    List<AbsCandidate> loadAll();
    AbsCandidate loadOne(int candidateId);
    void setEm(EntityManager em);
    void delete(AbsCandidate candidate);
    AbsCandidate findOne(Integer id);
    void merge(AbsCandidate candidate);

}

