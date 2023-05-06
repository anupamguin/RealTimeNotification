package com.realtime.notification.model;

import java.io.Serializable;

public class NotificationMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;
	private int entityId;
	private String notificationName;
	private String notificationType;

	public NotificationMessage() {
		super();
	}

	public NotificationMessage(int userId, int entityId, String notificationName, String notificationType) {
		super();
		this.userId = userId;
		this.entityId = entityId;
		this.notificationName = notificationName;
		this.notificationType = notificationType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getNotificationName() {
		return notificationName;
	}

	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	@Override
	public String toString() {
		return "NotificationMessage [userId=" + userId + ", entityId=" + entityId + ", notificationName="
				+ notificationName + ", notificationType=" + notificationType + "]";
	}

}
