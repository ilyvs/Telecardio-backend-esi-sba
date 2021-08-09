package com.bezkoder.springjwt.models;

import javax.persistence.*;

@Entity
@Table(	name = "SocialNum",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id"),
                @UniqueConstraint(columnNames = "sin")
        })

public class SocialNum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long sin;

    public SocialNum() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSin(){return sin;}

    public void setSin(Long sin){ this.sin=sin; }
}
