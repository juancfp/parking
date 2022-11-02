package br.juancfp.parking.mapper;

import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parking){
        return parking.stream().map(pk -> toParkingDTO(pk)).collect(Collectors.toList());
    }
}
