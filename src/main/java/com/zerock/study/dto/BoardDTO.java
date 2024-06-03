package com.zerock.study.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private Long bno;
    @NotEmpty
    @Size(min = 2, max = 100)
    private String title;
    @Size(min = 2, max = 10000)
    @NotEmpty
    private String content;
    @Size(min = 1, max = 100)
    @NotEmpty
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
