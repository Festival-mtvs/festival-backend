package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.log.RequestSaveLog;
import com.midnights.demo.aggregate.dto.log.ResponseSaveLog;
import com.midnights.demo.aggregate.entity.Log;
import com.midnights.demo.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public ResponseSaveLog saveEndLog(RequestSaveLog requestSaveLog) {
        Log log = Log.toEntity(requestSaveLog);

        logRepository.save(log);

        ResponseSaveLog responseSaveLog = ResponseSaveLog.fromEntity(log);

        return responseSaveLog;
    }
}
