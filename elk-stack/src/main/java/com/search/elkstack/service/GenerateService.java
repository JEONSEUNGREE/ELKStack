package com.search.elkstack.service;


import com.search.elkstack.entity.Category;
import com.search.elkstack.entity.Product;
import com.search.elkstack.entity.ProductCategory;
import com.search.elkstack.repository.CategoryRepository;
import com.search.elkstack.repository.ProductCategoryRepository;
import com.search.elkstack.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class GenerateService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public boolean generateProduct(String prodNm,String cateNm, int cnt) {

        List<Product> tmpProduct = new ArrayList<>();

        List<ProductCategory> tmpProductCategory = new ArrayList<>();

        Category cateInfo = categoryRepository.findByCategoryName(cateNm);

        IntConsumer intConsumer = num -> {

            Product product = Product.builder()
                    .productName(prodNm + "_" + num)
                    .description(cateNm + "_" + num)
                    .imageUrl(String.valueOf(UUID.randomUUID()))
                    .price(new Random().nextInt(10000000) + 1)
                    .build();
            tmpProduct.add(product);
            tmpProductCategory.add(new ProductCategory(null, product, cateInfo));
        };

        IntStream.range(0, cnt).forEach(
                intConsumer
        );

        productRepository.saveAll(tmpProduct);
        productCategoryRepository.saveAll(tmpProductCategory);

        return true;
    }

    public void generateCategory() {

        List<Category> categories1depth = new ArrayList<>();
        List<Category> categories2depth = new ArrayList<>();
        List<Category> categories3depth = new ArrayList<>();

        // 1depth
        categories1depth.add(new Category(null, "의류", null, null));
        categories1depth.add(new Category(null, "신발", null, null));
        categories1depth.add(new Category(null, "가방및지갑", null, null));
        categories1depth.add(new Category(null, "백팩", null, null));
        categories1depth.add(new Category(null, "뷰티및화장품", null, null));
        categories1depth.add(new Category(null, "가전제품", null, null));
        categories1depth.add(new Category(null, "가구및인테리어", null, null));

        // 2depth
        categories2depth.add(new Category(null, "상의", categories1depth.stream().filter(item -> "의류".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "하의", categories1depth.stream().filter(item -> "의류".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "아우터", categories1depth.stream().filter(item -> "의류".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories2depth.add(new Category(null, "스니커즈", categories1depth.stream().filter(item -> "신발".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "부츠", categories1depth.stream().filter(item -> "신발".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "샌들", categories1depth.stream().filter(item -> "신발".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories2depth.add(new Category(null, "핸드백", categories1depth.stream().filter(item -> "가방및지갑".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "백팩", categories1depth.stream().filter(item -> "가방및지갑".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "지갑", categories1depth.stream().filter(item -> "가방및지갑".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories2depth.add(new Category(null, "스킨케어", categories1depth.stream().filter(item -> "뷰티및화장품".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "메이크업", categories1depth.stream().filter(item -> "뷰티및화장품".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "헤어케어", categories1depth.stream().filter(item -> "뷰티및화장품".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories2depth.add(new Category(null, "TV및홈시어터", categories1depth.stream().filter(item -> "가전제품".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "냉장고및세탁기", categories1depth.stream().filter(item -> "가전제품".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "주방가전", categories1depth.stream().filter(item -> "가전제품".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories2depth.add(new Category(null, "소파및의자", categories1depth.stream().filter(item -> "가구및인테리어".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "침대및매트리스", categories1depth.stream().filter(item -> "가구및인테리어".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories2depth.add(new Category(null, "조명및전등", categories1depth.stream().filter(item -> "가구및인테리어".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        // 3depth
        categories3depth.add(new Category(null, "티셔츠", categories2depth.stream().filter(item -> "상의".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "셔츠", categories2depth.stream().filter(item -> "상의".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "니트웨어", categories2depth.stream().filter(item -> "상의".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "청바지", categories2depth.stream().filter(item -> "하의".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "슬랙스", categories2depth.stream().filter(item -> "하의".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "반바지", categories2depth.stream().filter(item -> "하의".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "재킷", categories2depth.stream().filter(item -> "아우터".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "코트", categories2depth.stream().filter(item -> "아우터".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "가디건", categories2depth.stream().filter(item -> "아우터".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "남성용", categories2depth.stream().filter(item -> "스니커즈".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "남성용", categories2depth.stream().filter(item -> "스니커즈".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "아동용", categories2depth.stream().filter(item -> "스니커즈".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "워커부츠", categories2depth.stream().filter(item -> "부츠".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "레인부츠", categories2depth.stream().filter(item -> "부츠".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "스노우부츠", categories2depth.stream().filter(item -> "부츠".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "플립플랍", categories2depth.stream().filter(item -> "샌들".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "슬라이드샌들", categories2depth.stream().filter(item -> "샌들".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "웨지샌들", categories2depth.stream().filter(item -> "샌들".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "숄더백", categories2depth.stream().filter(item -> "핸드백".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "크로스백", categories2depth.stream().filter(item -> "핸드백".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "토트백", categories2depth.stream().filter(item -> "핸드백".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "백팩", categories2depth.stream().filter(item -> "백팩".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "리그백", categories2depth.stream().filter(item -> "백팩".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "힙색", categories2depth.stream().filter(item -> "백팩".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "남성지갑", categories2depth.stream().filter(item -> "지갑".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "여성지갑", categories2depth.stream().filter(item -> "지갑".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "카드홀더", categories2depth.stream().filter(item -> "지갑".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "클렌징", categories2depth.stream().filter(item -> "스킨케어".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "토너", categories2depth.stream().filter(item -> "스킨케어".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "에센스", categories2depth.stream().filter(item -> "스킨케어".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "파운데이션", categories2depth.stream().filter(item -> "메이크업".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "립스틱", categories2depth.stream().filter(item -> "메이크업".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "아이섀도우", categories2depth.stream().filter(item -> "메이크업".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "샴푸", categories2depth.stream().filter(item -> "헤어케어".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "컨디셔너", categories2depth.stream().filter(item -> "헤어케어".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "헤어마스크", categories2depth.stream().filter(item -> "헤어케어".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "LEDTV", categories2depth.stream().filter(item -> "TV및홈시어터".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "사운드바", categories2depth.stream().filter(item -> "TV및홈시어터".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "홈시어터시스템", categories2depth.stream().filter(item -> "TV및홈시어터".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "냉장고", categories2depth.stream().filter(item -> "냉장고및세탁기".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "세탁기", categories2depth.stream().filter(item -> "냉장고및세탁기".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "건조기", categories2depth.stream().filter(item -> "냉장고및세탁기".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "전자레인지", categories2depth.stream().filter(item -> "주방가전".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "오븐", categories2depth.stream().filter(item -> "주방가전".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "주전자", categories2depth.stream().filter(item -> "주방가전".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "소파", categories2depth.stream().filter(item -> "소파및의자".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "의자", categories2depth.stream().filter(item -> "소파및의자".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "리클라이너", categories2depth.stream().filter(item -> "소파및의자".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "침대프레임", categories2depth.stream().filter(item -> "침대및매트리스".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "매트리스", categories2depth.stream().filter(item -> "침대및매트리스".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "베개및이불", categories2depth.stream().filter(item -> "침대및매트리스".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categories3depth.add(new Category(null, "천장조명", categories2depth.stream().filter(item -> "조명및전등".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "스탠드조명", categories2depth.stream().filter(item -> "조명및전등".equals(item.getCategoryName())).findAny().orElseThrow(), null));
        categories3depth.add(new Category(null, "벽조명", categories2depth.stream().filter(item -> "조명및전등".equals(item.getCategoryName())).findAny().orElseThrow(), null));

        categoryRepository.saveAll(categories1depth);
        categoryRepository.saveAll(categories2depth);
        categoryRepository.saveAll(categories3depth);
    }

}
