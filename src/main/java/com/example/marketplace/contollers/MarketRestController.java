package com.example.marketplace.contollers;

import com.example.marketplace.entities.Market;
import com.example.marketplace.services.IMarketServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
@Tag(name = "Market Management")

public class MarketRestController {

    private final IMarketServices marketServices;

    @Operation(description = "Retrieve all markets")
    @GetMapping("/all")
    List<Market> getAllMarkets(){

        return marketServices.retrieveAllMarkets();
    }

    @Operation (description = "Retrieve market")
    @GetMapping("/get/{id}")
    Market getMarket(@PathVariable("id") Integer idMarket){

        return marketServices.retrieveMarket(idMarket);
    }

    @Operation (description = "Add market")
    @PostMapping("/add")
    Market addMarket(@RequestBody Market m){
        return marketServices.addMarket(m);
    }

    @Operation (description = "Update market")
    @PutMapping("/update")
    Market updateMarket(@RequestBody Market m){
        return marketServices.updateMarket(m);
    }

    @Operation (description = "Delete market")
    @DeleteMapping("/delete/{id}")
    void deleteMarket(@PathVariable("id") Integer idMarket){
        marketServices.removeMarket(idMarket);
    }
}
