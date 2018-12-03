package com.miss.artificial_city.application;

import com.miss.artificial_city.dto.GetSavedBoardResponse;
import com.miss.artificial_city.dto.SaveBoardRequest;

import java.util.List;

public interface CreatorService {
    GetSavedBoardResponse openSimulationBoard(String id);
    void saveSimulationBoard(SaveBoardRequest request);
    List<String> getAllBoardNames();
    void save();
}
