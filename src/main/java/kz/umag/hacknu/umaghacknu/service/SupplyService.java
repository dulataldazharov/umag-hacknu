package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.repo.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplyService {

    @Autowired
    public SupplyRepository supplyRepository;
}
