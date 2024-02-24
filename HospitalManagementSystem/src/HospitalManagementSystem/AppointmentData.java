/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HospitalManagementSystem;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class AppointmentData {
    
    
    private Integer appID ;
    private Integer patientID ;
    private String patientName;
    private Integer doctorID ;
    private String doctorName;
    private String treatment;
    private Date date;
    private String time;
    
    
    public AppointmentData(Integer appID , Integer patientID , String patientName , Integer doctorID , String doctorName , String treatment ,Date date , String time )
    {
        this.appID = appID;
        this.patientID = patientID ;
        this.patientName = patientName ;
        this.doctorID = doctorID;
        this.doctorName = doctorName ;
        this.treatment = treatment;
        this.date = date ;
        this.time = time ;
    }
    
    
    public Integer getAppID()
    {
        return appID;
    }
    
    public Integer getPatientID()
    {
        return patientID ;
    }
    
    public String getPatientName()
    {
        return patientName;
    }
    
     public Integer getDoctorID()
    {
        return doctorID ;
    }
    
    public String getDoctorName()
    {
        return doctorName;
    }
    
    public String getTreatment()
    {
        return treatment ;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public String getTime()
    {
        return time ;
    }
    
    
    
}
