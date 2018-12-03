package com.miss.artificial_city.respositories;

import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.mappers.BoardMapper;
import com.miss.artificial_city.infastructure.db.mappers.NodeMapper;
import com.miss.artificial_city.infastructure.db.repositories.BoardDao;
import com.miss.artificial_city.mappers.NodeMocks;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoarDaoTest {
    private BoardEntity boardEntity;
    @Before
    public void setUp() {
        boardEntity = NodeMocks.mockBoardEntity();
    }
    @Test
    public void saveBoard() {
       

    }

}
