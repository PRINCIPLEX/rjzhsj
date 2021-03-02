package org.javaboy.vhr.controller;

import org.ggm.vhr.entity.StudyTimeEntity;
import org.javaboy.vhr.service.StudyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class StudyTimeController {
    @Autowired
    StudyTimeService studyTimeService;


    @RequestMapping(value = "/time/manage")
    @ResponseBody
    public List<StudyTimeEntity> getTime()
    {
        return studyTimeService.getTime();
    }
}
