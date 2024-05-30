package com.zerock.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default //별도의 값을 지정하지 않으면 기본값으로 1을 쓰겠다.
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type; // 검색의 종류 t, c, w, tc, tw, twc

    private String keyword; // 실제 검색을 필드


    public String[] getTypes(){
        if (type == null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    public Pageable getPageable(String...props){ // props 가변인자
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending()); //this.page -1 인 이유는 인덱스를 위해 인덱스는 0부터 시작. // this.size 한 페이지에 보여줄 게시글의 갯수
    }

    //생성된 링크(URL)을 저장하는 필드
    private String link;

    public String getLink(){
        if (link == null){
            StringBuilder builder = new StringBuilder();

            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if (type != null && type.length() > 0){
                builder.append("&type=" + type);
            }
            if (keyword != null) {
                try{
                    builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
                }catch (UnsupportedEncodingException e){}
            }
            link = builder.toString();
        }
        return link;
    }
}


