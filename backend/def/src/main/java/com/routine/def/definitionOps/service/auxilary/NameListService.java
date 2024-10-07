package com.routine.def.definitionOps.service.auxilary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.def.common.daoMapper.EntityToDaoMapper;
import com.routine.def.common.serviceResponse.ServiceResponse;
import com.routine.def.common.serviceResponse.ServiceResponseStandardized;
import com.routine.def.definitionOps.entity.Art;
import com.routine.def.definitionOps.entity.Project;
import com.routine.def.definitionOps.entity.Sport;
import com.routine.def.definitionOps.repository.ArtRepo;
import com.routine.def.definitionOps.repository.ProjectRepo;
import com.routine.def.definitionOps.repository.SportRepo;
import com.routine.def.definitionOps.service.dao.ArtDao;
import com.routine.def.definitionOps.service.dao.ProjectDao;
import com.routine.def.definitionOps.service.dao.SportDao;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class NameListService {
    @Autowired
    private SportRepo sportRepo;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ArtRepo artRepo;
    @Autowired
    private ServiceResponseStandardized spz;
    

        // Sport 
    public ServiceResponse selectDistinctSportTypes(HttpServletRequest request){
        try{
            List<Sport> data =  this.sportRepo.findAll();
            List<SportDao> dataDao = new ArrayList<>();
            if(data!=null && !data.isEmpty()){
                EntityToDaoMapper entityToDaoMapper = new EntityToDaoMapper();
                for(Sport e: data){
                    SportDao daoItem = entityToDaoMapper.convertToDao(e, SportDao.class);
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
            List<ProjectDao> dataDao = new ArrayList<>();
            if(data!=null && !data.isEmpty()){
                EntityToDaoMapper entityToDaoMapper = new EntityToDaoMapper();
                for(Project e: data){
                    ProjectDao daoItem = entityToDaoMapper.convertToDao(e, ProjectDao.class);
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
            List<ArtDao> dataDao = new ArrayList<>();
            if(data!=null && !data.isEmpty()){
                EntityToDaoMapper entityToDaoMapper = new EntityToDaoMapper();
                for(Art e: data){
                    ArtDao daoItem = entityToDaoMapper.convertToDao(e, ArtDao.class);
                    dataDao.add(daoItem);
                }
            }
            return this.spz.OkResponse(dataDao, request);
        }catch(Exception e){
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
}
