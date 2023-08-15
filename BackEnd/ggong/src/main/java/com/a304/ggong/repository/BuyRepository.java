package com.a304.ggong.repository;

import com.a304.ggong.entity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Long> {

    List<Buy> findByUser_UserNo(Long userNo);
}
