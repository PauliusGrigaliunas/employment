package vu.lt.persistence;

import vu.lt.entities.Candidate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

//@Alternative
@ApplicationScoped
public class EmployeesDAO implements ICandidatesDAO {

    @Inject
    private EntityManager em;

    public void persist(Candidate candidate) {
        String name = candidate.getName();
        candidate.setName(name + " employee");
        this.em.persist(candidate);
    }

    public void updateAndFlush(Candidate candidate) {
        String name = candidate.getName();
        candidate.setName(name + " employee");
        em.merge(candidate);
        em.flush();
    }

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

    public void delete(Candidate candidate) {
        this.em.remove(em.contains(candidate) ? candidate : this.em.merge(candidate));
    }

    public Candidate findOne(Integer id) {
        return em.find(Candidate.class, id);
    }

    public void merge(Candidate candidate) {
        this.em.merge(candidate);
    }
}