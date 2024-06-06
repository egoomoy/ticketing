package com.example.my.ticket.domain;

import com.example.my.ticket.application.port.out.BlackAndWhiteCombineType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class BookingBlackAndWhiteCombiner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combine_no")
    private Long combineNo;

    @Column
    private Long combineValue;

    @Enumerated(EnumType.STRING)
    private BlackAndWhiteCombineType combineType;

    @ManyToOne
    @JoinColumn(name = "group_no")
    private BookingBlackAndWhiteGroup blackAndWhiteGroup;
}