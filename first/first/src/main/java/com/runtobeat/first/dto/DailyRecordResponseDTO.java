package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class DailyRecordResponseDTO {
    private String dailyRecordId;
    private Double dailyTotalDistance;
    private Double dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;

}
