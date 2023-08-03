package com.a304.ggong.service;

import com.a304.ggong.dto.request.BuyRequest;
import com.a304.ggong.dto.response.AllItemResponse;
import com.a304.ggong.dto.response.BuyListResponse;
import com.a304.ggong.dto.response.BuyResponse;
import com.a304.ggong.entity.Product;

import java.util.List;

public interface ItemsService {
    //모든 상품 조회
    List<AllItemResponse> selectAllItems();
    //상품 구매 - 원하는 상품 선택
    Long selectItemById(BuyRequest request);
    //상품 구매 - 구매
    Boolean buyItem(BuyResponse response);

    //구매 내역 조회
    List<BuyListResponse> selectAllBuyList();
}
