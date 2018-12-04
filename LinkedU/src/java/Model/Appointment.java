/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ejzumba
 */
public class Appointment {
    private String date;
    private int studentId;
    private int universityId;
    private int appntDate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getAppntDate() {
        return appntDate;
    }

    public void setAppntDate(int appntDate) {
        this.appntDate = appntDate;
    }
    
    
}
