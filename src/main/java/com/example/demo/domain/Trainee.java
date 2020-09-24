package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainees")
public class Trainee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Boolean isGrouped;
    private String office;
    private String email;
    private String zoomId;
    private Long groupId;
}
