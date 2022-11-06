package br.juancfp.parking.controller;

import br.juancfp.parking.dto.ParkingCreateDTO;
import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api("Parking Controller")
public class ParkingController {


    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;

    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        return ResponseEntity.ok(parkingService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Find parking by id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id ){

        return ResponseEntity.of(parkingService.findById(id));
    }

    @PostMapping
    @ApiOperation("Create new parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){

        return new ResponseEntity(parkingService.create(dto), HttpStatus.CREATED);
    }
}
