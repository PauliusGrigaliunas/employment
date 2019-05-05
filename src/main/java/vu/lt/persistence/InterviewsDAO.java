package vu.lt.persistence;

import vu.lt.entities.Interview;
import vu.lt.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class InterviewsDAO {

    @Inject
    private EntityManager em;

    @LoggedInvocation
    public void persist(Interview interview){
        this.em.persist(interview);
    }
}
