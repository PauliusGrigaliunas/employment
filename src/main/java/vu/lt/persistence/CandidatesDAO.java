package vu.lt.persistence;

import vu.lt.entities.Candidate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CandidatesDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Candidate> loadAll() {
        return em.createNamedQuery("Candidate.findAll", Candidate.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Candidate candidate){
        this.em.persist(candidate);
    }

    public Candidate findOne(Integer id) {
        return em.find(Candidate.class, id);
    }
}
