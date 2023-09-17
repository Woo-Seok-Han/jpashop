package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
