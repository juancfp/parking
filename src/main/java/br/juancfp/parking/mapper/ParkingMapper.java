package br.juancfp.parking.mapper;

import br.juancfp.parking.dto.ParkingCreateDTO;
import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// Classe OK - Finalizada (02-11-2022)
@Component
public class ParkingMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    // temporário (até criar o módulo de persistencia)
    public Parking toParking(ParkingDTO parkingDTO){
        return MODEL_MAPPER.map(parkingDTO, Parking.class);
    }
    public Parking toParkingFromCreate(ParkingCreateDTO parkingCreateDTO){
        var id = UUID.randomUUID().toString();
        Parking pk = MODEL_MAPPER.map(parkingCreateDTO, Parking.class);
        pk.setId(id);
        return pk;
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parking){
        return parking.stream().map(pk -> toParkingDTO(pk)).collect(Collectors.toList());
    }
    public ParkingDTO toParkingDTOFromCreate(ParkingCreateDTO pcdto) {
        Parking pk = toParkingFromCreate(pcdto);
        return toParkingDTO(pk);
    }
}
