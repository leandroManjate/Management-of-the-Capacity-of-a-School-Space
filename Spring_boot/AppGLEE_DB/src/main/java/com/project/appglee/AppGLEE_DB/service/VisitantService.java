package com.project.appglee.AppGLEE_DB.service;

import com.project.appglee.AppGLEE_DB.entity.Visitant;
import com.project.appglee.AppGLEE_DB.repository.VisitantRepository;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.project.appglee.AppGLEE_DB.utils.Global.*;

@Service
@Transactional
public class VisitantService {
    private final VisitantRepository visitantRepository;

    public VisitantService(VisitantRepository visitantRepository) {
        this.visitantRepository = visitantRepository;
    }

    //METHOD TO SAVE AND UPDATE VISITANT
    public GenericResponse save(Visitant visitant){
        Optional<Visitant> opt = this.visitantRepository.findById(visitant.getId());
        int idf = opt.isPresent() ? opt.get().getId() : 0;
        if(idf == 0){
            if(visitantRepository.existByNumPhone(visitant.getPhone().trim()) == 1){
                return new GenericResponse(TYPE_RESULT, RESP_WARNING, "Sorry: " +
                        "There is already a visitant with the same number phone, " +
                        "and if the problem persists, contact technical support", null);
            }else{
                //SAVE
                visitant.setId(idf);
                return new GenericResponse(TYPE_DATA, RESP_OK, "Visitant registered correctly! ", this.visitantRepository.save(visitant));
            }
        }else{
            //UPDATE REGISTER
            if(visitantRepository.existByNumPhoneForUpdate(visitant.getPhone().trim(), visitant.getId()) == 1){
                return new GenericResponse(TYPE_RESULT, RESP_WARNING, "Error: There is a Visitant with the same data! " +
                        "Check and try again. ", null);
            }else{
                //UPDATE
                visitant.setId(idf);
                return new GenericResponse(TYPE_DATA, RESP_OK, "Updated visitant data! ", this.visitantRepository.save(visitant));
            }
        }
    }
}
