package com.project.logistick.DTO;

public class PaymentRequest {
    private int orderId;
    private String method; // "UPI", "CARD", "COD"
    private Double amount; // optional, if null use order.cost

    public PaymentRequest() {}

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
