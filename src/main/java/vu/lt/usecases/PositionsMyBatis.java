package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.myBatis.dao.PositionMapper;
import vu.lt.myBatis.model.Position;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PositionsMyBatis {
    @Inject
    private PositionMapper positionMapper;

    @Getter @Setter
    private List<Position> positionList;

    @Getter @Setter
    private Position positionToCreate = new Position();

    @PostConstruct
    public void init() { this.loadAllPositions();
    }

    private void loadAllPositions() {
        this.positionList = positionMapper.selectAll();
    }

    @Transactional
    public String createPosition() {
        positionMapper.insert(positionToCreate);
        return "/myBatis/positions?faces-redirect=true";
    }
}