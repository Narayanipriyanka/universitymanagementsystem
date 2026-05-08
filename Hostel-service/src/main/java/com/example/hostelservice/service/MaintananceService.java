package com.example.hostelservice.service;

import com.example.hostelservice.dto.MaintananceRequestDTO;
import com.example.hostelservice.entity.MaintananceRequest;
import com.example.hostelservice.entity.MaintananceStatus;
import com.example.hostelservice.repository.MaintananceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaintananceService {
    @Autowired
    private MaintananceRequestRepository maintananceRequestRepository;
    public String raiseMaintananceIssue(MaintananceRequestDTO dto){
        MaintananceRequest request=new MaintananceRequest();
        request.setDescription(dto.getDescription());
        request.setIssueDate(LocalDate.now());
        request.setIssueIn(dto.getIssueIn());
        request.setStatus(MaintananceStatus.PENDING);
        request.setStudentId(dto.getStudentId());
        maintananceRequestRepository.save(request);
        return "maintanance issue submitted successfully";
    }
    public List<MaintananceRequest> getPendingMaintanceReq(){
        List<MaintananceRequest> requests=maintananceRequestRepository.findAllByStatus(MaintananceStatus.PENDING);
       return requests;
    }
    public String solveMaintanaceIssue(String issueIn){
        List<MaintananceRequest> request=maintananceRequestRepository.findAllByIssueIn(issueIn);
        String descripition=new String();
        for(MaintananceRequest r:request){
            r.setStatus(MaintananceStatus.COMPLETED);
            descripition+=r.getDescription();
            maintananceRequestRepository.save(r);
        }
        return descripition+"in"+issueIn+" resolved successfully";
    }
}
