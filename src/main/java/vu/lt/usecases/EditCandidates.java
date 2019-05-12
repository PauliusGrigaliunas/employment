package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import vu.lt.entities.ICandidate;
import vu.lt.persistence.ICandidatesDAO;

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
    private ICandidatesDAO candidatesDAO;

    @Getter
    @Setter
    private ICandidate candidate;
    @Getter
    private ICandidate conflictingCandidate;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        load(Integer.parseInt(requestParameters.get("candidateId")));
    }

    private void load(int candidateId){
        this.candidate = candidatesDAO.findOne(candidateId);
    }

    public void prepareForEditing(ICandidate newCandidate) {
        candidate = newCandidate;
        conflictingCandidate = null;
    }

    @Transactional
    public String updateSelectedCandidate() {
        try {
            candidatesDAO.updateAndFlush(candidate);
            load(candidate.getId());
        } catch (OptimisticLockException ole) {

            conflictingCandidate = candidatesDAO.findOne(candidate.getId());
            Hibernate.initialize(conflictingCandidate.getPositionsList());
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
            //throw new OptimisticLockException( "Optimistic lock exception");
            return "/editCandidate.xhtml?faces-redirect=true&candidateId=" + this.candidate.getId() + "&error=optimistic-lock-exception";
        }
        return "/editCandidate.xhtml?faces-redirect=true&candidateId=" + candidate.getId();
    }
    @Transactional
    public void overwriteCandidate() {
        candidate.setOptLockVersion(conflictingCandidate.getOptLockVersion());
        updateSelectedCandidate();
    }

    @Transactional
    public String deleteSelectedCandidate(){
        try {
            candidatesDAO.delete(candidate);
        } catch (Exception e) {
            System.out.println("asdf " + e.getMessage());
            return "failed";
        }
        return "success";
    }
}
