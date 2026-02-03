import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pet implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String petId;
    private String petName;
    private String petSpecies;
    private int age;
    private String petOwnerName;
    private String contactInfo;
    private LocalDate registrationDate;
    private List<Appointment> appointments;

    public Pet(String petID, String petName, String petSpecies, int age, String petOwnerName, String contactInfo) {
        this.petId = petID;
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.age = age;
        this.petOwnerName = petOwnerName;
        this.contactInfo = contactInfo;
        this.registrationDate = LocalDate.now();
        this.appointments = new ArrayList<>();
    }
    // Getter
    public String getPetID() {
        return petId;
    }
    public String getPetName() {
        return petName;
    }
    public String getPetSpecies() {
        return petSpecies;
    }
    public int getAge() {
        return age;
    }
    public String getOwnerName() {
        return petOwnerName;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    // Setter
    public void addAppointment(Appointment app) {
        this.appointments.add(app);
    }

    @Override
    public String toString() {
        return "Pet ID: " + petId +
            "\nName: " + petName +
            "\nSpecies: " + petSpecies +
            "\nAge: " + age +
            "\nOwner: " + petOwnerName +
            "\nContact: " + contactInfo +
            "\nRegistered On: " + registrationDate +
            "\nAppointments Count: " + appointments.size();
    }
}