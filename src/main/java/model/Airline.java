package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Airline extends BaseEntity<Long> implements Serializable {
    private String name;
    private String email;
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "airline", cascade = CascadeType.PERSIST)
    private List<Flight> flights = new ArrayList<>();


}