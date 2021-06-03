package com.qa.ims.persistence.domain;

public class Order {
	
	
	private Long orderId;
	private Long fkCustomerId;
	
	public Order(Long orderId) {
		super();
		this.orderId = orderId;
	}
	public Order(Long orderId, Long fkCustomerId) {
		super();
		this.orderId = orderId;
		this.fkCustomerId = fkCustomerId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getFkCustomerId() {
		return fkCustomerId;
	}
	public void setFkCustomerId(Long fkCustomerId) {
		this.fkCustomerId = fkCustomerId;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", fkCustomerId=" + fkCustomerId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkCustomerId == null) ? 0 : fkCustomerId.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (fkCustomerId == null) {
			if (other.fkCustomerId != null)
				return false;
		} else if (!fkCustomerId.equals(other.fkCustomerId))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}
	
	
	
	
	
	


}
