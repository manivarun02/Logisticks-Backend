package com.project.logistick.DTO;

public class PricePreviewResponse {

    private Double distanceKm;
    private Double cost;

    public PricePreviewResponse() {
    }

    public PricePreviewResponse(Double distanceKm, Double cost) {
        this.distanceKm = distanceKm;
        this.cost = cost;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
