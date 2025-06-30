package at.fhtw.energy.service;

import at.fhtw.energy.EnergyRestApiApplication;
import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.entity.CurrentPercentageEntity;
import at.fhtw.energy.repository.CurrentPercentageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Integrationstest:
 *  • Startet den kompletten Spring-Context.
 *  • Ersetzt nur das Repository durch ein Mockito-Mock via @MockitoBean.
 */
@SpringBootTest(classes = EnergyRestApiApplication.class)
class EnergyServiceIntegrationTest {

    @Autowired
    private EnergyService energyService;

    @MockitoBean
    private CurrentPercentageRepository currentPercentageRepository;

    @Test
    void getCurrentPercentage_shouldReturnMappedValues() {
        // Arrange
        CurrentPercentageEntity entity = new CurrentPercentageEntity();
        entity.setCommunityUsed(70.0);
        entity.setGridUsed(30.0);
        when(currentPercentageRepository.findTopByOrderByHourDesc())
                .thenReturn(entity);

        // Act
        CurrentEnergyResponse response = energyService.getCurrentPercentage();

        // Assert & Mockito-Verification
        assertThat(response).isNotNull();
        assertThat(response.getCommunityDepleted()).isEqualTo(70.0);
        assertThat(response.getGridPortion()).isEqualTo(30.0);
        verify(currentPercentageRepository, times(1)).findTopByOrderByHourDesc();
        verifyNoMoreInteractions(currentPercentageRepository);
    }

    @Test
    void getCurrentPercentage_whenNoEntity_shouldReturnZeros() {
        when(currentPercentageRepository.findTopByOrderByHourDesc()).thenReturn(null);

        CurrentEnergyResponse response = energyService.getCurrentPercentage();

        assertThat(response.getCommunityDepleted()).isZero();
        assertThat(response.getGridPortion()).isZero();
        verify(currentPercentageRepository, times(1)).findTopByOrderByHourDesc();
        verifyNoMoreInteractions(currentPercentageRepository);
    }
}
