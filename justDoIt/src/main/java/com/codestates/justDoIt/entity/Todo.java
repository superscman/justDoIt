package com.codestates.justDoIt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "todo_order", nullable = false)
    private long order;

    @Column(nullable = false)
    private boolean completed;

}
