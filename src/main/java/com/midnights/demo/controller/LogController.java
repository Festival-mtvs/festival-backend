package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.log.RequestSaveLog;
import com.midnights.demo.aggregate.dto.log.ResponseSaveLog;
import com.midnights.demo.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/v1/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping("/postLog")
    @ResponseBody
    public ResponseEntity<ResponseSaveLog> saveLog(@RequestBody RequestSaveLog requestSaveLog){
        ResponseSaveLog responseSaveLog = logService.saveEndLog(requestSaveLog);

        return ResponseEntity.ok(responseSaveLog);
    }
}
