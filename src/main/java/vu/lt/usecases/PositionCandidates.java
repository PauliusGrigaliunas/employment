package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Candidate;
import vu.lt.entities.ICandidate;
import vu.lt.entities.Position;
import vu.lt.persistence.ICandidatesDAO;
import vu.lt.persistence.PositionsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PositionCandidates {

    @Inject
    private PositionsDAO positionsDAO;
    @Inject
    private ICandidatesDAO candidatesDAO;
    @Getter
    @Setter
    private ICandidate candidate = new Candidate();
    @Getter
    @Setter
    private Position position = new Position();

    @Getter
    private List<ICandidate> candidatesOfPositions;

    @PostConstruct
    public void init() {
        loadCandidateOfPosition();
    }

    private void loadCandidateOfPosition(){
        this.candidatesOfPositions = candidatesDAO.loadAll();
    }

    @Transactional
    public String createCandidate(){
        this.candidatesDAO.persist(candidate);
        return "index?faces-redirect=true";
    }
    @Transactional
    public String createPosition(){
        this.positionsDAO.persist(position);
        return "index?faces-redirect=true";
    }

    @Transactional
    public String mapCandidateToPosition() {
        ICandidate candidate = candidatesDAO.findOne(this.candidate.getId());
        Position  position = positionsDAO.findOne(this.position.getId());
        candidate.addPosition(position);
        candidatesDAO.merge(candidate);
        return "candidacy?faces-redirect=true";
    }

    @Transactional
    public String createPositionToCandidate(){

        if (this.candidate.getId() != 0 && this.position.getId() != 0){
            ICandidate candidate = candidatesDAO.loadOne(this.candidate.getId());
            this.position.addCandidate(candidate);
        }
        this.positionsDAO.persist(this.position);
        return "success";
    }
}