package com.runtobeat.first.service;

import com.runtobeat.first.dto.DailyRecordRequestDTO;
import com.runtobeat.first.dto.DailyRecordResponseDTO;
import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.Record;
import com.runtobeat.first.repository.DailyRecordJDBCRepository;
import com.runtobeat.first.repository.DailyRecordRepository;
import com.runtobeat.first.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyRecordService {

    @Autowired
    private DailyRecordRepository dailyRecordRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DailyRecordJDBCRepository dailyRecordJDBCRepository;


    public DailyRecord createDailyRecord(DailyRecordRequestDTO requestDTO) {
        DailyRecord dailyRecord = new DailyRecord(
                memberRepository.findById(requestDTO.getMemberId()).get(),
                requestDTO.getDailyTotalDistance(),
                requestDTO.getDailyTotalTime(),
                requestDTO.getYearMonthDate(),
                requestDTO.getDailyRecordPace(),
                requestDTO.getDailyRunningStep()
        );
        return dailyRecordRepository.save(dailyRecord);
    }

    /*스토리기록(Record) 에 기록이 하나 추가되면 동시에 dailyRecord값이 갱신된다 (더해진다 )
    public List<DailyRecord> getDailyRecordByMemeberId(String memberId) {
        //DailyRecord 테이블에서 멤버 아이디에 해당하는 값들을 where 절로 가져오고
        // 날짜별로 내림차순 , 7개만 가져온다
    }

    //ㅇDailyRecord 리스트에서 totalDistance를 가져 */


    public DailyRecord getDailyRecordByMemberId(Long id) {
        return dailyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<DailyRecord> getAllDailyRecords() {
        return dailyRecordRepository.findAll();
    }

    public DailyRecord updateDailyRecord(Long id, DailyRecordRequestDTO requestDTO) {
        DailyRecord existingRecord = dailyRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.setDailyTotalDistance(requestDTO.getDailyTotalDistance());
        existingRecord.setDailyTotalTime(requestDTO.getDailyTotalTime());
        existingRecord.setYearMonthDate(requestDTO.getYearMonthDate());
        existingRecord.setDailyRecordPace(requestDTO.getDailyRecordPace());
        return dailyRecordRepository.save(existingRecord);
    }

    public void deleteDailyRecord(Long id) {
        dailyRecordRepository.deleteById(id);
    }

    public List<DailyRecordResponseDTO> getDailyRecordListByMemberId(Long memberId) {
        List<DailyRecord> dailyRecordList = dailyRecordRepository.findAllByMemberMemberId(memberId);
        return dailyRecordList.stream().map(this::fromEntity).toList();
    }

    public DailyRecordResponseDTO fromEntity(DailyRecord dailyRecord) {
        return new DailyRecordResponseDTO(
                dailyRecord.getDailyRecordId(),
                dailyRecord.getMember().getMemberId(),
                dailyRecord.getDailyTotalDistance(),
                dailyRecord.getDailyTotalTime(),
                dailyRecord.getYearMonthDate(),
                dailyRecord.getDailyRecordPace(),
                dailyRecord.getDailyRunningStep());
    }


    public void updateDailyRecord(Record savedRecord) {
        DailyRecord originDaily = dailyRecordRepository.findByMemberMemberIdAndYearMonthDate(savedRecord.getMember().getMemberId(), savedRecord.getRecordDate());

        if (originDaily == null) {
            originDaily = new DailyRecord(
                    savedRecord.getMember(),
                    savedRecord.getRunningDistance(),
                    savedRecord.getRunningTime(),
                    savedRecord.getRecordDate(),
                    savedRecord.getRecordPace(),
                    savedRecord.getRunningStep()
            );
        } else {
            Double newDailyDistance = originDaily.getDailyTotalDistance() + savedRecord.getRunningDistance();
            long totalExistingSeconds = originDaily.getDailyTotalTime();
            long totalNewSeconds = savedRecord.getRunningTime();
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;

            long newDailyTime = updateTotalSeconds;
            Double newDailyPace = (newDailyDistance > 0) ? (updateTotalSeconds / newDailyDistance) : 0.0;

            Long newDailyStep = originDaily.getDailyRunningStep() + savedRecord.getRunningStep();

            originDaily.setDailyTotalDistance(newDailyDistance);
            originDaily.setDailyTotalTime(newDailyTime);
            originDaily.setDailyRecordPace(newDailyPace);
            originDaily.setDailyRunningStep(newDailyStep);
        }

        dailyRecordRepository.save(originDaily);
    }


    public Double getTodayAvgDistance() {
        return dailyRecordJDBCRepository.getTodayAvgDistance();
    }
}
