package vu.lt.entities.Employee;

import vu.lt.entities.Interview;
import vu.lt.entities.Position;

import java.util.List;


public interface IWorker {

    Integer getId();
    void setId(Integer id);
    String getName();
    void setName(String name);
    Integer getOptLockVersion();
    void setOptLockVersion(Integer optLockVersion);
    List<Position> getPositionsList();
    void setPositionsList(List<Position> positionsList);
    List<Interview> getInterviews();
    void setInterviews(List Interviews);
    void addPosition(Position position);
    void removePosition(Position position);
}
