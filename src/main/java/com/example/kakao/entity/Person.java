package com.example.kakao.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@ToString
public class Person {

    @Id
    private Long id;

    @NotNull
    private String name;
}
