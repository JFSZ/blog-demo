package com.example.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChartsVo
 * @Author chenxue
 * @Description 数据展示
 * @Date 2019/7/4 9:12
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChartsVo {
    private List<String> categories;
    private List<Integer> ds;
}
