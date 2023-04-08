package com.example.marketplace.services;

import com.example.marketplace.entities.Contract;
import com.example.marketplace.entities.Market;
import com.example.marketplace.enumerations.PackType;
import com.example.marketplace.repository.IContractRepository;
import com.example.marketplace.repository.IMarketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ContractService implements IContractServices {

    private final IContractRepository contractRepository;
    private final IMarketRepository marketRepository;

    @Override
    public List<Contract> retrieveAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        contractRepository.findAll().forEach(contracts::add);
        return contracts;
    }

    @Override
    public Contract addContract(Contract c , Integer idMarket ) {
        Market market = marketRepository.findById(idMarket).orElse(null);
        c.setMarket(market);
        LocalDateTime d = LocalDateTime.now();
        c.setDateDebutContract(d);
        assert c.getNbrMonths() > 0;
        c.setDateFinContract(d.plusMonths(c.getNbrMonths()));
        c.setPackType(PackType.BRONZE);
        c.setPaid(false);
        Integer priceMonth = 500;
        if (c.getNbrMonths()<6) {
            c.setPrice(c.getNbrMonths() * priceMonth);
        }
        else if (c.getNbrMonths() >= 6 && c.getNbrMonths() <12){
            c.setPrice((c.getNbrMonths() * priceMonth)-200);
        }
        else
            c.setPrice((c.getNbrMonths() * priceMonth)-400);

        return contractRepository.save(c);
    }

    @Override
    public String updateContract(Contract c ,Integer nbrMonths) {
        LocalDateTime d =LocalDateTime.now();
        if (c.getDateFinContract().isBefore(d)) {
            if (c.getPaid()) {
                switch (c.getPackType()) {
                    case BRONZE:
                        c.setPrice(500 * nbrMonths);
                        break;
                    case GOLD:
                        c.setPrice(300 * nbrMonths - c.getDiscount());
                        break;
                    case SILVER:
                        c.setPrice(400 * nbrMonths - c.getDiscount());
                        break;

                }
                c.setNbrMonths(nbrMonths);
                c.setDateDebutContract(d);
                c.setDateFinContract(d.plusMonths(nbrMonths));
                contractRepository.save(c);
                return "Contract Updated Successfully ! ";
            } else return "Please Finalise Paying Your Previous Contract So You Can Renew it ! ";
        } else return "Your Contract Is not Finished Yet ! ";
    }

    @Override
    public Contract retrieveContract(Integer idContract) {
        return contractRepository.findById(idContract).orElse(null);
    }
    @Override
    public void removeContract(Integer idContract) {
        contractRepository.deleteById(idContract);
    }

}
