package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @InjectMocks
    private PetService service;

    @Mock
    private CadastroPetDto cadastroPetDto;

    @Mock
    private PetRepository repository;

    @Mock
    private Abrigo abrigo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria cadastrar pet")
    void buscarPetsDisponiveis() {
        service.cadastrarPet(abrigo,cadastroPetDto);

        then(repository).should().save(any(Pet.class));
    }

    @Test
    @DisplayName("Deveria retornar os pets disponiveis")
    void cadastrarPet() {
        when(repository.findAllByAdotadoFalse()).thenReturn(Collections.emptyList());

        List<PetDto> pets = service.buscarPetsDisponiveis();

        assertTrue(pets.isEmpty());
        then(repository).should().findAllByAdotadoFalse();
    }
}