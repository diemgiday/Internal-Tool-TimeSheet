package com.devnguyen.timesheet.service;

import com.devnguyen.timesheet.model.Position;
import com.devnguyen.timesheet.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position findPositionById(int id) {
        return positionRepository.findById(id).orElse(null);
    }

    public void addPosition(Position position) {
        positionRepository.save(position);
    }

    public void deletePosition(int positionId) {
        positionRepository.deleteById(positionId);
    }
}
