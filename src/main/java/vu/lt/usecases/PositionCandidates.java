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
        loadCandidateOfposition();
    }

    public void loadCandidateOfposition() {
        //this.candidatesOfPositions = positionsDAO.getSkillHeroes(this.position.getId());
    }

    /*@Transactional
    public void mapHeroToSkill() {
        Candidate candidate = CandidatesDAO.loadOne(this.candidate.getId());
        Position position = skillsDAO.loadOne(this.position.getId());
        position.add(candidate);
        skillsDAO.merge(position);
    }

    @Transactional
    public String createSkillOfHero(){
        // Hero must use GenerationType.IDENTITY to ensure id starts from 1
        if (this.candidate.getId() != 0){
            Hero hero = heroesDAO.loadOne(this.candidate.getId());
            this.position.addHero(hero);
        }
        this.skillsDAO.persist(this.position);
        return "success";
    }*/
}