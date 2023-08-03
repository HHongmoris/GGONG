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

    @Override
    public List<AllItemResponse> selectAllItems() {
        return productRepository.findAll().stream()
                .map(AllItemResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long selectItemById(BuyRequest request) {
        return productRepository
                .findById(request.getProductNo())
                .orElseThrow()
                .getProductNo();
    }

    @Override
    public Boolean buyItem(BuyResponse response) {
        return null;
    }

    @Override
    public List<BuyListResponse> selectAllBuyList() {
        return buyRepository.findAll().stream()
                .map(BuyListResponse::new)
                .collect(Collectors.toList());
    }
}
