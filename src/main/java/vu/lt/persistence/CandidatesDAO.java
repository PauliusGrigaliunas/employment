package vu.lt.persistence;

import vu.lt.entities.AbsCandidate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
@ApplicationScoped
public class CandidatesDAO implements ICandidatesDAO {

    @Inject
    private EntityManager em;

    public void persist(AbsCandidate candidate){
        this.em.persist(candidate);
    }
    public void updateAndFlush(AbsCandidate candidate) {
        em.merge(candidate);
        em.flush();
    }

    public List<AbsCandidate> loadAll() {
        return em.createNamedQuery("Candidate.findAll", AbsCandidate.class).getResultList();
    }
    public AbsCandidate loadOne(int candidateId) {
        return em.createNamedQuery("Candidate.findById", AbsCandidate.class)
                .setParameter("candidateId", candidateId)
                .getSingleResult();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void delete(AbsCandidate candidate){ this.em.remove(em.contains(candidate) ? candidate : this.em.merge(candidate)); }

    public AbsCandidate findOne(Integer id) {
        return em.find(AbsCandidate.class, id);
    }

    public void merge(AbsCandidate candidate) {
        this.em.merge(candidate);
    }
}
