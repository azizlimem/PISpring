//package com.example.marketplace.BatchRahil;
//
//import com.example.marketplace.entities.Charity;
//import com.example.marketplace.repository.ICharityRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//
//@Slf4j
//public class ProjectProcessor implements ItemProcessor<Charity,Charity> {
//
//    @Override
//    public Charity process(Charity charity) {
//        log.info("Start Batch Item Processor");
//
//               // for(Charity charity:charities) {
//                    charity.setScore(charity.getNbre());
//                    if (charity.getMaladie() && charity.getCasGrave()) {
//                        charity.setScore(charity.getScore() + 100);
//                    } else if (charity.getMaladie() && charity.getCasGrave() == false) {
//                        charity.setScore(charity.getScore() + 30);
//
//                    } else if (charity.getMaladie() == false && charity.getCasGrave()) {
//                        charity.setScore(charity.getScore() + 70);
//
//                    }
//        log.info("Charity Score of "+charity.getSosName()+" is set for :"+charity.getScore());
//        return charity;
//    }
//
//}
