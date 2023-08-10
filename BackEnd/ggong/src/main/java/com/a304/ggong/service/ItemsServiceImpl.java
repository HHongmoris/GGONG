package com.a304.ggong.service;

import com.a304.ggong.dto.request.BuyRequest;
import com.a304.ggong.dto.response.AllItemResponse;
import com.a304.ggong.dto.response.BuyListResponse;
import com.a304.ggong.dto.response.BuyResponse;
import com.a304.ggong.repository.BuyRepository;
import com.a304.ggong.repository.PointRepository;
import com.a304.ggong.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemsServiceImpl implements ItemsService{
    private final ProductRepository productRepository;
    private final BuyRepository buyRepository;
    private final PointRepository pointRepository;

    //모든 상품 조회
    @Override
    public List<AllItemResponse> selectAllItems() {
        return productRepository.findAll().stream()
                .map(AllItemResponse::new)
                .collect(Collectors.toList());
    }

    //상품 구매 - 원하는 상품 선택
    @Override
    public Long selectItemById(BuyRequest request) {
        //상품 id로 상품 찾아서 반환
        return productRepository
                .findById(request.getProductNo())
                .orElseThrow()
                .getProductNo();
    }

    //상품 구매 - 구매
    @Override
    public BuyResponse buyItem(BuyRequest request) {
        BuyResponse buyResponse = new BuyResponse(true, request.getProductNo());
        return buyResponse;
    }

    //구매내역 조회
    @Override
    public List<BuyListResponse> selectAllBuyList() {
        return buyRepository.findAll().stream()
                .map(BuyListResponse::new)
                .collect(Collectors.toList());
    }
}
