package vu.lt.persistence;

import vu.lt.entities.Interview;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ApplicationScoped
public class InterviewsExtendDAO extends IInterviewsDAO {

    @Inject
    private EntityManager em;

    @Override
    public void persist(Interview interview) {

        interview.setName(interview.getName() + " interview");
        this.em.persist(interview);
    }
}
