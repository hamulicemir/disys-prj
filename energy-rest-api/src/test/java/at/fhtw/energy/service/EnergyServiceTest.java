// Unit‑Tests für EnergyService
package at.fhtw.energy.service;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.entity.CurrentPercentageEntity;
import at.fhtw.energy.repository.CurrentPercentageRepository;
import at.fhtw.energy.repository.HistoricalEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnergyServiceTest {

    @Mock
    private CurrentPercentageRepository currentPercentageRepository;

    @Mock
    private HistoricalEntryRepository historicalEntryRepository;

    @InjectMocks
    private EnergyService energyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCurrentPercentage_whenDataExists_returnsMappedResponse() {
        // Arrange
        CurrentPercentageEntity entity = new CurrentPercentageEntity();
        entity.setCommunityUsed(65.5);
        entity.setGridUsed(34.5);

        when(currentPercentageRepository.findTopByOrderByHourDesc()).thenReturn(entity);

        // Act
        CurrentEnergyResponse response = energyService.getCurrentPercentage();

        // Assert
        assertNotNull(response);
        assertEquals(65.5, response.getCommunityDepleted(), 0.001);
        assertEquals(34.5, response.getGridPortion(), 0.001);
    }

    @Test
    void getCurrentPercentage_whenNoData_returnsZeros() {
        // Arrange
        when(currentPercentageRepository.findTopByOrderByHourDesc()).thenReturn(null);

        // Act
        CurrentEnergyResponse response = energyService.getCurrentPercentage();

        // Assert
        assertNotNull(response);
        assertEquals(0.0, response.getCommunityDepleted());
        assertEquals(0.0, response.getGridPortion());
    }
}
