package com.routine.def.definitionOps.business.service.auxilary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.def.common.dtoMapper.EntityToDaoMapper;
import com.routine.def.common.serviceResponse.ServiceResponse;
import com.routine.def.common.serviceResponse.ServiceResponseStandardized;
import com.routine.def.definitionOps.business.dto.ArtDTO;
import com.routine.def.definitionOps.business.dto.ProjectDTO;
import com.routine.def.definitionOps.business.dto.SportDTO;
import com.routine.def.definitionOps.dao.entity.Art;
import com.routine.def.definitionOps.dao.entity.Project;
import com.routine.def.definitionOps.dao.entity.Sport;
import com.routine.def.definitionOps.dao.repository.ArtRepo;
import com.routine.def.definitionOps.dao.repository.ProjectRepo;
import com.routine.def.definitionOps.dao.repository.SportRepo;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class NameListService {
    
    private final SportRepo sportRepo;
    private final ProjectRepo projectRepo;
    private final ArtRepo artRepo;
    private final ServiceResponseStandardized spz;
    
    @Autowired
    public NameListService(
        SportRepo sportRepo,
        ProjectRepo projectRepo,
        ArtRepo artRepo,
        ServiceResponseStandardized spz
    ){
        this.sportRepo = sportRepo;
        this.projectRepo = projectRepo;
        this.artRepo = artRepo;
        this.spz = spz;
    }

        // Sport 
    public ServiceResponse selectDistinctSportTypes(HttpServletRequest request){
        try{
            List<Sport> data =  this.sportRepo.findAll();
            List<SportDTO> dataDao = new ArrayList<>();
            if(data!=null && !data.isEmpty()){
                EntityToDaoMapper entityToDaoMapper = new EntityToDaoMapper();
                for(Sport e: data){
                    SportDTO daoItem = entityToDaoMapper.convertToDao(e, SportDTO.class);
                    dataDao.add(daoItem);
                }
            }
            return this.spz.OkResponse(dataDao, request);
        }catch(Exception e){
            return this.spz.IntervalServerErrorResponse(request);
        }
    }

        // Project 
    public ServiceResponse selectDistinctProjects(HttpServletRequest request){
        try{
            List<Project> data =  this.projectRepo.findAll();
            List<ProjectDTO> dataDao = new ArrayList<>();
            if(data!=null && !data.isEmpty()){
                EntityToDaoMapper entityToDaoMapper = new EntityToDaoMapper();
                for(Project e: data){
                    ProjectDTO daoItem = entityToDaoMapper.convertToDao(e, ProjectDTO.class);
                    dataDao.add(daoItem);
                }
            }
            return this.spz.OkResponse(dataDao, request);
        }catch(Exception e){
            return this.spz.IntervalServerErrorResponse(request);
        }
    }

        // Art 
    public ServiceResponse selectDistinctArtTypes(HttpServletRequest request){
        try{
            List<Art> data =  this.artRepo.findAll();
            List<ArtDTO> dataDao = new ArrayList<>();
            if(data!=null && !data.isEmpty()){
                EntityToDaoMapper entityToDaoMapper = new EntityToDaoMapper();
                for(Art e: data){
                    ArtDTO daoItem = entityToDaoMapper.convertToDao(e, ArtDTO.class);
                    dataDao.add(daoItem);
                }
            }
            return this.spz.OkResponse(dataDao, request);
        }catch(Exception e){
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
}
