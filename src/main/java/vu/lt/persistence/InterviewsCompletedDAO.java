package vu.lt.persistence;

import vu.lt.entities.Interview;
import vu.lt.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
@Specializes
public class InterviewsCompletedDAO extends InterviewsDAO {

    @Override
    @LoggedInvocation
    public void persist(Interview interview){
        String name = interview.getName();
        interview.setName(name + " (completed)");
        this.em.persist(interview);
    }

}