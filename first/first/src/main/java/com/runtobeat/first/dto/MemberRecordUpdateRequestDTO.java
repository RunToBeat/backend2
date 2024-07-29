package com.runtobeat.first.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberRecordUpdateRequestDTO {
    private Double totalDistance;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime totalTime;
    private Double avgPace;
}