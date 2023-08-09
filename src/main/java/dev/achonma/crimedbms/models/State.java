package dev.achonma.crimedbms.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String capital;
    @Column
    private String state_code;

    public State() {
    }

    public State(String name, String capital, String state_code, List<Prison> prisons) {
        this.name = name;
        this.capital = capital;
        this.state_code = state_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    @Override
    public String toString() {
        return "State [id=" + id + ", name=" + name + ", capital=" + capital + ", state_code=" + state_code + "]";
    }
}
