package vu.lt.persistence;

import vu.lt.entities.Candidate;
import vu.lt.entities.Position;
import vu.lt.usecases.Candidates;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class PositionsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Position> loadAll() {
        return em.createNamedQuery("Position.findAll", Position.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Position position){
        this.em.persist(position);
    }

    public Position findOne(Integer id) {
        return em.find(Position.class, id);
    }

     List<Candidate> getQualifiedCandidates(int positionId) {
        return em.createNamedQuery("Candidate.findById", Candidate.class)
                .setParameter("candidateId", positionId)
                .getResultList();
    }
}