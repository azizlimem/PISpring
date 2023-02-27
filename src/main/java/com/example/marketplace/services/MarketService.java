package com.example.marketplace.services;

import com.example.marketplace.entities.Market;
import com.example.marketplace.repository.IMarketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MarketService implements IMarketServices{
    private final IMarketRepository marketRepository;

    @Override
    public List<Market> retrieveAllMarkets() {
        List<Market> markets = new ArrayList<>();
        marketRepository.findAll().forEach(markets::add);
        return markets;
    }

    @Override
    public Market addMarket(Market m) {

        return marketRepository.save(m);
    }

    @Override
    public Market updateMarket(Market m) {

        return marketRepository.save(m);
    }

    @Override
    public Market retrieveMarket(Integer idMarket) {
        return marketRepository.findById(idMarket).orElse(null);
    }
    @Override
    public void removeMarket(Integer idMarket) {
        marketRepository.deleteById(idMarket);
    }
}
