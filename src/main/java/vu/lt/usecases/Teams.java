package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Team;
import vu.lt.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Teams {

    @Inject
    private TeamsDAO teamsDAO;

    @Getter @Setter
    private Team teamToCreate = new Team();

    @Getter
    private List<Team> allTeams;

    @PostConstruct
    public void init(){
        loadAllTeams();
    }

    @Transactional
    public String createTeam(){
        this.teamsDAO.persist(teamToCreate);
        return "success";
    }

    private void loadAllTeams(){
        this.allTeams = teamsDAO.loadAll();
    }
}
