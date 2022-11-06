package br.juancfp.parking;

import br.juancfp.parking.controller.ParkingController;
import br.juancfp.parking.dto.ParkingCreateDTO;
import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.mapper.ParkingMapper;
import br.juancfp.parking.service.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ParkingControllerTest {

    private static final String PARKING_API_URI = "/parking";
    private static final String VALID_UUID = UUID.randomUUID().toString();
    private static final String INVALID_UUID = UUID.randomUUID().toString();




    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();



    @Mock
    private ParkingMapper parkingMapper;
    @Mock
    private ParkingService parkingService;

    @InjectMocks
    private ParkingController parkingController;

    @BeforeEach
    void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(parkingController).build();
    }

    // TODO CRUD tests
    // (OK)    -     READ : GET sem argumentos (todos) -> returns ok
    // (OK)    -     READ : GET com argumento id -> returns ok
    // (OK)    -     READ : GET com argumento id -> returns not found
    //         -     CREATE : POST sem argumentos necessarios -> bad request 400
    //         -     CREATE : POST com argumentos necessarios -> created 201




    @Test
    void whenGETIsCalledWithoutArgumentsThenAOkStatusIsReturned() throws Exception {
        //given

        ParkingDTO result = new ParkingDTO(VALID_UUID, "ABC-0101", "RJ", "CORSA", "CINZA");
        List<ParkingDTO> resultList = Arrays.asList(result);
        // when
        when(parkingService.findAll()).thenReturn(resultList);


        //then
        mockMvc.perform(get(PARKING_API_URI).
                contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultList))
                ).andExpect(status().isOk());

    }

    @Test
    void whenGETIsCalledWithWrongArgumentsThenNotFoundIsReturned() throws Exception {
        //given
        ParkingDTO invalidResult = new ParkingDTO(INVALID_UUID,
                "ABC-0101", "RJ", "CORSA", "CINZA");
        // when
        when(parkingService.findById(INVALID_UUID)).thenReturn(Optional.empty());


        //then
        mockMvc.perform(get(PARKING_API_URI+"/"+INVALID_UUID).
                contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidResult))
        ).andExpect(status().isNotFound());

    }
    @Test
    void whenGETIsCalledWithArgumentsThenAOkStatusIsReturned() throws Exception {
        //given

        ParkingDTO result = new ParkingDTO(VALID_UUID, "ABC-0101", "RJ", "CORSA", "CINZA");
        // when

        when(parkingService.findById(VALID_UUID)).thenReturn(Optional.of(result));


        //then
        mockMvc.perform(get(PARKING_API_URI+"/"+VALID_UUID).
                contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(result))
        ).andExpect(status().isOk());

    }

    @Test
    void whenPOSTIsCalledThenACreatedStatusIsReturned() throws Exception{
        //given
        ParkingDTO parkingDTO = new ParkingDTO(VALID_UUID, " ", "", "", "");

        //when
        when(parkingService.create(isA(ParkingCreateDTO.class))).thenReturn(Optional.ofNullable(parkingDTO));

        //then
        mockMvc.perform(post(PARKING_API_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isCreated());

    }
}


