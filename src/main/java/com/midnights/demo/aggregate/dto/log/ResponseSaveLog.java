package com.midnights.demo.aggregate.dto.log;

import com.midnights.demo.aggregate.entity.Log;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseSaveLog {
    private Long logNo;

    private Long holdTime;

    private String message;

    public static ResponseSaveLog fromEntity(Log log) {
        return ResponseSaveLog.builder()
                .logNo(log.getLogNo())
                .holdTime(log.getHoldTime())
                .message("저장 완료")
                .build();
    }
}
