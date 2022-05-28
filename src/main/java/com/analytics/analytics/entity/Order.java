package com.analytics.analytics.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "orders")
public class Order {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column
    private String trackingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", trackingId='" + trackingId + '\'' +
                '}';
    }
}
