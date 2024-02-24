/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HospitalManagementSystem;

/**
 *
 * @author hp
 */
public class DoctorData {
    
    private Integer doctorNumber;
    private String firstName;
    private String lastName;
    private String specialization;
    private String contactNumber;
    private String emailAddress;
    
    
    public DoctorData(Integer doctorNumber , String firstName , String lastName , String specialization , String contactNumber , String emailAddress)
    {
        this.doctorNumber = doctorNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
    }
    
    public Integer getDoctorNumber()
    {
        return doctorNumber ;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getSpecialization()
    {
        return specialization;
    }
    
    public String getContactNumber()
    {
        return contactNumber;
    }
    
    public String getEmailAddress()
    {
        return emailAddress;
    }
    
}
