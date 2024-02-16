package com.search.elkstack.data;


import com.search.elkstack.service.GenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
public class DataController {

    private final GenerateService generateService;
    @GetMapping("/generate/product")
    public String generateProduct(@Param("prodNm") String prodNm, @Param("cateNm") String cateNm, @Param("cnt") int cnt) {

        generateService.generateProduct(prodNm, cateNm, cnt);

        return "success";
    }

//    @GetMapping("/generate/category")
//    public String generateCategory() {
//
//        generateService.generateCategory();
//
//        return "success";
//    }
}
