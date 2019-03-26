package vu.lt.persistence;

import vu.lt.entities.Interview;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class InterviewsDAO {

    @Inject
    private EntityManager em;

    public void persist(Interview interview){
        this.em.persist(interview);
    }
}
