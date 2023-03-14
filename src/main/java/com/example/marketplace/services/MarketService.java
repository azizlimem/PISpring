package com.example.marketplace.services;

import com.example.marketplace.entities.*;
import com.example.marketplace.enumerations.ERole;
import com.example.marketplace.enumerations.PackType;
import com.example.marketplace.repository.IContractRepository;
import com.example.marketplace.repository.IMarketRepository;
import com.example.marketplace.repository.IRoleRepository;
import com.example.marketplace.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MarketService implements IMarketServices{
    private final IMarketRepository marketRepository;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IContractRepository contractRepository;
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
@Override
    public String addAndAsignToUser(Market market , Integer idUser){
        User u = userRepository.findById(idUser).orElse(null);
    assert u != null;

            market.setUser(u);
            marketRepository.save(market);
            u.getRoles().clear();
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            u.getRoles().add(modRole);
            userRepository.save(u);
            return null;
    }
//@Scheduled(cron = "*/10 * * * * *")
    /*public void updateMarketContract(){
        List<Market> markets = new ArrayList<>();
        marketRepository.findAll().forEach(markets::add);
        for (Market market : markets) {
            double somme = 0;
            for (Product p : market.getCatalogue().getProducts()) {
                somme = somme + (p.getQtySold() * p.getPrice());
            }
            if (somme > 3000) {
                market.getContract().setPackType(PackType.SILVER);
                Integer newPrice = 400 * market.getContract().getNbrMonths();
                Integer discount = market.getContract().getPrice()-newPrice;
                market.getContract().setDiscount(market.getContract().getDiscount()+discount);
                for (Product p : market.getCatalogue().getProducts()) {
                    p.setQtySold(0);
                }
            }
            if (somme > 5000) {
                market.getContract().setPackType(PackType.GOLD);
                Integer newPrice = 300 * market.getContract().getNbrMonths();
                Integer discount = market.getContract().getPrice()-newPrice;
                market.getContract().setDiscount(market.getContract().getDiscount()+discount);
                for (Product p : market.getCatalogue().getProducts()) {
                    p.setQtySold(0);
                }
            }

        }

    }*/



}
