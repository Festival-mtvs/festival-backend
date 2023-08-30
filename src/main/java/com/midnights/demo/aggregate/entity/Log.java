package com.midnights.demo.aggregate.entity;

import com.midnights.demo.aggregate.dto.log.RequestSaveLog;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("로그 번호")
    @Column(name = "log_no")
    private Long logNo;

    @Column(name = "hold_time")
    @Comment("유지 시간")
    private Long holdTime;

    public static Log toEntity(RequestSaveLog requestSaveLog) {
        return Log.builder()
                .holdTime(requestSaveLog.getHoldTime())
                .build();
    }
}
