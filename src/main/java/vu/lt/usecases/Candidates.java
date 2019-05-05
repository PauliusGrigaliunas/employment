package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.AbsCandidate;
import vu.lt.entities.Candidate;
import vu.lt.persistence.ICandidatesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Candidates {

    @Inject
    private ICandidatesDAO candidatesDAO;

    @Getter @Setter
    private AbsCandidate candidateToCreate= new Candidate();

    @Getter
    private List<AbsCandidate> allCandidates;

    @PostConstruct
    public void init(){
        loadAllCandidates();
    }

    @Transactional

    public String createCandidate(){
        this.candidatesDAO.persist(candidateToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllCandidates(){
        this.allCandidates = candidatesDAO.loadAll();
    }
}
