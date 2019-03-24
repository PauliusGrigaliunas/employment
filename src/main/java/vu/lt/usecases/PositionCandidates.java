package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Candidate;
import vu.lt.entities.Position;
import vu.lt.persistence.CandidatesDAO;
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
    @Setter
    private Candidate candidate = new Candidate();
    @Getter
    @Setter
    private Position position = new Position();

    @Getter
    private List<Candidate> candidatesOfPositions;
    @Getter
    private List<Candidate> positionsOfPositions;

    @PostConstruct
    public void init() {
        loadCandidateOfPosition();
    }

    public void loadCandidateOfPosition() {
       /* /*this.candidatesOfPositions = candidatesDAO.(this.candidate.getId());
        this.positionsOfPositions = positionsDAO.getQualifiedCandidates(this.position.getId());*/
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
    public void mapCandidateToPosition() {
        Candidate candidate = candidatesDAO.loadOne(this.candidate.getId());
        Position  position = positionsDAO.loadOne(this.position.getId());
        candidate.addPosition(position);
        position.addCandidate(candidate);
        candidatesDAO.merge(candidate);
        positionsDAO.merge(position);
    }

    @Transactional
    public String createPositionToCandidate(){

        if (this.candidate.getId() != 0 && this.position.getId() != 0){
            Candidate candidate = candidatesDAO.loadOne(this.candidate.getId());
            this.position.addCandidate(candidate);
        }
        this.positionsDAO.persist(this.position);
        return "success";
    }
}