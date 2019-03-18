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

@Model
public class Positions {

    @Inject
    private PositionsDAO positionsDAO;

    @Getter
    @Setter
    private Position positionToCreate = new Position();

    @Getter
    private List<Position> allPositions;

    @PostConstruct
    public void init(){
        loadAllCandidates();
    }

    @Transactional
    public String createCandidate(){
        this.positionsDAO.persist(positionToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllCandidates(){
        this.allPositions = positionsDAO.loadAll();
    }
}