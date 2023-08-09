package dev.achonma.crimedbms.models;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "prison")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Prison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String warden_name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public Prison() {
    }

    public Prison(String name, String warden_name, String address, String phone, String email, State state) {
        this.name = name;
        this.warden_name = warden_name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.state = state;
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

    public String getWarden_name() {
        return warden_name;
    }

    public void setWarden_name(String warden_name) {
        this.warden_name = warden_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Prison [id=" + id + ", name=" + name + ", warden_name=" + warden_name + ", address=" + address
                + ", phone=" + phone + ", email=" + email + ", state=" + state + "]";
    }
}
