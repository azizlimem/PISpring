package com.example.marketplace.services;

import com.example.marketplace.entities.Market;

import java.util.List;

public interface IMarketServices {
    List<Market> retrieveAllMarkets();

    Market addMarket (Market m);

    Market updateMarket (Market m);

    Market retrieveMarket(Integer idMarket);

    void removeMarket(Integer idMarket);
}
