package com.illud.freight.client.activiti_rest_api.model.freight;

public class TransportOwnerResponse {

	private String finalizedBudget;
	private String truckType;
	private String freightId;
	
	public String getFinalizedBudget() {
		return finalizedBudget;
	}
	public void setFinalizedBudget(String finalizedBudget) {
		this.finalizedBudget = finalizedBudget;
	}
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	public String getFreightId() {
		return freightId;
	}
	public void setFreightId(String freightId) {
		this.freightId = freightId;
	}
	
	
}
