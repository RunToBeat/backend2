package com.runtobeat.first.controller;

import com.runtobeat.first.dto.MonthlyRecordRequestDTO;
import com.runtobeat.first.dto.MonthlyRecordResponseDTO;
import com.runtobeat.first.entity.MonthlyRecord;
import com.runtobeat.first.service.MonthlyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monthly-record")
public class MonthlyRecordController {

    @Autowired
    private MonthlyRecordService monthlyRecordService;

    @PostMapping
    public ResponseEntity<MonthlyRecordResponseDTO> createMonthlyRecord(@RequestBody MonthlyRecordRequestDTO requestDTO) {
        MonthlyRecord monthlyRecord = monthlyRecordService.createMonthlyRecord(requestDTO);
        return ResponseEntity.ok(new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonth(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyRecordResponseDTO> getMonthlyRecordById(@PathVariable String id) {
        MonthlyRecord monthlyRecord = monthlyRecordService.getMonthlyRecordById(id);
        return ResponseEntity.ok(new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonth(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep()
        ));
    }

    @GetMapping
    public ResponseEntity<List<MonthlyRecordResponseDTO>> getAllMonthlyRecords() {
        List<MonthlyRecord> monthlyRecords = monthlyRecordService.getAllMonthlyRecords();
        List<MonthlyRecordResponseDTO> responseDTOs = monthlyRecords.stream().map(monthlyRecord -> new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonth(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyRecordResponseDTO> updateMonthlyRecord(@PathVariable String id, @RequestBody MonthlyRecordRequestDTO requestDTO) {
        MonthlyRecord monthlyRecord = monthlyRecordService.updateMonthlyRecord(id, requestDTO);
        return ResponseEntity.ok(new MonthlyRecordResponseDTO(
                monthlyRecord.getMonthlyRecordId(),
                monthlyRecord.getMemberId(),
                monthlyRecord.getMonthlyTotalDistance(),
                monthlyRecord.getMonthlyTotalTime(),
                monthlyRecord.getYearMonth(),
                monthlyRecord.getMonthlyRecordPace(),
                monthlyRecord.getMonthlyRunningStep()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthlyRecord(@PathVariable String id) {
        monthlyRecordService.deleteMonthlyRecord(id);
        return ResponseEntity.noContent().build();
    }
}
