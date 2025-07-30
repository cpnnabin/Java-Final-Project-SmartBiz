package com.meta.smartbiz.mapper;

import com.meta.smartbiz.dto.PartyDto;
import com.meta.smartbiz.entity.Party;
import org.springframework.stereotype.Component;

@Component
public class PartyMapper {

    public PartyDto toDto(Party party) {
        if (party == null) return null;
        
        PartyDto dto = new PartyDto();
        dto.setId(party.getId());
        dto.setName(party.getName());
        dto.setEmail(party.getEmail());
        dto.setPhone(party.getPhone());
        dto.setAddress(party.getAddress());
        dto.setGstNumber(party.getGstNumber());
        dto.setPartyType(party.getPartyType());
        dto.setOpeningBalance(party.getOpeningBalance());
        dto.setCurrentBalance(party.getCurrentBalance());
        return dto;
    }

    public Party toEntity(PartyDto dto) {
        if (dto == null) return null;
        
        Party party = new Party();
        party.setId(dto.getId());
        party.setName(dto.getName());
        party.setEmail(dto.getEmail());
        party.setPhone(dto.getPhone());
        party.setAddress(dto.getAddress());
        party.setGstNumber(dto.getGstNumber());
        party.setPartyType(dto.getPartyType());
        party.setOpeningBalance(dto.getOpeningBalance() != null ? dto.getOpeningBalance() : 0.0);
        party.setCurrentBalance(dto.getCurrentBalance() != null ? dto.getCurrentBalance() : dto.getOpeningBalance() != null ? dto.getOpeningBalance() : 0.0);
        return party;
    }
}

