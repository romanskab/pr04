package com.oktenweb.pr04.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myFile_id;
    private String name;
    private String place;
    private long size;
    @Enumerated(EnumType.STRING)
    private Type type;
    private org.joda.time.LocalDateTime timeStart;
    private LocalDateTime timeFinish;


}
