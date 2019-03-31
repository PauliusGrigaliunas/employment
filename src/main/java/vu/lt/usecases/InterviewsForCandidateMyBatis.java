package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.myBatis.dao.CandidateMapper;
import vu.lt.myBatis.dao.InterviewMapper;
import vu.lt.myBatis.model.Candidate;
import vu.lt.myBatis.model.Interview;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class InterviewsForCandidateMyBatis {

    @Inject
    private InterviewMapper interviewMapper;
    @Inject
    private CandidateMapper candidateMapper;

    @Getter
    private List<Interview> allInterviews;

    @Getter @Setter
    private Interview interviewToCreate = new Interview();

    @PostConstruct
    public void init() {
        this.loadAllInterview();
    }

    private void loadAllInterview() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer candidateId = Integer.parseInt(requestParameters.get("candidateId"));

        this.allInterviews = interviewMapper.getInterviewByCandidateID(candidateId);
    }

    @Transactional
    public String createInterview() {
        interviewMapper.insert(interviewToCreate);
        return "/myBatis/interviews?faces-redirect=true";
    }
}