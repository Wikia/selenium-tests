package com.wikia.webdriver.elements.oasis.components.notifications;

public enum NotificationType {
    CONFIRM("wds-success"),
    NOTIFY("wds-message"),
    ERROR("wds-alert"),
    WARN("wds-warning"),
    NON_DISMISSIBLE("nonDismissible");

    private final String className;

    NotificationType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}