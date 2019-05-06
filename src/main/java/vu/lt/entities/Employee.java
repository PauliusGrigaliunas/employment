package vu.lt.entities;

import org.mybatis.cdi.Transactional;

import javax.enterprise.inject.Specializes;
import javax.persistence.Entity;

@Entity
public class Employee extends ICandidate{


    @Override
    @Transactional
    public void addPosition(Position position) {

        if( !positionsList.contains(position) ){
        super.addPosition(position);
        }
    }

}
