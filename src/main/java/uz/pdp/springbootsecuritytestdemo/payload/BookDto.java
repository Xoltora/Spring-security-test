package uz.pdp.springbootsecuritytestdemo.payload;

import lombok.Data;

@Data
public class BookDto {
    private Integer id;
    private String name;
    private String author;
}
