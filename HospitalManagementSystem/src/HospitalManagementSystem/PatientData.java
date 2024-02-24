/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HospitalManagementSystem;

/**
 *
 * @author hp
 */
public class PatientData {
    
    private Integer patientNumber;
    private String firstName;
    private String lastName;
    private Integer age;
    private String contactNumber;
    private String address;
    
    
    public PatientData(Integer patientNumber , String firstName , String lastName , Integer age , String contactNumber , String address)
    {
        this.patientNumber = patientNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.address = address;
    }
    
    public Integer getPatientNumber()
    {
        return patientNumber ;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public Integer getAge()
    {
        return age;
    }
    
    public String getContactNumber()
    {
        return contactNumber;
    }
    
    public String getAddress()
    {
        return address;
    }
    
}
