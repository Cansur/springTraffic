package RouteC.farm.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Trade {

    @Id
    private Integer id;
}
