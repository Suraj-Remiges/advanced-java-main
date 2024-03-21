package com.remiges.adv_java_assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.remiges.adv_java_assignment.dto.RankDto;
import com.remiges.adv_java_assignment.entity.Ranks;
import com.remiges.adv_java_assignment.repo.RankRepo;

@Service
public class RankService {

    @Autowired
    private RankRepo repo;

    public ResponseEntity<?> getAllRank() {
        List<Ranks> rankes = repo.findAll();
        return new ResponseEntity<>(rankes, HttpStatus.OK);

    }

    public ResponseEntity<?> addRank(RankDto rankDto) {

        if (rankDto.getId() == null || rankDto.getRankDesc().isEmpty()) {
            return new ResponseEntity<>("Rank ID or RankDesc is blanck", HttpStatus.BAD_REQUEST);
        } else {
            Ranks rank = new Ranks();

            rank.setId(rankDto.getId());
            rank.setParentRankId(rankDto.getParentRankId());
            rank.setRankDesc(rankDto.getRankDesc());
            repo.save(rank);
            return new ResponseEntity<>(rank, HttpStatus.OK);

        }
    }

    public ResponseEntity<?> getRankById(Integer rankId) {

        if (rankId == null) {

            return new ResponseEntity<>("Rank ID is black", HttpStatus.BAD_REQUEST);
        } else {

            Optional<Ranks> rank = repo.findById(rankId);

            if (rank.isPresent()) {

                return new ResponseEntity<>(rank.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Rank Not Found For id = {" + rankId + "}", HttpStatus.BAD_REQUEST);
            }

        }
    }

}
