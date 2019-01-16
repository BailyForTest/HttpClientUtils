package com.dingtone.parsing;

public class Tasks {
    private Long taskId;
    private Long orderId;
    private String purchaseAppId;
    private String taskType;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPurchaseAppId() {
        return purchaseAppId;
    }

    public void setPurchaseAppId(String purchaseAppId) {
        this.purchaseAppId = purchaseAppId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
