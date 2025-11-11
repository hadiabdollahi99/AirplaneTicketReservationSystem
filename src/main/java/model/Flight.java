package model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Flight extends BaseEntity<Long> implements Serializable {
    private String flightNumber;
    private Double price;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private Integer totalSeats;
    private Integer availableSeats;
    private String aircraftType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_airline")
    private Airline airline;


    public String getFormattedDepartureTime() {
        if (departureTime == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return departureTime.format(formatter);
    }

    public String getFormattedArrivalTime() {
        if (arrivalTime == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return arrivalTime.format(formatter);
    }

    public Long getDurationInMinutes() {
        if (departureTime == null || arrivalTime == null) {
            return 0L;
        }
        return java.time.Duration.between(departureTime, arrivalTime).toMinutes();
    }

    public String getFormattedDuration() {
        long minutes = getDurationInMinutes();
        if (minutes == 0) {
            return "N/A";
        }
        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;

        if (hours > 0 && remainingMinutes > 0) {
            return hours + "h " + remainingMinutes + "m";
        } else if (hours > 0) {
            return hours + "h";
        } else {
            return remainingMinutes + "m";
        }
    }
}
