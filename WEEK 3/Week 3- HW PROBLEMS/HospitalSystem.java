import java.util.*;

class Patient {
    String patientId, patientName, gender, contactInfo;
    int age;
    String[] medicalHistory, currentTreatments;
    static int totalPatients;

    Patient(String id, String name, int age, String g, String contact, String[] history) {
        patientId=id; patientName=name; this.age=age; gender=g; contactInfo=contact;
        medicalHistory=history; currentTreatments=new String[5];
        totalPatients++;
    }

    void updateTreatment(String treatment) {
        for(int i=0;i<currentTreatments.length;i++)
            if(currentTreatments[i]==null){ currentTreatments[i]=treatment; break; }
    }

    void dischargePatient() {
        currentTreatments=new String[5];
    }
}

class Doctor {
    String doctorId, doctorName, specialization;
    String[] availableSlots;
    int patientsHandled;
    double consultationFee;

    Doctor(String id, String name, String spec, String[] slots, double fee) {
        doctorId=id; doctorName=name; specialization=spec;
        availableSlots=slots; consultationFee=fee;
    }
}

class Appointment {
    String appointmentId, appointmentDate, appointmentTime, status;
    Patient patient;
    Doctor doctor;
    static int totalAppointments;
    static double totalRevenue;
    static String hospitalName;

    Appointment(String id, Patient p, Doctor d, String date, String time, String type) {
        appointmentId=id; patient=p; doctor=d;
        appointmentDate=date; appointmentTime=time;
        status="Scheduled"; totalAppointments++;
        double bill=generateBill(type);
        totalRevenue+=bill; doctor.patientsHandled++;
    }

    double generateBill(String type) {
        if(type.equals("Consultation")) return doctor.consultationFee;
        else if(type.equals("Follow-up")) return doctor.consultationFee*0.5;
        else return doctor.consultationFee*2;
    }

    void cancelAppointment() {
        status="Cancelled";
    }
}

public class HospitalSystem {
    static void generateHospitalReport() {
        System.out.println("Hospital: "+Appointment.hospitalName);
        System.out.println("Total Patients: "+Patient.totalPatients);
        System.out.println("Total Appointments: "+Appointment.totalAppointments);
        System.out.println("Total Revenue: "+Appointment.totalRevenue);
    }

    static double getDoctorUtilization(Doctor d) {
        return d.patientsHandled*100.0/Appointment.totalAppointments;
    }

    static void getPatientStatistics(Patient p) {
        System.out.println("Patient: "+p.patientName+" Age:"+p.age+" Gender:"+p.gender);
        System.out.println("History: "+Arrays.toString(p.medicalHistory));
        System.out.println("Treatments: "+Arrays.toString(p.currentTreatments));
    }

    public static void main(String[] args) {
        Appointment.hospitalName="CityCare Hospital";

        Patient p1=new Patient("P1","Arun",30,"M","9999999999",new String[]{"Allergy"});
        Patient p2=new Patient("P2","Sneha",25,"F","8888888888",new String[]{"Asthma"});

        Doctor d1=new Doctor("D1","Dr. Mehta","Cardiology",new String[]{"10AM","11AM"},500);
        Doctor d2=new Doctor("D2","Dr. Rani","Dermatology",new String[]{"2PM","3PM"},300);

        Appointment a1=new Appointment("A1",p1,d1,"01-09-2025","10AM","Consultation");
        p1.updateTreatment("Heart Checkup");

        Appointment a2=new Appointment("A2",p2,d2,"02-09-2025","2PM","Emergency");
        p2.updateTreatment("Skin Allergy Test");

        generateHospitalReport();
        System.out.println("Utilization of "+d1.doctorName+": "+getDoctorUtilization(d1)+"%");
        getPatientStatistics(p1);
        getPatientStatistics(p2);
    }
}
