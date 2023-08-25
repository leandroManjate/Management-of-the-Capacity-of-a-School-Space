package com.project.appglee.AppGLEE_DB.service;

import com.project.appglee.AppGLEE_DB.entity.DetailRequest;
import com.project.appglee.AppGLEE_DB.entity.Request;
import com.project.appglee.AppGLEE_DB.entity.dto.GenereteRequestDTO;
import com.project.appglee.AppGLEE_DB.entity.dto.RequestWithDetailDTO;
import com.project.appglee.AppGLEE_DB.repository.DetailRequestRepository;
import com.project.appglee.AppGLEE_DB.repository.EventRepository;
import com.project.appglee.AppGLEE_DB.repository.RequestRepository;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.project.appglee.AppGLEE_DB.utils.Global.*;

@Service
@Transactional
public class RequestService {

  private final RequestRepository requestRepository;
  private final DetailRequestRepository detailRequestRepository;
  private final DetailRequestService detailRequestService;
  private final EventRepository eventRepository;

  public RequestService(RequestRepository requestRepository, DetailRequestRepository detailRequestRepository, DetailRequestService detailRequestService, EventRepository eventRepository) {
    this.requestRepository = requestRepository;
    this.detailRequestRepository = detailRequestRepository;
    this.detailRequestService = detailRequestService;
    this.eventRepository = eventRepository;
  }

  //REQUESTS EVENT AND HIS DETAILS
  public GenericResponse<List<RequestWithDetailDTO>> giveMyRequest(int idVis) {
    final List<RequestWithDetailDTO> dtos = new ArrayList<>();
    final Iterable<Request> requests = requestRepository.giveMyRequest(idVis);
    requests.forEach(p -> {
      dtos.add(new RequestWithDetailDTO(p, detailRequestRepository.findByRequest(p.getId())));
    });
    return new GenericResponse(CORRECT_OPERATION, RESP_OK, "Request find! ", dtos);
  }

  //SAVE REQUEST
  public GenericResponse saveRequest(GenereteRequestDTO dto) {

      Date date = new Date();
      dto.getRequest().setConfirmResquest(new java.sql.Date(date.getTime()));
      dto.getRequest().setCancelRequest(false);
      dto.getRequest().setVisitant(dto.getVisitant());
      this.requestRepository.save(dto.getRequest());
      for (DetailRequest detailRequest : dto.getDetailRequests()) {
          detailRequest.setRequest(dto.getRequest());
          this.requestRepository.discountVacancy(detailRequest.getQuantity(), detailRequest.getEvent().getId());

          /* if (detailRequest.getEvent().getVacancy() > 0) {
            this.requestRepository.discountVacancy(detailRequest.getQuantity(), detailRequest.getEvent().getId());
          } else { } */

        }

    //Call DetailRequestService
    this.detailRequestService.saveDetails(dto.getDetailRequests());
    return new GenericResponse(TYPE_DATA, RESP_OK, CORRECT_OPERATION, dto);
  }

  //CANCEL REQUEST
  public GenericResponse cancelRequest(int id) {
    Request request = this.requestRepository.findById(id).orElse(new Request());
    if (request.getId() != 0) {
      request.setCancelRequest(true);
      this.reestablishVacancy(id);
      this.requestRepository.save(request);
      return new GenericResponse(TYPE_DATA, RESP_OK, CORRECT_OPERATION, request);
    } else {
      return new GenericResponse(TYPE_DATA, RESP_OK, INCORRECT_OPERATION, "Request not vaild!!");
    }
  }

  private void reestablishVacancy(final int requestID) {
    Iterable<DetailRequest> detailRequests = this.detailRequestRepository.findByRequest(requestID);
    for (DetailRequest dr : detailRequests) {
      requestRepository.increaseVacancy(dr.getQuantity(), dr.getEvent().getId());
    }
  }

}
