package com.rif.backend.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserEmail(String email);

    @Query("SELECT n FROM Notification n WHERE n.message LIKE %:keyword%")
    List<Notification> findApprovalAndRevisionNotifications(@Param("keyword") String keyword);
}
