package br.juancfp.parking.controller;

import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<ParkingDTO>> findAll(){
        return ResponseEntity.ok(parkingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id ){

        return ResponseEntity.of(parkingService.findById(id));
    }
}
