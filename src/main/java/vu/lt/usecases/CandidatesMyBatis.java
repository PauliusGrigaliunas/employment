package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.myBatis.model.Candidate;
import vu.lt.myBatis.dao.CandidateMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CandidatesMyBatis {
    @Inject
    private CandidateMapper candidatesDAO;

    @Getter
    private List<Candidate> allCandidates;

    @Getter @Setter
    private Candidate candidateToCreate = new Candidate();

    @PostConstruct
    public void init() {
        this.loadAllCandidate();
    }

    private void loadAllCandidate() {
        this.allCandidates = candidatesDAO.selectAll();
    }

    @Transactional
    public String createCandidate() {
        int a=5;
        candidatesDAO.insert(candidateToCreate);
        return "/myBatis/candidates?faces-redirect=true";
    }
}
