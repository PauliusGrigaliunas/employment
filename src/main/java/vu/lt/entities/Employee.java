package vu.lt.entities;

import org.mybatis.cdi.Transactional;

import javax.enterprise.inject.Specializes;
import javax.persistence.Entity;

@Entity
@Specializes
public class Employee extends ICandidate{


    @Override
    @Transactional
    public void addPosition(Position position) {

        position.setName(position.getName() + "!");
        super.addPosition(position);
    }

}
