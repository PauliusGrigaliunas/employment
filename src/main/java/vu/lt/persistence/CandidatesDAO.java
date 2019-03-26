package vu.lt.persistence;

import vu.lt.entities.Candidate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CandidatesDAO {

    @Inject
    private EntityManager em;

    public List<Candidate> loadAll() {
        return em.createNamedQuery("Candidate.findAll", Candidate.class).getResultList();
    }
    public Candidate loadOne(int candidateId) {
        return em.createNamedQuery("Candidate.findById", Candidate.class)
                .setParameter("candidateId", candidateId)
                .getSingleResult();
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

    public void merge(Candidate candidate) {
        this.em.merge(candidate);
    }
}
