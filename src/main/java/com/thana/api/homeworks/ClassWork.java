package com.thana.api.homeworks;

import java.util.Date;

public record ClassWork(String subjectName, String works, Date date) {

    public String getSubject() {
        return subjectName;
    }

    public String getWorks() {
        return works;
    }

    public Date getDate() {
        return date;
    }

    public boolean subjectIsRelatedTo(String name) {
        return this.subjectName.contains(name);
    }
}
