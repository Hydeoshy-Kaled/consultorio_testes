package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService service;

    @Mock
    private TutorRepository repository;

    @Mock
    private CadastroTutorDto dto;

    @Mock
    private Tutor tutor;

    @Mock
    private AtualizacaoTutorDto atualizacaoTutorDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this.service);
    }
    @Test
    @DisplayName("Deveria cadastrar tutor")
    void cadastrar(){
        //Arrange

        given(repository.existsByTelefoneOrEmail(dto.telefone(),dto.email())).willReturn(false);

        //ACT + ASSERT

        assertDoesNotThrow(() -> service.cadastrar(dto));
        then(repository).should().save(new Tutor(dto));

    }

    @Test
    @DisplayName("Deveria atualizar dados do tutor")
    void atualizar(){
        //Arrange

        given(repository.getReferenceById(atualizacaoTutorDto.id())).willReturn(tutor);

        //ACT

        service.atualizar(atualizacaoTutorDto);

        //  ASSERT
        then(tutor).should().atualizarDados(atualizacaoTutorDto);

    }
}