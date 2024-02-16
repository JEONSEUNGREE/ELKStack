package com.search.elkstack;

import com.search.elkstack.repository.CategoryRepository;
import com.search.elkstack.repository.ProductRepository;
import com.search.elkstack.service.GenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final GenerateService generateService;

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        // 카테고리 생성
        if (categoryRepository.count() > 0) {
            return;
        }
        generateService.generateCategory();

        // 상품 1만개씩 10만개 생성
        if (productRepository.count() > 0) {
            return;
        }

        String[] strings = {
                "LG스탠바이미,LEDTV",
                "삼성_버블_통돌이_세탁기,세탁기",
                "리바트소파,소파",
                "안방스마트조명,스탠드조명",
                "LG광파오븐,오븐",
                "Dolby사운드,사운드바",
                "탈모예방샴푸,샴푸",
                "희안한립스틱,립스틱",
                "이상한토너,토너",
                "크록스,레인부츠",
        };

        Arrays.stream(strings).forEach(item -> {
            generateService.generateProduct(item.split(",")[0], item.split(",")[1], 10000);
        });

    }
}
