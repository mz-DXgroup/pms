package dxgroup.pms.domain;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
}
