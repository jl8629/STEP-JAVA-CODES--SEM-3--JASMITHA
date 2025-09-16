import java.time.LocalDate;
import java.util.*;

final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory, LocalDate birthDate, String bloodType) {
        if (recordId == null || recordId.isEmpty() || patientDNA == null || patientDNA.isEmpty() || birthDate == null || bloodType == null || bloodType.isEmpty())
            throw new IllegalArgumentException("Invalid medical record data");
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = allergies != null ? allergies.clone() : new String[0];
        this.medicalHistory = medicalHistory != null ? medicalHistory.clone() : new String[0];
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public String[] getAllergies() { return allergies.clone(); }
    public String[] getMedicalHistory() { return medicalHistory.clone(); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }
    public final boolean isAllergicTo(String substance) {
        for (String allergy : allergies) if (allergy.equalsIgnoreCase(substance)) return true;
        return false;
    }

    @Override
    public String toString() { return "MedicalRecord{" + "recordId='" + recordId + "', birthDate=" + birthDate + ", bloodType='" + bloodType + "'}"; }
    @Override
    public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof MedicalRecord)) return false; MedicalRecord that = (MedicalRecord) o; return Objects.equals(recordId, that.recordId); }
    @Override
    public int hashCode() { return Objects.hash(recordId); }
}

class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord;
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;
    private int roomNumber;
    private String attendingPhysician;

    public Patient(String patientId, MedicalRecord record) {
        if (patientId == null || patientId.isEmpty() || record == null) throw new IllegalArgumentException("Invalid patient data");
        this.patientId = patientId;
        this.medicalRecord = record;
    }
    public Patient(String patientId, MedicalRecord record, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician) {
        this(patientId, record);
        this.currentName = currentName;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
    }
    public Patient(MedicalRecord record, String newId) { this(newId, record); }

    String getBasicInfo() { return "PatientID=" + patientId + ", Name=" + currentName; }
    public String getPublicInfo() { return "Name=" + currentName + ", Room=" + roomNumber; }

    public String getPatientId() { return patientId; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public String getCurrentName() { return currentName; }
    public void setCurrentName(String currentName) { this.currentName = currentName; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String insuranceInfo) { this.insuranceInfo = insuranceInfo; }
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String attendingPhysician) { this.attendingPhysician = attendingPhysician; }

    @Override
    public String toString() { return "Patient{" + "patientId='" + patientId + "', currentName='" + currentName + "', room=" + roomNumber + "}"; }
    @Override
    public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof Patient)) return false; Patient patient = (Patient) o; return Objects.equals(patientId, patient.patientId); }
    @Override
    public int hashCode() { return Objects.hash(patientId); }
}

class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = certifications != null ? new HashSet<>(certifications) : new HashSet<>();
    }
    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return new HashSet<>(certifications); }
    @Override
    public String toString() { return "Doctor{" + "license='" + licenseNumber + "', specialty='" + specialty + "'}"; }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = qualifications != null ? new ArrayList<>(qualifications) : new ArrayList<>();
    }
    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return new ArrayList<>(qualifications); }
    @Override
    public String toString() { return "Nurse{" + "nurseId='" + nurseId + "', shift='" + shift + "'}"; }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        this.adminId = adminId;
        this.accessPermissions = accessPermissions != null ? new ArrayList<>(accessPermissions) : new ArrayList<>();
    }
    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return new ArrayList<>(accessPermissions); }
    @Override
    public String toString() { return "Administrator{" + "adminId='" + adminId + "'}"; }
}

class HospitalSystem {
    private final Map<String, Object> patientRegistry = new HashMap<>();
    public static final String PRIVACY_POLICY = "STRICT";

    public boolean admitPatient(Object patient, Object staff) {
        if (!(patient instanceof Patient)) return false;
        if (!validateStaffAccess(staff, patient)) return false;
        Patient p = (Patient) patient;
        patientRegistry.put(p.getPatientId(), p);
        return true;
    }

    private boolean validateStaffAccess(Object staff, Object patient) {
        if (staff instanceof Doctor) return true;
        if (staff instanceof Nurse) return true;
        if (staff instanceof Administrator) return true;
        return false;
    }

    Map<String, Object> getPatientRegistry() { return new HashMap<>(patientRegistry); }
}

public class MainHospitalSystem {
    public static void main(String[] args) {
        MedicalRecord record = new MedicalRecord("R001", "DNA123", new String[]{"Peanuts"}, new String[]{"Asthma"}, LocalDate.of(1990, 1, 1), "O+");
        Patient patient = new Patient("P001", record, "Alice", "Bob", "XYZ Insurance", 101, "Dr. Smith");
        Doctor doctor = new Doctor("LIC123", "Cardiology", new HashSet<>(Arrays.asList("Surgery", "Pediatrics")));
        Nurse nurse = new Nurse("NUR456", "Night", Arrays.asList("ICU", "ER"));
        Administrator admin = new Administrator("ADM789", Arrays.asList("FullAccess"));
        HospitalSystem system = new HospitalSystem();
        System.out.println(system.admitPatient(patient, doctor));
        System.out.println(patient.getPublicInfo());
        System.out.println(record.isAllergicTo("Peanuts"));
        System.out.println(nurse);
        System.out.println(admin);
    }
}
