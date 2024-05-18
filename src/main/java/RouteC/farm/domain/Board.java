package RouteC.farm.domain;

import jakarta.persistence.*;
import lombok.Data;
// import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // CreatedDate 사용하기 위해 추가
@Entity // Entity는 db에 있는 테이블을 의미
@Data
public class Board {

    @Id // primary key를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    // @CreatedDate
    // private LocalDateTime updated_datetime;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;
}