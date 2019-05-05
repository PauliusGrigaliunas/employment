package vu.lt.persistence;

import vu.lt.entities.Candidate;
import vu.lt.entities.Position;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PositionsDAO implements IPositionsDAO {

    @Inject
    private EntityManager em;

    public List<Position> loadAll() {
        return em.createNamedQuery("Position.findAll", Position.class).getResultList();
    }

    public Position loadOne(int positionId) {
        return em.createNamedQuery("Position.findById", Position.class)
                .setParameter("positionId", positionId)
                .getSingleResult();
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

    public List<Candidate> getQualifiedCandidates(int positionId) {
        return em.createNamedQuery("Candidate.findById", Candidate.class)
                .setParameter("candidateId", positionId)
                .getResultList();
    }

    public void merge(Position position) {
        this.em.merge(position);
    }
}