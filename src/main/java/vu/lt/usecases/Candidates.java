package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Employee.Candidate;
import vu.lt.persistence.CandidatesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Candidates {

    @Inject
    private CandidatesDAO candidatesDAO;

    @Getter @Setter
    private Candidate candidateToCreate = new Candidate();

    @Getter
    private List<Candidate> allCandidates;

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
