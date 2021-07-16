package com.myjobassistant;

public class Documents {
    String cvname;
    String cvurl;

    public Documents(String cvname, String cvurl) {
        this.cvname = cvname;
        this.cvurl = cvurl;
    }

    public Documents() {
    }

    public String getCvname() {
        return cvname;
    }

    public void setCvname(String cvname) {
        this.cvname = cvname;
    }

    public String getCvurl() {
        return cvurl;
    }

    public void setCvurl(String cvurl) {
        this.cvurl = cvurl;
    }
}
