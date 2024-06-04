package com.example.my.ticket.adapter.out.persistence;

import com.example.my.ticket.domain.BlackAndWhiteCombineOperator;
import com.example.my.ticket.domain.BlackAndWhiteType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class BookingBlackAndWhiteGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_no")
    private Long groupNo;

    @Enumerated(EnumType.STRING)
    private BlackAndWhiteType blackAndWhiteType;

    @Enumerated(EnumType.STRING)
    private BlackAndWhiteCombineOperator combineOperator;

    @ManyToOne
    @JoinColumn(name = "event_no")
    private Event event;
}
