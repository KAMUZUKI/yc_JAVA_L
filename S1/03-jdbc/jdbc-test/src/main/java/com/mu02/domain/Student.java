package com.mu02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer sid;
    private String name;
    private String deptName;
    private Integer age;
}
