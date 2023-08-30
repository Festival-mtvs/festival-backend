package com.midnights.demo.common.page;

import org.springframework.data.domain.Page;

public class Pagination {

    public static PagingButtonInfo getPagingButtonInfo(Page page) {

        int currentPage = page.getNumber() + 1;
        int startPage;
        int endPage;
        int defaultButtonCount = 3;

        startPage = (int)(Math.ceil((double) currentPage /defaultButtonCount) - 1) * defaultButtonCount + 1;
        endPage = startPage + defaultButtonCount - 1;

        if(page.getTotalPages() < endPage) {
            endPage = page.getTotalPages();
        }

        if(page.getTotalPages() == 0 && endPage == 0) {
            endPage = startPage;
        }

        return new PagingButtonInfo(currentPage, startPage, endPage);
    }
}
