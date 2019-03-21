package vu.lt.usecases;

import lombok.Getter;
import vu.lt.entities.Candidate;
import vu.lt.entities.Position;
import vu.lt.persistence.CandidatesDAO;
import vu.lt.persistence.InterviewsDAO;
import vu.lt.persistence.PositionsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model //RequestScoped
public class PositionCandidates {

    @Inject
    private PositionsDAO positionsDAO;
    @Inject
    private CandidatesDAO candidatesDAO;

    @Getter
    private Candidate candidate = new Candidate();
    @Getter
    private Position position = new Position();

    @Getter
    private List<Candidate> candidatesOfPositions;

    @PostConstruct
    public void init() {
        loadCandidateOfPosition();
    }

    public void loadCandidateOfPosition() {
        this.candidatesOfPositions = positionsDAO.getQualifiedCandidates(this.position.getId());
    }

    @Transactional
    public void mapCandidateToPosition() {
        Candidate candidate = candidatesDAO.loadOne(this.candidate.getId());
        Position  position = positionsDAO.loadOne(this.position.getId());
        candidate.addPosition(position);
        position.addCandidate(candidate);
        candidatesDAO.merge(candidate);
        positionsDAO.merge(position);
    }

    @Transactional
    public String createSkillOfHero(){

        if (this.candidate.getId() != 0){
            Candidate candidate = candidatesDAO.loadOne(this.candidate.getId());
            this.position.addCandidate(candidate);
        }
        this.positionsDAO.persist(this.position);
        return "success";
    }
}