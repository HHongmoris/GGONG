package com.a304.ggong.controller;

import com.a304.ggong.dto.request.BuyRequest;
import com.a304.ggong.dto.response.AllItemResponse;
import com.a304.ggong.dto.response.BuyListResponse;
import com.a304.ggong.dto.response.BuyResponse;
import com.a304.ggong.entity.Product;
import com.a304.ggong.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemsController {

    @Autowired
    private final ItemsService itemsService;

    //모든 상품 조회(GET)
    @GetMapping
    public ResponseEntity<List<AllItemResponse>> list(){
        List<AllItemResponse> itemList = itemsService.selectAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(itemList);
    }

    //상품구매(POST)
    @PostMapping("/{items}")
    public ResponseEntity<Long> buyItem(@PathVariable("items") BuyRequest request){
        Long selectItem = itemsService.selectItemById(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(selectItem);
    }

    //상품구매(GET)
    //이거 대대적으로 손볼 필요가 있음
    //BuyResponse에서 item 정보까지 같이 넘겨야 pathvariable 설정 가능할듯
    @GetMapping("/{items}")
    public ResponseEntity<BuyResponse> bought(BuyRequest request){
        BuyResponse boughtItem = itemsService.buyItem(request);
        return ResponseEntity.status(HttpStatus.OK).body(boughtItem);
    }

    //구매내역 조회(GET)
    @GetMapping("/buy")
    public ResponseEntity<List<BuyListResponse>> orderList(){
        List<BuyListResponse> boughtList = itemsService.selectAllBuyList();
        return ResponseEntity.status(HttpStatus.OK).body(boughtList);
    }
}
