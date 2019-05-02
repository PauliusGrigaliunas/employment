package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import vu.lt.entities.Candidate;
import vu.lt.persistence.CandidatesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class EditCandidates implements Serializable {

    @Inject
    private CandidatesDAO candidatesDAO;

    @Getter
    @Setter
    private Candidate candidate;
    @Getter
    private Candidate conflictingCandidate;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        load(Integer.parseInt(requestParameters.get("candidateId")));
    }

    private void load(int candidateId){
        this.candidate = candidatesDAO.findOne(candidateId);
    }

    public void prepareForEditing(Candidate newCandidate) {
        candidate = newCandidate;
        conflictingCandidate = null;
    }

    @Transactional
    public void updateSelectedCandidate() {
        try {
            candidatesDAO.updateAndFlush(candidate);
            load(candidate.getId());
        } catch (OptimisticLockException ole) {

            conflictingCandidate = candidatesDAO.findOne(candidate.getId());
            Hibernate.initialize(conflictingCandidate.getPositionsList());
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
            throw new OptimisticLockException( "Optimistic lock exception");
        }
    }
    @Transactional
    public void overwriteCandidate() {
        candidate.setOptLockVersion(conflictingCandidate.getOptLockVersion());
        updateSelectedCandidate();
    }
    @Transactional
    public String deleteSelectedCandidate(){
        candidatesDAO.delete(candidate);
        return "index?";
    }
}
