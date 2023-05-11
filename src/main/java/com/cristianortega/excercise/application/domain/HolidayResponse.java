package com.cristianortega.excercise.application.domain;

import java.util.List;

public class HolidayResponse {

    private String status;
    private List<Holiday> data;

    public HolidayResponse() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Holiday> getData() {
        return data;
    }

    public void setData(List<Holiday> data) {
        this.data = data;
    }
}
