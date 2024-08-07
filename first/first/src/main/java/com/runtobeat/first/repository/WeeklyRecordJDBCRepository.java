package com.runtobeat.first.repository;

import com.runtobeat.first.entity.Record;
import com.runtobeat.first.entity.WeeklyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

@Repository
public class WeeklyRecordJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    WeeklyRecordRepository weeklyRecordRepository;

    public WeeklyRecordJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Calculate the ISO week number and year from a LocalDate
    public String getWeekYear(LocalDate date) {
        int weekNumber = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = date.get(IsoFields.WEEK_BASED_YEAR);
        return year + "-" + weekNumber;
    }

    public void save(Record record) {
        // Calculate week year from record date
        String weekYear = getWeekYear(record.getRecordDate());

        // Retrieve existing weekly record by weekYear
        WeeklyRecord existingRecord = weeklyRecordRepository.getByWeekYear(weekYear);

        if (existingRecord == null) {
            // If no existing record, create a new WeeklyRecord
            WeeklyRecord newRecord = new WeeklyRecord(
                    record.getMember(),
                    record.getRunningDistance(),
                    record.getRunningTime(),
                    record.getRecordDate(),
                    record.getRecordPace(),
                    record.getRunningStep(),
                    weekYear
            );
            weeklyRecordRepository.save(newRecord);
        } else {
            // Update existing record
            existingRecord.setWeeklyTotalDistance(existingRecord.getWeeklyTotalDistance() + record.getRunningDistance());
            long totalExistingSeconds = existingRecord.getWeeklyTotalTime();
            long totalNewSeconds = record.getRunningTime();
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;
            existingRecord.setWeeklyTotalTime(updateTotalSeconds);
            double newTotalDistance = existingRecord.getWeeklyTotalDistance();
            existingRecord.setWeeklyRecordPace(updateTotalSeconds / newTotalDistance);

            existingRecord.setWeeklyRunningStep(existingRecord.getWeeklyRunningStep() + record.getRunningStep());

            weeklyRecordRepository.save(existingRecord);
        }
    }

    public Double getThisWeekAvgDistance() {
        // Get today's date
        LocalDate today = LocalDate.now();
        // Calculate the week year string for today
        String weekYear = getWeekYear(today);
        // SQL query to calculate the average distance for the current week
        String sql = "SELECT AVG(weeklyTotalDistance) FROM WeeklyRecord WHERE weekYear = ?";
        // Execute the query and return the average distance
        return jdbcTemplate.queryForObject(sql, new Object[]{weekYear}, Double.class);
    }
}
