package br.juancfp.parking.service;

import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {


    // ========= mock do banco de dados ===========
    private static Map<String, ParkingDTO> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        ParkingDTO parking = new ParkingDTO(id, "DMS-1111", "RJ", "CELTA", "PRETO");
        parkingMap.put(id, parking);
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    // ============================================
    public List<ParkingDTO> findAll(){
        //TODO conexão com banco de dados

        return parkingMap.values().stream().collect(Collectors.toList());
    }
}
