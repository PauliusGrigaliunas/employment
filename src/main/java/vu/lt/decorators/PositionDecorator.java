package vu.lt.decorators;

import vu.lt.entities.Position;
import vu.lt.persistence.IPositionsDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Decorator
public abstract class PositionDecorator implements IPositionsDAO {

    @Inject
    @Delegate
    @Any
    IPositionsDAO positionsDAO;

    @Transactional
    public void persist(Position position) {
        //System.out.println("\n>> Decorator doing stuff before ICategoriesDAO is invoked. \n");
        position.setName(position.getName() + " *");
        positionsDAO.persist(position);
    }
}
