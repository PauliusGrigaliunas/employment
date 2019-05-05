package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.ViewScoped;
import vu.lt.entities.AbsCandidate;
import vu.lt.entities.Interview;
import vu.lt.persistence.ICandidatesDAO;
import vu.lt.persistence.InterviewsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named
@ViewScoped
public class InterviewsForCandidate implements Serializable {

    @Inject
    private ICandidatesDAO candidatesDAO;

    @Inject
    private InterviewsDAO interviewDAO;

    @Getter @Setter
    private AbsCandidate candidate;

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
