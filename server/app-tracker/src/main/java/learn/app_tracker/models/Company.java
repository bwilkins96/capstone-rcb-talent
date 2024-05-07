package learn.app_tracker.models;

import java.util.Objects;

public class Company {

    private int companyId;
    private String name;
    private String email;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String phone;

    public Company(int companyId, String name, String email, String address, String city, String state, String postalCode, String phone) {
        this.companyId = companyId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(email, company.email) && Objects.equals(address, company.address) && Objects.equals(city, company.city) && Objects.equals(state, company.state) && Objects.equals(postalCode, company.postalCode) && Objects.equals(phone, company.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, address, city, state, postalCode, phone);
    }

}
