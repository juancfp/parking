package br.juancfp.parking.service;

import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.mapper.ParkingMapper;
import br.juancfp.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingMapper parkingMapper;
    public ParkingService(ParkingMapper parkingMapper){
        this.parkingMapper = parkingMapper;
    }
    // ========= mock do banco de dados ===========
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "RJ", "CELTA", "PRETO");
        parkingMap.put(id, parking);
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    // ============================================
    public List<ParkingDTO> findAll(){
        //TODO conexão com banco de dados
        List<Parking> pk = parkingMap.values().stream().collect(Collectors.toList());
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(pk);

        return result;
    }
}
