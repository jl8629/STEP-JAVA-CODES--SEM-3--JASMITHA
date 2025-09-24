class MedicalStaff {
    protected String name;
    protected int id;

    MedicalStaff(String name, int id) {
        this.name = name;
        this.id = id;
    }

    void scheduleShift() {
        System.out.println(name + " (ID: " + id + ") shift scheduled.");
    }

    void accessIDCard() {
        System.out.println(name + " (ID: " + id + ") accessed ID card.");
    }

    void processPayroll() {
        System.out.println(name + " (ID: " + id + ") payroll processed.");
    }
}

class Doctor extends MedicalStaff {
    Doctor(String name, int id) {
        super(name, id);
    }

    void diagnosePatient() {
        System.out.println(name + " is diagnosing patients.");
    }

    void prescribeMedicine() {
        System.out.println(name + " is prescribing medicine.");
    }

    void performSurgery() {
        System.out.println(name + " is performing surgery.");
    }
}

class Nurse extends MedicalStaff {
    Nurse(String name, int id) {
        super(name, id);
    }

    void administerMedicine() {
        System.out.println(name + " is administering medicine.");
    }

    void monitorPatient() {
        System.out.println(name + " is monitoring patients.");
    }

    void assistProcedure() {
        System.out.println(name + " is assisting in procedures.");
    }
}

class Technician extends MedicalStaff {
    Technician(String name, int id) {
        super(name, id);
    }

    void operateEquipment() {
        System.out.println(name + " is operating equipment.");
    }

    void runTests() {
        System.out.println(name + " is running tests.");
    }

    void maintainInstruments() {
        System.out.println(name + " is maintaining instruments.");
    }
}

class Administrator extends MedicalStaff {
    Administrator(String name, int id) {
        super(name, id);
    }

    void scheduleAppointments() {
        System.out.println(name + " is scheduling appointments.");
    }

    void manageRecords() {
        System.out.println(name + " is managing records.");
    }
}

public class HospitalManagement {
    public static void main(String[] args) {
        MedicalStaff[] staff = {
            new Doctor("Dr. Alice", 101),
            new Nurse("Nurse Bob", 102),
            new Technician("Tech Carol", 103),
            new Administrator("Admin David", 104)
        };

        for(MedicalStaff m : staff) {
            m.scheduleShift();
            m.accessIDCard();
            m.processPayroll();
            System.out.println();
        }

        Doctor d = (Doctor) staff[0];
        d.diagnosePatient();
        d.prescribeMedicine();
        d.performSurgery();

        Nurse n = (Nurse) staff[1];
        n.administerMedicine();
        n.monitorPatient();
        n.assistProcedure();

        Technician t = (Technician) staff[2];
        t.operateEquipment();
        t.runTests();
        t.maintainInstruments();

        Administrator a = (Administrator) staff[3];
        a.scheduleAppointments();
        a.manageRecords();
    }
}
