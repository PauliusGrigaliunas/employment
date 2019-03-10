package vu.lt.persistence;

import vu.lt.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class PlayersDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(Player player){
        this.em.persist(player);
    }
}
