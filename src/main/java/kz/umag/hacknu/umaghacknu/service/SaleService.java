package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.repo.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    public SaleRepository saleRepository;
}
