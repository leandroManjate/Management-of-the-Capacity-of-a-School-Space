package com.project.appglee.AppGLEE_DB.service;
import com.project.appglee.AppGLEE_DB.entity.DetailRequest;
import com.project.appglee.AppGLEE_DB.repository.DetailRequestRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DetailRequestService {
    private final DetailRequestRepository detailRequestRepository;

    public DetailRequestService(DetailRequestRepository detailRequestRepository) {
        this.detailRequestRepository = detailRequestRepository;
    }
    //SAVE REQUEST DETAIL
    public void saveDetails(Iterable<DetailRequest> detail){
        this.detailRequestRepository.saveAll(detail);
    }
}
