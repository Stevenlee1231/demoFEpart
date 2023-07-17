package com.stevenLee.eduService.entity.chapter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class chapterVo {
    private String id;
    private String title;
    private List<videoVo> children = new ArrayList<>();
}
