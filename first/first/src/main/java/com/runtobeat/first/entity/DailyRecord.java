package com.runtobeat.first.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Dailyrecord")
@Getter
@Setter
public class DailyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dailyRecordId;
    private String memberId;
    private Double dailyTotalDistance;
    private LocalTime dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;
    private Long dailyRunningStep;

    public DailyRecord(String dailyRecordId, Double dailyTotalDistance, LocalTime dailyTotalTime, LocalDate yearMonthDate, Double dailyRecordPace, Long dailyRunningStep) {
        this.dailyRecordId = dailyRecordId;
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
        this.dailyRunningStep = dailyRunningStep;
    }
    public DailyRecord(Double dailyTotalDistance, LocalTime dailyTotalTime, LocalDate yearMonthDate, Double dailyRecordPace, Long dailyRunningStep) {
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
        this.dailyRunningStep = dailyRunningStep;
    }




}
