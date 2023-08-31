package com.midnights.demo.aggregate.dto.admin;

import com.midnights.demo.aggregate.entity.Festival;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class ResponseReadFestival {
    private List<Festival> festivals;
    private int pageNumber;
    private int totalPages;

    public ResponseReadFestival(Page<Festival> page) {
        this.festivals = page.getContent();
        this.pageNumber = page.getNumber();
        this.totalPages = page.getTotalPages();
    }
}
