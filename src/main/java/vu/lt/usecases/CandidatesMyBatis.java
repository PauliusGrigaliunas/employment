package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.myBatis.model.Candidate;
import vu.lt.myBatis.model.dao.CandidateMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CandidatesMyBatis {
    @Inject
    private CandidateMapper candidateMapper;

    @Getter
    private List<Candidate> allCandidates;

    @Getter @Setter
    private Candidate candidateToCreate = new Candidate();

    @PostConstruct
    public void init() {
        this.loadAllCandidate();
    }

    private void loadAllCandidate() {
        this.allCandidates = candidateMapper.selectAll();
    }

    @Transactional
    public String createCandidate() {
        candidateMapper.insert(candidateToCreate);
        return "/myBatis/teams?faces-redirect=true";
    }
}
