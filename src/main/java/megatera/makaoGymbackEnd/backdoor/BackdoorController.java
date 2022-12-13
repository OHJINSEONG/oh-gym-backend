package megatera.makaoGymbackEnd.backdoor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import megatera.makaoGymbackEnd.models.Option;
import megatera.makaoGymbackEnd.models.Product;
import megatera.makaoGymbackEnd.models.UserName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/setup-database")
    public String setupDatabase() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM license");
        jdbcTemplate.execute("DELETE FROM lecture");

        Option ptOption = new Option(12);
        Option empty = new Option((Integer) null);
        Option useOfDateOption = new Option(90);
        String mondayOption = "월 수 금";
        String tuesdayOption = "화 목";
        String timeOption = "11:00";

        List<Product> products = new ArrayList<>();
        products.add(new Product("헬스장 이용권", new UserName("오진성"), useOfDateOption, empty, null, null, 180000));
        products.add(new Product("피티", new UserName("오진욱"), empty, ptOption, timeOption, mondayOption, 360000));

        for (int i = 0; i < 2; i += 1) {
            Long id = (long) i + 1;
            jdbcTemplate.update("INSERT INTO " +
                            "product(" +
                            "id, title, user_name, date_option, pt_option, price, time_of_pt, day_of_week, " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    id, products.get(i).title(), products.get(i).trainer().value(), products.get(i).dateOfUse().value(),
                    products.get(i).ptTimes().value(), products.get(i).price(), products.get(i).timeOfPt(), products.get(i).dayOfWeek(),
                    now, now
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
