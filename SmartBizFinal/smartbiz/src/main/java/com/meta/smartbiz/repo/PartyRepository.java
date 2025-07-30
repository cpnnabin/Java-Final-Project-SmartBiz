package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.Party;
import com.meta.smartbiz.entity.PartyType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findByNameContainingIgnoreCase(String name);
    List<Party> findByPartyType(PartyType partyType);
    List<Party> findByPartyTypeIn(List<PartyType> partyTypes);
}

