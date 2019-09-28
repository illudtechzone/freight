package com.illud.freight.client.activiti_rest_api.model.freight;

public class CustomerStatus {

	String status;
	String trackingId;

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
