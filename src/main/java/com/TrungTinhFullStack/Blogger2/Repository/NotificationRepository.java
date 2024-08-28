package com.TrungTinhFullStack.Blogger2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.Notification;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<com.TrungTinhFullStack.Blogger2.Entity.Notification,Long> {
    List<Notification> findByUserIdAndIsReadFalse(Long userId);
}
