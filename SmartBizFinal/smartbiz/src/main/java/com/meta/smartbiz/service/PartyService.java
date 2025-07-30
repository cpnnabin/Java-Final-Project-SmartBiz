package com.meta.smartbiz.service;

import com.meta.smartbiz.dto.PartyDto;
import com.meta.smartbiz.entity.Party;
import com.meta.smartbiz.entity.PartyType;
import com.meta.smartbiz.mapper.PartyMapper;
import com.meta.smartbiz.repo.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PartyMapper partyMapper;

    public List<PartyDto> getAllParties() {
        return partyRepository.findAll().stream()
                .map(partyMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PartyDto> getPartyById(Long id) {
        return partyRepository.findById(id)
                .map(partyMapper::toDto);
    }

    public PartyDto saveParty(PartyDto partyDto) {
        Party party = partyMapper.toEntity(partyDto);
        Party savedParty = partyRepository.save(party);
        return partyMapper.toDto(savedParty);
    }

    public void deleteParty(Long id) {
        partyRepository.deleteById(id);
    }

    public List<PartyDto> searchParties(String name) {
        return partyRepository.findByNameContainingIgnoreCase(name).stream()
                .map(partyMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PartyDto> getPartiesByType(PartyType partyType) {
        return partyRepository.findByPartyType(partyType).stream()
                .map(partyMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PartyDto> getCustomers() {
        List<PartyType> customerTypes = List.of(PartyType.CUSTOMER, PartyType.BOTH);
        return partyRepository.findByPartyTypeIn(customerTypes).stream()
                .map(partyMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PartyDto> getSuppliers() {
        List<PartyType> supplierTypes = List.of(PartyType.SUPPLIER, PartyType.BOTH);
        return partyRepository.findByPartyTypeIn(supplierTypes).stream()
                .map(partyMapper::toDto)
                .collect(Collectors.toList());
    }
}

