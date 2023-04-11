package com.example.marketplace.services;

import com.example.marketplace.entities.*;
import com.example.marketplace.enumerations.ERole;
import com.example.marketplace.enumerations.PackType;
import com.example.marketplace.repository.*;
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
    private final IProductRepo productRepo;
    private final MailerService mailerService;
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
    public void updateMarketContract(){
        List<Market> markets = new ArrayList<>();
        marketRepository.findAll().forEach(markets::add);
        for (Market market : markets) {
            double somme = 0;
            for (Product p : market.getProduct()) {
                somme = somme + (p.getQtySold() * p.getPrice());
                System.out.print(somme);
            }
            if (somme > 5000 && somme<10000) {
                market.getContract().setPackType(PackType.SILVER);
                Integer newPrice = 400 * market.getContract().getNbrMonths();
                Integer discount = market.getContract().getPrice()-newPrice;
                market.getContract().setDiscount(market.getContract().getDiscount()+discount);
                contractRepository.save(market.getContract());
                for (Product p : market.getProduct()) {
                    p.setQtySold(0);
                    productRepo.save(p);
                }
            }
            if (somme >= 10000) {
                market.getContract().setPackType(PackType.GOLD);
                Integer newPrice = 300 * market.getContract().getNbrMonths();
                Integer discount = market.getContract().getPrice()-newPrice;
                market.getContract().setDiscount(market.getContract().getDiscount()+discount);
                contractRepository.save(market.getContract());
                for (Product p : market.getProduct()) {
                    p.setQtySold(0);
                    productRepo.save(p);
                }
            }

        }

    }


   // @Scheduled(cron = "*/40 * * * * *")
    public void bestMarket(){
        List<Contract> contracts = new ArrayList<>();
        contractRepository.maxDiscount().forEach(contracts::add);

        for(Contract c : contracts){
            User u = c.getMarket().getUser();
            mailerService.sendEmail(u.getEmail(),"Best Market This Month ","Congratulations Mr "+ u.getLastName() + " !\nYour Market has won 1000 DT Discount for being the Best Product Seller This month !");
            c.setDiscount(c.getDiscount()+1000);
            contractRepository.save(c);

        }

    }



}
