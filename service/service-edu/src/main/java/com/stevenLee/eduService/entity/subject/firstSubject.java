package com.stevenLee.eduService.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class firstSubject {
    private String id;
    private String title;
    private List<secondSubject> children = new ArrayList<>();
}
