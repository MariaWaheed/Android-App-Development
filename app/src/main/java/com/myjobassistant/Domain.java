package com.myjobassistant;

public class Domain {
    String SelectedDomain;

    public Domain(String selectedDomain) {
        SelectedDomain = selectedDomain;
    }

    public Domain() {
    }

    public String getSelectedDomain() {
        return SelectedDomain;
    }

    public void setSelectedDomain(String selectedDomain) {
        SelectedDomain = selectedDomain;
    }
}
