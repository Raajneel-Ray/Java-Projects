import java.io.Serializable; // Allows the class to be serialized for saving and loading appointments
import java.time.LocalDate;

public class Appointment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String appointmentType; // vet visit, vaccination, grooming
    private LocalDate date;
    private String notes;// optional

    public Appointment(String appointmentType, LocalDate date, String notes) {
        this.appointmentType = appointmentType;
        this.date = date;
        this.notes = notes;
    }

    public String getAppointmentType() { return this.appointmentType; }
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
                "\nAppointment Type : " + this.appointmentType +
                "\nNotes : " + this.notes;
    }
}