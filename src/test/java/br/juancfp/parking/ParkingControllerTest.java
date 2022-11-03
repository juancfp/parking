package br.juancfp.parking;

import br.juancfp.parking.controller.ParkingController;
import br.juancfp.parking.dto.ParkingDTO;
import br.juancfp.parking.service.ParkingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingControllerTest {

    private static final String PARKING_API_URI = "/parking";
    private static final String VALID_UUID = UUID.randomUUID().toString();



    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    
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

}
