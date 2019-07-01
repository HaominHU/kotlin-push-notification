package com.leon.kotlinpushnotification.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Notifications (
        @Id @GeneratedValue var id: Long? = null,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")var sendTime: Timestamp = Timestamp(System.currentTimeMillis())
//        var send: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
) {}