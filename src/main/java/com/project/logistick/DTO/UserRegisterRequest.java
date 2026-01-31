package com.project.logistick.DTO;

import com.project.logistick.Entitiesclasses.UsageType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRegisterRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotBlank
    private String password;
    @NotBlank
    private String mobile;
    @NotBlank
    private String location;
    @NotNull
    private UsageType usageType;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public UsageType getUsageType() {
		return usageType;
	}
	public void setUsageType(UsageType usageType) {
		this.usageType = usageType;
	}
    
}

