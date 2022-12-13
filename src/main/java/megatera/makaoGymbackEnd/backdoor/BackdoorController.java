package megatera.makaoGymbackEnd.backdoor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import megatera.makaoGymbackEnd.models.Option;
import megatera.makaoGymbackEnd.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM work");
        jdbcTemplate.execute("DELETE FROM request");
        jdbcTemplate.execute("DELETE FROM person");
        jdbcTemplate.execute("DELETE FROM trainer");
        jdbcTemplate.execute("DELETE FROM option");
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM license");
        jdbcTemplate.execute("DELETE FROM lecture");

        jdbcTemplate.update("INSERT INTO " +
                        "person(" +
                        "id, user_name, name, pt_times, period_of_use, " +
                        "created_at, updated_at" +
                        ")" +
                        "VALUES(1, '오진성', '오진성', 0, 0, ?, ?)",
                now, now
        );

        Long trainerId = 1L;

        jdbcTemplate.update("INSERT INTO " +
                        "trainer(" +
                        "id, user_name, name, start_time, end_time, " +
                        "created_at, updated_at" +
                        ")" +
                        "VALUES(?, '오진성', '오진성', '09:00', '18:00', ?, ?)",
                trainerId, now, now
        );

        Long productId = 1L;

        List<Option> ptOptions = new ArrayList<>();
        Option option1 = new Option(productId, 12, 60, 360000);
        Option option2 = new Option(productId, 30, 180, 720000);
        ptOptions.add(option1);
        ptOptions.add(option2);


        Product product = new Product("피티", trainerId);

        jdbcTemplate.update("INSERT INTO " +
                        "product(" +
                        "id, title, trainer_id, " +
                        "created_at, updated_at" +
                        ") " +
                        "VALUES(?, ?, ?, ?, ?)",
                productId, product.title(), product.trainerId(),
                now, now
        );

        for (int i = 0; i < ptOptions.size(); i += 1) {
            Long id = (long) (i + 1);

            jdbcTemplate.update("INSERT INTO " +
                            "option(" +
                            "id, product_id, date_of_use, price, pt_times) " +
                            "VALUES(?, ?, ?, ?, ?)",
                    id,
                    ptOptions.get(i).productId(),
                    ptOptions.get(i).dateOfUse(),
                    ptOptions.get(i).price(),
                    ptOptions.get(i).ptTimes()
            );
        }

        return "Ok";
    }

    @GetMapping("delete-products")
    public String deleteProducts() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }

    @GetMapping("delete-orders")
    public String deleteOrders() {
        jdbcTemplate.execute("DELETE FROM license");

        return "OK";
    }
}

//서버에 요청 뭘로? 유저아디로 보낸다 트레이너가 수락 강의 채결 !! 토큰 준다. 토큰 서버에서 겟 바이 알람 요청
//
