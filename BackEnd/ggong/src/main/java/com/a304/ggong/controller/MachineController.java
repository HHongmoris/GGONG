package com.a304.ggong.controller;

import com.a304.ggong.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/machines")

public class MachineController {
    private final MachineService machineService;
}
