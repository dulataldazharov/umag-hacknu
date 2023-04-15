package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.FirstUpdate;
import kz.umag.hacknu.umaghacknu.repo.FirstUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FirstUpdateService {
    @Autowired
    public FirstUpdateRepository firstUpdateRepository;

    public void updateTime(Long barcode, Date time) {
        FirstUpdate upd = firstUpdateRepository.findByBarcode(barcode);
        if (upd == null) {
            upd = new FirstUpdate();
            upd.barcode = barcode;
            upd.time = time;
        }
        if (upd.time.after(time)) {
            upd.time = time;
        }
        firstUpdateRepository.save(upd);
    }
}
