package com.cybrilla.request;

public class CustomerRequest {
	private int creditORdebit;

	private Long accoutNumber;

	private Double amount;

	private String address;

	private Long PhoneNumber;

	private int status;

	public Long getAccoutNumber() {
		return accoutNumber;
	}

	public void setAccoutNumber(Long accoutNumber) {
		this.accoutNumber = accoutNumber;
	}

	public int getCreditORdebit() {
		return creditORdebit;
	}

	public void setCreditORdebit(int creditORdebit) {
		this.creditORdebit = creditORdebit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerRequest [creditORdebit=" + creditORdebit + ", accoutNumber=" + accoutNumber + ", amount="
				+ amount + ", address=" + address + ", PhoneNumber=" + PhoneNumber + ", status=" + status + "]";
	}

}
