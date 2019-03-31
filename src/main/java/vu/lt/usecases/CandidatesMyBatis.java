package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.myBatis.model.Candidate;
import vu.lt.myBatis.dao.CandidateMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@ApplicationScoped
public class CandidatesMyBatis {
    @Inject
    private CandidateMapper candidateMapper;

    @Getter @Setter
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
        return "/myBatis/candidates?faces-redirect=true";
    }
}
