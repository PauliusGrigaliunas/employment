package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Interview;
import vu.lt.entities.Candidate;
import vu.lt.persistence.InterviewsDAO;
import vu.lt.persistence.CandidatesDAO;

@Model
public class InterviewsForCandidate implements Serializable {

    @Inject
    private CandidatesDAO candidatesDAO;

    @Inject
    private InterviewsDAO interviewDAO;

    @Getter @Setter
    private Candidate candidate;

    @Getter @Setter
    private Interview interviewToCreate = new Interview();

    @Getter
    private List<Interview> allInterview;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer candidateId = Integer.parseInt(requestParameters.get("candidateId"));
        this.candidate = candidatesDAO.findOne(candidateId);
    }

    @Transactional
    public String createInterview() {
        interviewToCreate.setCandidate(this.candidate);
        interviewDAO.persist(interviewToCreate);
        return "interviews?faces-redirect=true&candidateId=" + this.candidate.getId();
    }

}
