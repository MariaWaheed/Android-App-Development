package com.myjobassistant;

public class Experience {
    String title;
    String company;
    String location;
    String startyear;
    String endyear;
    String emp_type;
    String industry;

    public Experience(){}
    public Experience(String title, String company, String location, String startyear,String endyear,String emp_type,String industry) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.startyear = startyear;
        this.endyear = endyear;
        this.emp_type= emp_type;
        this.industry= industry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }

    public String getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
