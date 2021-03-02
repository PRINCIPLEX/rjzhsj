package org.javaboy.vhr.controller;

import org.ggm.vhr.entity.MapEntity;
import org.javaboy.vhr.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
public class MapController {

    @Autowired
    MapService mapService;




    @RequestMapping(value = "/map/manage/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteMap(@PathVariable Long id) {
        return mapService.deleteMap(id);
    }





    @RequestMapping(value = "/map/manage/",method = RequestMethod.POST)
    @ResponseBody
    public boolean addMap(@RequestBody MapEntity mapEntity)
    {
        return mapService.addMap(mapEntity);
    }

    @RequestMapping(value = "/map/manage")
    @ResponseBody
    public List<MapEntity> getMap()
    {
        return mapService.getMap();
    }
}
