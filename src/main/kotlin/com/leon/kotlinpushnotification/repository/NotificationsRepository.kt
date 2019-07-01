package com.leon.kotlinpushnotification.repository

import com.leon.kotlinpushnotification.model.Notifications
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationsRepository : JpaRepository<Notifications, Long> {}