package com.example.marketplace.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class log {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idLog;
    @Temporal(TemporalType.DATE)
    private Date dateLog;

    private String charity;
}
