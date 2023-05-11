package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "TestInProgress", schema = "public", catalog = "manufacture")
public class TestInProgressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "TestInProgress")
    private int testInProgress;

    public int getTestInProgress() {
        return testInProgress;
    }

    public void setTestInProgress(int testInProgress) {
        this.testInProgress = testInProgress;
    }

    @Basic
    @Column(name = "TestID")
    private int testId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "SerialNum")
    private int serialNum;

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestInProgressEntity that = (TestInProgressEntity) o;
        return testInProgress == that.testInProgress && testId == that.testId && serialNum == that.serialNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testInProgress, testId, serialNum);
    }
}
