package vu.lt.persistence;

import vu.lt.entities.Interview;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@Specializes
public class InterviewsDAO extends IInterviewsDAO{

    @Inject
    private EntityManager em;

    public void persist(Interview interview){
        this.em.persist(interview);
    }
}
