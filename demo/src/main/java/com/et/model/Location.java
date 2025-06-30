package com.et.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity
@Data
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String address;

    @Column(columnDefinition = "geometry(Point,4326)")
    @JsonIgnore
    private Point geom;
    // 添加经度和纬度的便捷访问方法
    @Transient
    public Double getLatitude() {
        return geom != null ? geom.getY() : null;
    }

    @Transient
    public Double getLongitude() {
        return geom != null ? geom.getX() : null;
    }
}