import java.io.Serializable; // Allows the class to be serialized for saving and loading appointments
import java.time.LocalDate;

public class Appointment implements Serializable {
    private String appointType; // vet visit, vaccination, grooming
    private LocalDate date;
    private String notes;// optional

    public Appointment(String appointType, String notes) {
        this.appointType = appointType;
        this.date = LocalDate.now();
        this.notes = notes;
    }

    public String getAppointmentType() { return this.appointType; }
    public LocalDate getDate() {
        return this.date;
    }
    public String getNotes() {
        return this.notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Date : " + this.date +
                "\nAppointment Type : " + this.appointType +
                "\nNotes : " + this.notes;
    }
}