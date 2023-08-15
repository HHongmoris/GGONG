package com.a304.ggong.service;

import com.a304.ggong.dto.request.BuyRequest;
import com.a304.ggong.dto.response.AllItemResponse;
import com.a304.ggong.dto.response.BuyListResponse;
import com.a304.ggong.dto.response.BuyResponse;
import com.a304.ggong.entity.Buy;
import com.a304.ggong.entity.Product;
import com.a304.ggong.entity.User;
import com.a304.ggong.repository.BuyRepository;
import com.a304.ggong.repository.PointRepository;
import com.a304.ggong.repository.ProductRepository;
import com.a304.ggong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemsServiceImpl implements ItemsService{
    private final ProductRepository productRepository;
    private final BuyRepository buyRepository;
    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    //모든 상품 조회
    @Override
    public List<AllItemResponse> selectAllItems() {
        return productRepository.findAll().stream()
                .map(AllItemResponse::new)
                .collect(Collectors.toList());
    }

    public BuyResponse insertBuyItem(LocalDateTime now, String email, Long itemId){

        // 일단 product 객체 가져오고
        Product product = productRepository.findById(itemId).get();

        // email로 user 객체 가져오고
        User user = userRepository.findByEmail(email).get();

        // 메소드 호출 시간 가져오고 TimeStamp로 변환
        Timestamp Tnow = Timestamp.valueOf(now);

        // Buy entity에 넣어줘
        BuyRequest buyRequest = new BuyRequest();
        buyRequest.setBuyTime(Tnow);
        buyRequest.setProduct(product);
        buyRequest.setUser(user);

        Buy buy = buyRequest.toEntity();

        buyRepository.save(buy);

        // response 리턴해줘야지
        BuyResponse buyResponse = new BuyResponse(product);
        buyResponse.setBought(true);

        return buyResponse;
    }

//    //상품 구매 - 원하는 상품 선택
//    @Override
//    public Long selectItemById(Long itemId) {
//        //상품 id로 상품 찾아서 반환
//        return productRepository
//                .findById(itemId)
//                .orElseThrow()
//                .getProductNo();
//    }
//
//    //상품 구매 - 구매
//    @Override
//    public BuyResponse buyItem(BuyRequest request) {
//        BuyResponse buyResponse = new BuyResponse(true, request.getProductNo());
//        return buyResponse;
//    }

    //구매내역 조회
    @Override
    public List<BuyListResponse> selectAllBuyList(String email) {
        List<BuyListResponse> tmp = new ArrayList<>();

        // email로 userNo 찾기
        Long userNo = userRepository.findByEmail(email).get().getUserNo();

        List<Buy> buyList = buyRepository.findByUser_UserNo(userNo);

        for(int idx = 0; idx < buyList.size(); idx++){
            tmp.add(new BuyListResponse(buyList.get(idx)));
        }

        return tmp;
//        return buyRepository.findAll().stream()
//                .map(BuyListResponse::new)
//                .collect(Collectors.toList());
    }
}
