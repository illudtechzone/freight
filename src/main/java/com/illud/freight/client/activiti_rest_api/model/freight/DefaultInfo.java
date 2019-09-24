package com.illud.freight.client.activiti_rest_api.model.freight;

public class DefaultInfo {

	String source;
    String destination;
    Double buget;
    Long customerId;
    
    
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getBuget() {
		return buget;
	}
	public void setBuget(Double buget) {
		this.buget = buget;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
    
    
}
