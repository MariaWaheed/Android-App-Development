package com.myjobassistant;

public class JobRequirement {
    String job_type;
    String salary;
    String otherinfo;

    public JobRequirement(String job_type, String salary, String otherinfo) {
        this.job_type = job_type;
        this.salary = salary;
        this.otherinfo = otherinfo;
    }

    public JobRequirement() {
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }
}
