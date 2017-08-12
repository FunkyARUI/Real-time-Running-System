package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RunningLocationServiceApplication.class)
@WebAppConfiguration
public class LocationRepositoryTest {
    @Autowired
    LocationRepository repository;

    @Test
    public void saveLocation() {
        this.repository.save(new Location(new UnitInfo("ross-runningId-1")));
        assertThat(this.repository.findByUnitInfoRunningId("ross-runningId-1", new PageRequest(0, 1)).getContent()
                .get(0).getRunningId()).isEqualTo("ross-runningId-1");
    }

    @Test
    public void queryByUnitInfoRunningId() {
        final String runningId = "ross-runningId-2";
        this.repository.save(new Location(new UnitInfo(runningId)));
        assertTrue(this.repository.findByUnitInfoRunningId(runningId, new PageRequest(0, 10)).getTotalElements() == 1);
        assertTrue(this.repository.findByUnitInfoRunningId(runningId, new PageRequest(0, 10)).getContent().get(0)
                .getRunningId().equals(runningId));
    }
}
