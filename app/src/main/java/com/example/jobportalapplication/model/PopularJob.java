package com.example.jobportalapplication.model;

public class PopularJob {
    private String companyName;
    private String jobLocation;
    private String jobTitle;
    private String jobSalary;
    private String postedInfo;
    private String jobTags;

    public PopularJob(String companyName, String jobLocation, String jobTitle,
                      String jobSalary, String postedInfo, String jobTags) {
        this.companyName = companyName;
        this.jobLocation = jobLocation;
        this.jobTitle = jobTitle;
        this.jobSalary = jobSalary;
        this.postedInfo = postedInfo;
        this.jobTags = jobTags;
    }

    public String getCompanyName() { return companyName; }
    public String getJobLocation() { return jobLocation; }
    public String getJobTitle() { return jobTitle; }
    public String getJobSalary() { return jobSalary; }
    public String getPostedInfo() { return postedInfo; }
    public String getJobTags() { return jobTags; }
}
