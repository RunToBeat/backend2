package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyRecordRepository extends JpaRepository<DailyRecord, Long> {
    List<DailyRecord> findByMemberMemberId(Long memberId);

    List<DailyRecord> findAllByMemberMemberId(Long memberId);

    DailyRecord getByYearMonthDate(LocalDate recordDate);

    DailyRecord findByMemberMemberIdAndYearMonthDate(Long memberId, LocalDate recordDate);
}
