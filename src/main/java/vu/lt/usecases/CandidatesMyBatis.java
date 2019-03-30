package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.cdi.Transactional;
import vu.lt.mybatis.CandidateMapper;
import vu.lt.mybatis.model.Candidate;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
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
        this.loadAllCandidates();
    }

    private void loadAllCandidates() {
        this.allCandidates = candidateMapper.selectAll();
    }

    @Transactional
    public String createCandidate() {
        candidateMapper.insert(candidateToCreate);
        return "/myBatis/candidates?faces-redirect=true";
    }
}
