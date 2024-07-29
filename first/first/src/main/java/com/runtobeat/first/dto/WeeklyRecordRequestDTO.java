package com.runtobeat.first.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WeeklyRecordRequestDTO {
    private Long weeklyRecordId;
    private Long memberId;
    private Double weeklyTotalDistance;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime weeklyTotalTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearWeek;
    private Double weeklyRecordPace;
    private Long weeklyRunningStep;
    private String weekYear;

    public WeeklyRecordRequestDTO(Long memberId, Double weeklyTotalDistance, LocalTime weeklyTotalTime, LocalDate yearWeek, Double weeklyRecordPace, Long weeklyRunningStep, String weekYear) {
        this.memberId = memberId;
        this.weeklyTotalDistance = weeklyTotalDistance;
        this.weeklyTotalTime = weeklyTotalTime;
        this.yearWeek = yearWeek;
        this.weeklyRecordPace = weeklyRecordPace;
        this.weeklyRunningStep = weeklyRunningStep;
        this.weekYear = weekYear;
    }
}
