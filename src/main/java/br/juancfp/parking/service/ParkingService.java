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
        id = getUUID();
        Parking parking2 = new Parking(id, "WAP-1211", "RJ", "TORO", "AZUL");
        parkingMap.put(id, parking2);
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

    public Optional<ParkingDTO> findById(String id) {

        var result = parkingMap.get(id);
        ParkingDTO resultDTO = null;
        if(result!=null){
            resultDTO = parkingMapper.toParkingDTO(result);
        }
        return Optional.ofNullable(resultDTO);
    }
}
