package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class ValidacaoTutorComLimiteDeAdocoesTest {

    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validador;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize the dto with a valid tutor ID
        when(dto.idTutor()).thenReturn(1L); // Assume the tutor ID is 1
    }

    @Test
    @DisplayName("Deveria recusar uma nova adoção por ser maior que 5")
    void validar() {
        //ARRANGE
        given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).willReturn(5);

        //ACT + ASSERT
        assertThrows(ValidacaoException.class,() -> validador.validar(dto));

    }

    @Test
    @DisplayName("Deveria permitir uma nova adoção por ser maior que 5")
    void validar2() {
        //ARRANGE
        given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).willReturn(4);

        //ACT + ASSERT
        assertDoesNotThrow(() -> validador.validar(dto));

    }
}