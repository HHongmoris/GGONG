package com.a304.ggong.controller;

import com.a304.ggong.dto.request.BuyRequest;
import com.a304.ggong.dto.response.AllItemResponse;
import com.a304.ggong.dto.response.BuyListResponse;
import com.a304.ggong.dto.response.BuyResponse;
import com.a304.ggong.entity.Product;
import com.a304.ggong.entity.User;
import com.a304.ggong.global.jwt.service.JwtService;
import com.a304.ggong.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemsController {

    @Autowired
    private final ItemsService itemsService;
    @Autowired
    private final JwtService jwtService;

    //모든 상품 조회(GET)
    @GetMapping
    public ResponseEntity<List<AllItemResponse>> list(){
        List<AllItemResponse> itemList = itemsService.selectAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(itemList);
    }

    // 상품 구매(Post)
    @PostMapping("/{itemId}")
    public ResponseEntity<BuyResponse> buyItem(@RequestHeader(required = true, name = "Authorization") String token, @PathVariable("itemId")Long itemId){
        // 먼저 구매 시간 받아와
        LocalDateTime now = LocalDateTime.now();

        //성민
        String email = jwtService.extractEmailTest(token);
        if(email==null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // service의 insertItem메소드를 찾아가
        BuyResponse buyResponse = itemsService.insertBuyItem(now, email, itemId);

        return new ResponseEntity<>(buyResponse,HttpStatus.OK);
    }

//    //상품구매(POST)
//    @PostMapping("/{itemId}")
//    public ResponseEntity<Long> buyItem(@PathVariable("itemId") Long itemId){
//        Long selectItem = itemsService.selectItemById(itemId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(selectItem);
//    }
//
//    //상품구매(GET)
//    //이거 대대적으로 손볼 필요가 있음
//    //BuyResponse에서 item 정보까지 같이 넘겨야 pathvariable 설정 가능할듯
//    @GetMapping("/{items}")
//    public ResponseEntity<BuyResponse> bought(BuyRequest request){
//        BuyResponse boughtItem = itemsService.buyItem(request);
//        return ResponseEntity.status(HttpStatus.OK).body(boughtItem);
//    }

    //구매내역 조회(GET)
    @GetMapping("/buy")
    public ResponseEntity<List<BuyListResponse>> orderList(@RequestHeader(required = true, name = "Authorization") String token){
        String email = jwtService.extractEmailTest(token);
        if(email==null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<BuyListResponse> boughtList = itemsService.selectAllBuyList(email);
        return ResponseEntity.status(HttpStatus.OK).body(boughtList);
    }
}
