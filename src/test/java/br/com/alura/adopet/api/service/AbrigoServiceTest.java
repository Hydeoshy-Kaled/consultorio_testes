package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService service;

    @Mock
    private AbrigoRepository repository;

    @Mock
    private Abrigo abrigo;

    @Mock
    private PetRepository petRepository;

    @Test
    @DisplayName("Deveria chamar lista de todos os abrigos")
    void listar() {
        //ACT
        service.listar();

        //ASSERT
        then(repository).should().findAll();

    }



    @Test
    @DisplayName("Deveria chamar lista de pets do abrigo atraves do nome")
    void listarPetsDoAbrigo() {
        //ARRANGE
        String nome = "Miau";

        //ACT
        given(repository.findByNome(nome)).willReturn(Optional.of(abrigo));

        //ASSERT
        then(petRepository).should().findByAbrigo(abrigo);
    }


}