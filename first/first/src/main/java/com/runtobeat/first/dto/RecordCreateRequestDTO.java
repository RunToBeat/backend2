package com.runtobeat.first.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordCreateRequestDTO {
    private Long memberId;
    private Double runningDistance;
    private Long runningTime;
    private LocalDate recordDate;
    private Double recordPace;
    private Long runningStep;

}
