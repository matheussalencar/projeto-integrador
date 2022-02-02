package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.BatchDto;
import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundService {

    @Autowired
    private InboundRepository inboundRepository;

    @Autowired
    private ProductAnnouncementService productAnnouncementService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private BatchService batchService;

    public List<Batch> create(InboundDto inboundDto) {
        try {
            Section s = sectionService.get(inboundDto.getSectionId());

            List<Batch> batchList = new ArrayList<>();

            for (BatchDto batchDto : inboundDto.getBatchDtoList()) {
                ProductAnnouncement pa = productAnnouncementService.get(batchDto.getProductId());
                checkTypeMatch(pa.getProduct().getProductType(), s.getType());
                Batch batch = BatchDto.convert(batchDto, pa, batchDto.getInitialQuantity());
                batchList.add(batch);
            }

            Inbound inbound = InboundDto.convert(inboundDto, batchList, s);

            inbound.getBatchList().stream().forEach(batch -> batch.setInbound(inbound));

            inboundRepository.save(inbound);
            return inbound.getBatchList();

        }catch (RuntimeException e){
            throw new BusinessException(e.getMessage());
        }
    }

    public Inbound get(Long id){
       try {
        Inbound inbound = inboundRepository.findById(id).orElse(null);
        return inbound;
       } catch (RuntimeException e) {
           throw new NotFoundException("Inbound order " + id + " não encontrado na base de dados.");
       }
    }

//    public InboundDto update(Long id, InboundDto inbound){
//        Inbound foundedInbound = inboundRepository.findById(id).orElse(null);
//        foundedInbound.setSectionId(inbound.getSectionId());
//        foundedInbound.setDate(inbound.getDate());
//        List<Batch> newBatchList = new ArrayList<>();
//
//        for (BatchDto payloadBatch:inbound.getBatchList()){
//            Batch foundedBatch = batchService.get(payloadBatch.getId());
//            if (foundedBatch.getInbound().getId() != id) throw new BusinessException("Id de batch não corresponde ao inbound");
//            foundedBatch.setInitialQuantity(payloadBatch.getInitialQuantity());
//            foundedBatch.setType(payloadBatch.getType());
//            foundedBatch.setProductAnnouncementDto(productAnnouncementService.get(payloadBatch.getProductId()));
//            Integer sold = foundedBatch.getInitialQuantity() - foundedBatch.getStock();
//            foundedBatch.setStock(payloadBatch.getInitialQuantity() - sold);
//            foundedBatch.setManufacturingDateTime(payloadBatch.getManufacturingDateTime());
//            foundedBatch.setDueDate(payloadBatch.getDueDate());
//            foundedBatch.setCurrentTemperature(payloadBatch.getCurrentTemperature());
//            foundedBatch.setInbound(foundedInbound);
//            newBatchList.add(foundedBatch);
//        }
//        foundedInbound.setBatchList(newBatchList);
//        inboundRepository.save(foundedInbound);
//        return foundedInbound;
//    }

    private void checkTypeMatch(ProductTypes p1, ProductTypes p2){
        if (!p1.equals(p2)) {
            String message = "Um produto " + p1 + " não pode ser armazenado em um setor de " + p2;
            throw new BusinessException(message);
        }
    }

}