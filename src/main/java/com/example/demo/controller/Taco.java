package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import lombok.Data;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@RestController
@RequestMapping("/")
public class Taco {
    private JdbcTemplate jdbc;
    private String name;
    private List<String> ingredients;


    @GetMapping("ha")
    public String main() {

        System.out.println("美丽人生");
        return "BeautifulLife";
    }

    @GetMapping("wu")
    public String one() {
        System.out.println("悲惨世界");
        return "Miserables";
    }

    @GetMapping("word")
    public String getOne(String str) {
        if (StringUtils.isEmpty(str))
            str = "可爱的人人人都爱但他不可爱时还愿意发现他的可爱";
        List<Word> words = WordSegmenter.seg(str);
        System.out.println(words);
        List<Word> words1 = WordSegmenter.segWithStopWords(str);
        String result = "words:".concat(words.toString()).concat("\n").concat("words1:").concat(words1.toString());
        return result;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(rs.getString("id"), rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }

}