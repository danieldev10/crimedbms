package dev.achonma.crimedbms.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "criminal_record")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CriminalRecord extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String case_number;

    @Column
    private Date date_of_offense;

    @Column
    private Date date_of_arrest;

    @Column
    private String charges;

    @Column
    private String location_of_offence;

    @Column
    private String court;

    @Column
    private Date court_date;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String photo;

    @ManyToOne
    @JoinColumn(name = "crime_id")
    private Crime crime;

    @Column
    private Date punishment_start_date;

    @Column
    private String punishment_period;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "prison_id")
    private Prison prison;

    public CriminalRecord() {
    }

    public CriminalRecord(String case_number, Date date_of_offense, Date date_of_arrest, String charges,
            String location_of_offence, String court, Date court_date, String first_name, String last_name,
            String photo, Crime crime, Date punishment_start_date, String punishment_period, State state,
            Prison prison) {
        this.case_number = case_number;
        this.date_of_offense = date_of_offense;
        this.date_of_arrest = date_of_arrest;
        this.charges = charges;
        this.location_of_offence = location_of_offence;
        this.court = court;
        this.court_date = court_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo = photo;
        this.crime = crime;
        this.punishment_start_date = punishment_start_date;
        this.punishment_period = punishment_period;
        this.state = state;
        this.prison = prison;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public Date getDate_of_offense() {
        return date_of_offense;
    }

    public void setDate_of_offense(Date date_of_offense) {
        this.date_of_offense = date_of_offense;
    }

    public Date getDate_of_arrest() {
        return date_of_arrest;
    }

    public void setDate_of_arrest(Date date_of_arrest) {
        this.date_of_arrest = date_of_arrest;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getLocation_of_offence() {
        return location_of_offence;
    }

    public void setLocation_of_offence(String location_of_offence) {
        this.location_of_offence = location_of_offence;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public Date getCourt_date() {
        return court_date;
    }

    public void setCourt_date(Date court_date) {
        this.court_date = court_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public Date getPunishment_start_date() {
        return punishment_start_date;
    }

    public void setPunishment_start_date(Date punishment_start_date) {
        this.punishment_start_date = punishment_start_date;
    }

    public String getPunishment_period() {
        return punishment_period;
    }

    public void setPunishment_period(String punishment_period) {
        this.punishment_period = punishment_period;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    @Override
    public String toString() {
        return "CriminalRecord [id=" + id + ", case_number=" + case_number + ", date_of_offense=" + date_of_offense
                + ", date_of_arrest=" + date_of_arrest + ", charges=" + charges + ", location_of_offence="
                + location_of_offence + ", court=" + court + ", court_date=" + court_date + ", first_name=" + first_name
                + ", last_name=" + last_name + ", photo=" + photo + ", crime=" + crime + ", punishment_start_date="
                + punishment_start_date + ", punishment_period=" + punishment_period + ", state=" + state + ", prison="
                + prison + "]";
    }

}
