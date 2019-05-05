package vu.lt.persistence;

import vu.lt.entities.Candidate;
import vu.lt.entities.ICandidate;

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

    public void persist(ICandidate candidate){
        this.em.persist(candidate);
    }
    public void updateAndFlush(ICandidate candidate) {
        em.merge(candidate);
        em.flush();
    }

    public List<ICandidate> loadAll() {
        return em.createNamedQuery("Candidate.findAll", ICandidate.class).getResultList();
    }
    public ICandidate loadOne(int candidateId) {
        return em.createNamedQuery("Candidate.findById", ICandidate.class)
                .setParameter("candidateId", candidateId)
                .getSingleResult();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void delete(ICandidate candidate){ this.em.remove(em.contains(candidate) ? candidate : this.em.merge(candidate)); }

    public ICandidate findOne(Integer id) {
        return em.find(Candidate.class, id);
    }

    public void merge(ICandidate candidate) {
        this.em.merge(candidate);
    }
}
