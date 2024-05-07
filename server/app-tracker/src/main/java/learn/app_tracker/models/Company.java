package learn.app_tracker.models;

import java.util.Objects;

public class Company {

    private int companyId;
    private String name;
    private String address;

    public Company() {

    }

    public Company(int companyId, String name, String address) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyId == company.companyId && Objects.equals(name, company.name) && Objects.equals(address, company.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name, address);
    }

}
