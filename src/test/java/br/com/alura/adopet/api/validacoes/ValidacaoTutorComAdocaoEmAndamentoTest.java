package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class ValidacaoTutorComAdocaoEmAndamentoTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validador;



    @Test
    @DisplayName("Não deveria permitir solicitação de adoção de tutor com pedido em andamento")
    void validar1() {
        //ARRANGE
        given(adocaoRepository.existsByTutorIdAndStatus(
                dto.idTutor(),
                StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);

        //ACT + ASSERT
        assertThrows(ValidacaoException.class,()-> validador.validar(dto));
    }

    @Test
    @DisplayName("Deveria permitir solicitação de adoção de tutor com pedido em andamento")
    void validar2() {
        //ARRANGE
        given(adocaoRepository.existsByTutorIdAndStatus(
                dto.idTutor(),
                StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);

        //ACT + ASSERT
        assertThrows(ValidacaoException.class,()-> validador.validar(dto));
    }
}