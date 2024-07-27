package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class WeeklyRecordResponseDTO {
    private String weeklyRecordId;
    private String memberId;
    private Double weeklyTotalDistance;
    private LocalTime weeklyTotalTime;
    private LocalDate yearWeek;
    private Double weeklyRecordPace;
    private Long weeklyRunningStep;
    private String weekYear;

    public WeeklyRecordResponseDTO(String memberId, Double weeklyTotalDistance, LocalTime weeklyTotalTime, LocalDate yearWeek, Double weeklyRecordPace, Long weeklyRunningStep, String weekYear) {
        this.memberId = memberId;
        this.weeklyTotalDistance = weeklyTotalDistance;
        this.weeklyTotalTime = weeklyTotalTime;
        this.yearWeek = yearWeek;
        this.weeklyRecordPace = weeklyRecordPace;
        this.weeklyRunningStep = weeklyRunningStep;
        this.weekYear = weekYear;
    }
}
