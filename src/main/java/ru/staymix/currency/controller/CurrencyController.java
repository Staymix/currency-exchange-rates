package ru.staymix.currency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.staymix.currency.service.CbrService;

import java.math.BigDecimal;

@RestController
@RequestMapping("currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CbrService service;

    @GetMapping("/rate/{code}")
    public BigDecimal currencyRate(@PathVariable String code) {
        return service.requestByCurrencyCode(code);
    }
}
