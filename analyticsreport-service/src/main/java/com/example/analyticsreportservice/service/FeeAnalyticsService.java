package com.example.analyticsreportservice.service;

import com.example.analyticsreportservice.dto.FeeReportDTO;
import com.example.analyticsreportservice.entity.FeeDetails;
import com.example.analyticsreportservice.repository.FeeDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeAnalyticsService {
    @Autowired
    private FeeDetailsRepo feeDetailsRepo;

    public FeeReportDTO feeCollectionReport(String month){
        List<FeeDetails> feeDetails=feeDetailsRepo.findAll();
        Double hostelFee=0.0;
        Double tutionFee=0.0;
        Double examFee=0.0;
        Double messFee=0.0;
        Double libraryFee=0.0;
        Double totalCollection=0.0;
        Integer isLibraryFee=0;
        Integer isHostelFee=0;
        Integer isTutionFee=0;
        Integer isMessFee=0;
        Integer isexamFee=0;
        Integer others=0;
        for(FeeDetails f:feeDetails){
            if(f.getPaidDate().getMonth().toString().equals(month)){
                totalCollection+=f.getAmountPaid();
                switch (f.getCategory()){
                    case "LIBRARY_FEE":
                        libraryFee+=f.getAmountPaid();
                        isLibraryFee++;
                        break;
                    case "HOSTEL_FEE":
                        hostelFee+=f.getAmountPaid();
                        isHostelFee++;
                        break;
                    case "TUTION_FEE":
                        tutionFee+=f.getAmountPaid();
                        isTutionFee++;
                        break;
                    case "MESS_FEE":
                        messFee+=f.getAmountPaid();
                        isMessFee++;
                        break;
                    case "EXAM_FEE":
                        examFee+=f.getAmountPaid();
                        isexamFee++;
                        break;
                    default:others++;

                }
            }

        }
        return new FeeReportDTO(hostelFee,examFee,libraryFee,tutionFee,messFee,isexamFee,isHostelFee,isLibraryFee,isTutionFee,totalCollection);
    }


    public String feeCollectionAnalyticsInMonth(String month){
        List<FeeDetails> feeDetails=feeDetailsRepo.findAll();
        Double totalCollection=0.0;
        Integer isLibraryFee=0;
        Integer isHostelFee=0;
        Integer isTutionFee=0;
        Integer isMessFee=0;
        Integer isexamFee=0;
        Integer others=0;
        for(FeeDetails f:feeDetails){
            if(f.getPaidDate().getMonth().toString().equals(month)){
                totalCollection+=f.getAmountPaid();
                switch (f.getCategory()){
                    case "LIBRARY_FEE":
                        isLibraryFee++;
                        break;
                    case "HOSTEL_FEE":
                        isHostelFee++;
                        break;
                    case "TUTION_FEE":
                        isTutionFee++;
                        break;
                    case "MESS_FEE":
                        isMessFee++;
                        break;
                    case "EXAM_FEE":isexamFee++;
                    break;
                    default:others++;

                }
            }

        }
        if(isLibraryFee>isHostelFee&&isLibraryFee>isMessFee&&isLibraryFee>isTutionFee&&isLibraryFee>isexamFee){
            return "Overall monthly fee collection is Rs."+totalCollection+"In this month High amount of fee collected from LIBRARY FEE Most students paid LIBRARYFEE";
        }
        else if(isTutionFee>isHostelFee&&isTutionFee>isLibraryFee&&isTutionFee>isexamFee&&isTutionFee>isMessFee){
            return "Overall monthly fee collection is Rs."+totalCollection+"In this month High amount of fee collected from TUTIONFEE Most students paid TUTIONFEE";

        }
        else if(isexamFee>isHostelFee&&isexamFee>isLibraryFee&&isexamFee>isTutionFee&&isTutionFee>isMessFee){
            return "Overall monthly fee collection is Rs."+totalCollection+"In this month High amount of fee collected from EXAMFEE Most students paid EXAMFEE";

        }
        else if(isHostelFee>isTutionFee&&isHostelFee>isLibraryFee&&isHostelFee>isexamFee&&isHostelFee>isMessFee){
            return "Overall monthly fee collection is Rs."+totalCollection+"In this month High amount of fee collected from HOSTELFEE Most students paid HOSTELFEE";

        }
        else{
            return "Overall monthly fee collection is Rs."+totalCollection+"In this month High amount of fee collected from MESSFEE Most students paid MESSFEE";
        }
    }
}
