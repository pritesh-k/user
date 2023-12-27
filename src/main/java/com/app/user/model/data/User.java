package com.app.user.model.data;

import com.app.user.model.api.AddressInfo;
import com.app.user.model.api.BankInfo;
import com.app.user.model.api.CompanyInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.UUID;

@Document
public class User {
	@JsonProperty("id")
	private UUID id;

	@JsonProperty("dummyId")
	private long dummyId;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("maidenName")
	private String maidenName;

	@JsonProperty("age")
	private Integer age;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	@JsonProperty("birthDate")
	private String birthDate;

	@JsonProperty("image")
	private String image;

	@JsonProperty("bloodGroup")
	private String bloodGroup;

	@JsonProperty("height")
	private Integer height;

	@JsonProperty("weight")
	private double weight;

	@JsonProperty("eyeColor")
	private String eyeColor;

	@JsonProperty("hair")
	private com.app.user.model.api.User.HairInfo hair;

	@JsonProperty("domain")
	private String domain;

	@JsonProperty("ip")
	private String ip;

	@JsonProperty("address")
	private AddressInfo address;

	@JsonProperty("macAddress")
	private String macAddress;

	@JsonProperty("university")
	private String university;

	@JsonProperty("bank")
	private BankInfo bank;

	@JsonProperty("company")
	private CompanyInfo company;

	@JsonProperty("ein")
	private String ein;

	@JsonProperty("ssn")
	private String ssn;

	@JsonProperty("userAgent")
	private String userAgent;

	@JsonProperty("createdDate")
	private ZonedDateTime createdDate;

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}
	// Constructors, getters, and setters

	public User() {
		// Default constructor
	}

	public User(UUID id, String firstName, String lastName, String maidenName, int age, String gender,
				String email, String phone, String username, String password, String birthDate, String image,
				String bloodGroup, int height, double weight, String eyeColor, com.app.user.model.api.User.HairInfo hair, String domain,
				String ip, AddressInfo address, String macAddress, String university, BankInfo bank,
				CompanyInfo company, String ein, String ssn, String userAgent) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.maidenName = maidenName;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.image = image;
		this.bloodGroup = bloodGroup;
		this.height = height;
		this.weight = weight;
		this.eyeColor = eyeColor;
		this.hair = hair;
		this.domain = domain;
		this.ip = ip;
		this.address = address;
		this.macAddress = macAddress;
		this.university = university;
		this.bank = bank;
		this.company = company;
		this.ein = ein;
		this.ssn = ssn;
		this.userAgent = userAgent;
	}

	// Getter and setter methods for each field

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public long getDummyId() {
		return dummyId;
	}

	public void setDummyId(long dummyId) {
		this.dummyId = dummyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public com.app.user.model.api.User.HairInfo getHair() {
		return hair;
	}

	public void setHair(com.app.user.model.api.User.HairInfo hair) {
		this.hair = hair;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public BankInfo getBank() {
		return bank;
	}

	public void setBank(BankInfo bank) {
		this.bank = bank;
	}

	public CompanyInfo getCompany() {
		return company;
	}

	public void setCompany(CompanyInfo company) {
		this.company = company;
	}

	public String getEin() {
		return ein;
	}

	public void setEin(String ein) {
		this.ein = ein;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	// HairInfo class
	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", dummyId=" + dummyId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", maidenName='" + maidenName + '\'' +
				", age=" + age +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", birthDate='" + birthDate + '\'' +
				", image='" + image + '\'' +
				", bloodGroup='" + bloodGroup + '\'' +
				", height=" + height +
				", weight=" + weight +
				", eyeColor='" + eyeColor + '\'' +
				", hair=" + hair +
				", domain='" + domain + '\'' +
				", ip='" + ip + '\'' +
				", address=" + address +
				", macAddress='" + macAddress + '\'' +
				", university='" + university + '\'' +
				", bank=" + bank +
				", company=" + company +
				", ein='" + ein + '\'' +
				", ssn='" + ssn + '\'' +
				", userAgent='" + userAgent + '\'' +
				", createdDate=" + createdDate +
				'}';
	}
}



