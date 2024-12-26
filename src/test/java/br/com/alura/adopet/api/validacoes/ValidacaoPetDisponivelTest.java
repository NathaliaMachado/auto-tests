package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @Mock
    private PetRepository repository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private Pet pet;

    @InjectMocks
    ValidacaoPetDisponivel petDisponivel;

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoPet() {

        BDDMockito.given(repository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        Assertions.assertDoesNotThrow(() -> petDisponivel.validar(dto));
    }

    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoPet() {

        BDDMockito.given(repository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> petDisponivel.validar(dto));
    }

}