package br.juancfp.parking.controller;

import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {


    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    @GetMapping
    public List<ParkingDTO> findAll(){
        return parkingService.findAll();
    }
}
