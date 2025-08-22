package com.example.jobportalapplication.model;

public class RecentJob {
    private String jobTitle;
    private String companyName;
    private String location;
    private String postedDate;
    private String jobType;
    private double salary;

    public RecentJob(String jobTitle, String companyName, String location, String postedDate, String jobType, double salary) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
        this.postedDate = postedDate;
        this.jobType = jobType;
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public String getJobType() {
        return jobType;
    }

    public double getSalary() {
        return salary;
    }
}
