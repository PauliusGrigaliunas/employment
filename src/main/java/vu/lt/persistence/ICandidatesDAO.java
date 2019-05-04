package vu.lt.persistence;

import vu.lt.entities.Candidate;

import javax.persistence.EntityManager;
import java.util.List;

public interface ICandidatesDAO {

    void persist(Candidate candidate);
    void updateAndFlush(Candidate candidate);
    List<Candidate> loadAll();
    Candidate loadOne(int candidateId);
    void setEm(EntityManager em);
    void delete(Candidate candidate);
    Candidate findOne(Integer id);
    void merge(Candidate candidate);

}

