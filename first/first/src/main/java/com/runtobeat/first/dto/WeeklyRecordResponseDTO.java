package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WeeklyRecordResponseDTO {
    private Long weeklyRecordId;
    private Long memberId;
    private Double weeklyTotalDistance;
    private Long weeklyTotalTime;
    private LocalDate yearWeek;
    private Double weeklyRecordPace;
    private Long weeklyRunningStep;
    private String weekYear;

    public WeeklyRecordResponseDTO(Long memberId, Double weeklyTotalDistance, Long weeklyTotalTime, LocalDate yearWeek, Double weeklyRecordPace, Long weeklyRunningStep, String weekYear) {
        this.memberId = memberId;
        this.weeklyTotalDistance = weeklyTotalDistance;
        this.weeklyTotalTime = weeklyTotalTime;
        this.yearWeek = yearWeek;
        this.weeklyRecordPace = weeklyRecordPace;
        this.weeklyRunningStep = weeklyRunningStep;
        this.weekYear = weekYear;
    }
}

