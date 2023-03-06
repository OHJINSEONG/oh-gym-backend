package megatera.makaoGymbackEnd.backdoor;

import megatera.makaoGymbackEnd.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

        jdbcTemplate.execute("DELETE FROM notification");
        jdbcTemplate.execute("DELETE FROM chat");
        jdbcTemplate.execute("DELETE FROM chatting_room");
        jdbcTemplate.execute("DELETE FROM pt_ticket");
        jdbcTemplate.execute("DELETE FROM locker_ticket");
        jdbcTemplate.execute("DELETE FROM locker");
        jdbcTemplate.execute("DELETE FROM exercise_set");
        jdbcTemplate.execute("DELETE FROM exercise");
        jdbcTemplate.execute("DELETE FROM diary");
        jdbcTemplate.execute("DELETE FROM work");
        jdbcTemplate.execute("DELETE FROM request");
        jdbcTemplate.execute("DELETE FROM trainer");
        jdbcTemplate.execute("DELETE FROM person");
        jdbcTemplate.execute("DELETE FROM option");
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM orders");
        jdbcTemplate.execute("DELETE FROM lecture");

        for (int i = 0; i < 30; i += 1) {
            Long id = (long) (i + 1);

            jdbcTemplate.update("INSERT INTO " +
                            "orders(" +
                            "id, user_id, option_id, product_id , type, " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, 1, 1, 1, 'PT', ? ,?)",
                    id,
                    LocalDateTime.of(2022, 12, 30 - i, 0, 0), now
            );
        }

        for (int i = 0; i < 30; i += 1) {
            Long id = (long) (i + 31);

            jdbcTemplate.update("INSERT INTO " +
                            "orders(" +
                            "id, user_id, option_id, product_id , type, " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, 1, 1, 1, 'PT', ? ,?)",
                    id,
                    LocalDateTime.of(2022, 11, 30 - i, 0, 0), now
            );
        }

        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer(new UserName("정재"), new Name("김선우"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213421150-04c6d49c-823e-4db8-bdd2-7f8fb2fc1c3b.jpeg", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("크리스"), new Name("크리스 범스테드"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213080752-afa8724b-2864-419e-a08d-364750c95b22.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("브랜든"), new Name("브랜든 핸드릭슨"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213081295-8e3fd835-ac4a-439b-bc61-41b77d1b75be.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("정대진"), new Name("정대진"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213081430-c786bb7c-6198-4b58-940b-9f6ce3e73e04.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("조준"), new Name("조준"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213082140-3de745a0-c676-472e-9bd7-b0813f781c48.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("김강민"), new Name("김강민"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213082716-39c44e05-6e2c-49a6-a0b2-394457b3c5dc.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("김민수"), new Name("김민수"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213082965-943fc180-d409-429e-b4c8-90c7e0a0c040.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("최봉석"), new Name("최봉석"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213083155-76d005ae-a408-41a1-82af-c4e257d411fe.png",LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("구건모"), new Name("구건모"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213627341-49beaba1-fd03-4373-b447-ce4bd4b9e630.png",LocalTime.parse("09:00"), LocalTime.parse("18:00")));

        long trainerIndex = 1L;
        for (Trainer trainer : trainers) {
            jdbcTemplate.update("INSERT INTO " +
                            "trainer(" +
                            "id, user_name, name, phone_number, age, gender, start_time, end_time, image ,status, " +
                            "created_at, updated_at" +
                            ")" +
                            "VALUES(?, ?, ?, '01085568955', ?, '남자', ?, ?, ?, 'CREATED', ?, ?)",
                    trainerIndex, trainer.userName().value(), trainer.name().value(), trainer.age().value(), trainer.startTime().toString(), trainer.endTime().toString(), trainer.image(),
                    now, now
            );
            trainerIndex += 1L;
        }

        List<Option> options = new ArrayList<>();
        Option option1 = new Option(1L, new Count(0L), new Amount(100000L), 30L);
        Option option2 = new Option(1L, new Count(0L), new Amount(180000L), 90L);
        Option option3 = new Option(1L, new Count(0L), new Amount(240000L), 180L);
        Option option4 = new Option(1L, new Count(0L), new Amount(360000L), 360L);
        Option option5 = new Option(2L, new Count(0L), new Amount(30000L), 90L);
        Option option6 = new Option(3L, new Count(12L), new Amount(720000L), 60L);
        Option option7 = new Option(4L, new Count(12L), new Amount(360000L), 60L);
        Option option8 = new Option(5L, new Count(12L), new Amount(360000L), 60L);
        Option option9 = new Option(6L, new Count(12L), new Amount(360000L), 60L);
        Option option10 = new Option(7L, new Count(12L), new Amount(360000L), 60L);
        Option option11 = new Option(8L, new Count(12L), new Amount(360000L), 60L);
        Option option12 = new Option(9L, new Count(12L), new Amount(360000L), 60L);
        Option option13 = new Option(10L, new Count(12L), new Amount(360000L), 60L);

        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        options.add(option6);
        options.add(option7);
        options.add(option8);
        options.add(option9);
        options.add(option10);
        options.add(option11);
        options.add(option12);
        options.add(option13);

        List<Product> products = new ArrayList<>();
        Product product1 = new Product(new Title("이용권"), 1L, new Category("MEMBERSHIP"));
        Product product2 = new Product(new Title("락카"), 1L, new Category("LOCKER"));
        Product product3 = new Product(new Title("피티"), 1L, new Category("PT"));
        Product product4 = new Product(new Title("피티"), 2L, new Category("PT"));
        Product product5 = new Product(new Title("피티"), 3L, new Category("PT"));
        Product product6 = new Product(new Title("피티"), 4L, new Category("PT"));
        Product product7 = new Product(new Title("피티"), 5L, new Category("PT"));
        Product product8 = new Product(new Title("피티"), 6L, new Category("PT"));
        Product product9 = new Product(new Title("피티"), 7L, new Category("PT"));
        Product product10 = new Product(new Title("피티"), 8L, new Category("PT"));

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);

        for (int i = 0; i < products.size(); i += 1) {
            Long id = (long) (i + 1);
            jdbcTemplate.update("INSERT INTO " +
                            "product(" +
                            "id, title, trainer_id, type , " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, ?, ?, ?, ?, ?)",
                    id, products.get(i).title().value(), products.get(i).trainerId(), products.get(i).type().value(),
                    now, now
            );
        }

        for (int i = 0; i < options.size(); i += 1) {
            Long id = (long) (i + 1);

            jdbcTemplate.update("INSERT INTO " +
                            "option(" +
                            "id, product_id, price, pt_times, date_of_use, " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)",
                    id,
                    options.get(i).productId(),
                    options.get(i).price().value(),
                    options.get(i).ptTimes().value(),
                    options.get(i).dateOfUse(),
                    now, now
            );
        }

        for (int i = 0; i < 100; i += 1) {
            Long id = (long) (i + 1);

            jdbcTemplate.update("INSERT INTO " +
                            "locker(" +
                            "id, user_id, locker_number, status, " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, null, ?, 'AVAILABLE', ? ,?)",
                    id,
                    id,
                    now, now
            );
        }

        return "Ok";
    }

    @GetMapping("setup-database-products")
    public String setupDataProducts() {
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.execute("DELETE FROM option");
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM trainer");

        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer(new UserName("정재"), new Name("김선우"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213421150-04c6d49c-823e-4db8-bdd2-7f8fb2fc1c3b.jpeg", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("크리스"), new Name("크리스 범스테드"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213080752-afa8724b-2864-419e-a08d-364750c95b22.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("브랜든"), new Name("브랜든 핸드릭슨"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213081295-8e3fd835-ac4a-439b-bc61-41b77d1b75be.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("정대진"), new Name("정대진"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213081430-c786bb7c-6198-4b58-940b-9f6ce3e73e04.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("조준"), new Name("조준"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213082140-3de745a0-c676-472e-9bd7-b0813f781c48.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("김강민"), new Name("김강민"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213082716-39c44e05-6e2c-49a6-a0b2-394457b3c5dc.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("김민수"), new Name("김민수"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"),"https://user-images.githubusercontent.com/107606892/213082965-943fc180-d409-429e-b4c8-90c7e0a0c040.png", LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("최봉석"), new Name("최봉석"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213083155-76d005ae-a408-41a1-82af-c4e257d411fe.png",LocalTime.parse("09:00"), LocalTime.parse("18:00")));
        trainers.add(new Trainer(new UserName("구건모"), new Name("구건모"), new PhoneNumber("01085568965"), new Age("971117-1932201"), new Gender("남자"), "https://user-images.githubusercontent.com/107606892/213872158-675ab424-b5d7-4fbb-8a8e-0ffb55ddd093.jpeg",LocalTime.parse("09:00"), LocalTime.parse("18:00")));

        long trainerIndex = 1L;
        for (Trainer trainer : trainers) {
            jdbcTemplate.update("INSERT INTO " +
                            "trainer(" +
                            "id, user_name, name, phone_number, age, gender, start_time, end_time, image ,status, " +
                            "created_at, updated_at" +
                            ")" +
                            "VALUES(?, ?, ?, '01085568955', ?, '남자', ?, ?, ?, 'CREATED', ?, ?)",
                    trainerIndex, trainer.userName().value(), trainer.name().value(), trainer.age().value(), trainer.startTime().toString(), trainer.endTime().toString(), trainer.image(),
                    now, now
            );
            trainerIndex += 1L;
        }

        List<Option> options = new ArrayList<>();
        Option option1 = new Option(1L, new Count(0L), new Amount(100000L), 30L);
        Option option2 = new Option(1L, new Count(0L), new Amount(180000L), 90L);
        Option option3 = new Option(1L, new Count(0L), new Amount(240000L), 180L);
        Option option4 = new Option(1L, new Count(0L), new Amount(360000L), 360L);

        Option option6 = new Option(3L, new Count(12L), new Amount(720000L), 60L);
        Option option7 = new Option(4L, new Count(12L), new Amount(360000L), 60L);
        Option option8 = new Option(5L, new Count(12L), new Amount(360000L), 60L);
        Option option9 = new Option(6L, new Count(12L), new Amount(360000L), 60L);
        Option option10 = new Option(7L, new Count(12L), new Amount(360000L), 60L);
        Option option11 = new Option(8L, new Count(12L), new Amount(360000L), 60L);
        Option option12 = new Option(9L, new Count(12L), new Amount(360000L), 60L);
        Option option13 = new Option(10L, new Count(12L), new Amount(360000L), 60L);

        Option option14 = new Option(2L, new Count(0L), new Amount(5000L), 30L);
        Option option15 = new Option(2L, new Count(0L), new Amount(15000L), 90L);
        Option option16 = new Option(2L, new Count(0L), new Amount(25000L), 180L);

        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        options.add(option6);
        options.add(option7);
        options.add(option8);
        options.add(option9);
        options.add(option10);
        options.add(option11);
        options.add(option12);
        options.add(option13);
        options.add(option14);
        options.add(option15);
        options.add(option16);


        List<Product> products = new ArrayList<>();
        Product product1 = new Product(new Title("이용권"), 1L, new Category("MEMBERSHIP"));
        Product product2 = new Product(new Title("락카"), 1L, new Category("LOCKER"));
        Product product3 = new Product(new Title("피티"), 1L, new Category("PT"));
        Product product4 = new Product(new Title("피티"), 2L, new Category("PT"));
        Product product5 = new Product(new Title("피티"), 3L, new Category("PT"));
        Product product6 = new Product(new Title("피티"), 4L, new Category("PT"));
        Product product7 = new Product(new Title("피티"), 5L, new Category("PT"));
        Product product8 = new Product(new Title("피티"), 6L, new Category("PT"));
        Product product9 = new Product(new Title("피티"), 7L, new Category("PT"));
        Product product10 = new Product(new Title("피티"), 8L, new Category("PT"));

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);

        for (int i = 0; i < products.size(); i += 1) {
            Long id = (long) (i + 1);
            jdbcTemplate.update("INSERT INTO " +
                            "product(" +
                            "id, title, trainer_id, type , " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, ?, ?, ?, ?, ?)",
                    id, products.get(i).title().value(), products.get(i).trainerId(), products.get(i).type().value(),
                    now, now
            );
        }

        for (int i = 0; i < options.size(); i += 1) {
            Long id = (long) (i + 1);

            jdbcTemplate.update("INSERT INTO " +
                            "option(" +
                            "id, product_id, price, pt_times, date_of_use, " +
                            "created_at, updated_at" +
                            ") " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)",
                    id,
                    options.get(i).productId(),
                    options.get(i).price().value(),
                    options.get(i).ptTimes().value(),
                    options.get(i).dateOfUse(),
                    now, now
            );
        }
        return "OK";
    }

    @GetMapping("delete-all")
    public String deleteAll() {
        jdbcTemplate.execute("DELETE FROM chat");
        jdbcTemplate.execute("DELETE FROM chatting_room");
        jdbcTemplate.execute("DELETE FROM pt_ticket");
        jdbcTemplate.execute("DELETE FROM locker_ticket");
        jdbcTemplate.execute("DELETE FROM locker");
        jdbcTemplate.execute("DELETE FROM exercise_set");
        jdbcTemplate.execute("DELETE FROM exercise");
        jdbcTemplate.execute("DELETE FROM diary");
        jdbcTemplate.execute("DELETE FROM work");
        jdbcTemplate.execute("DELETE FROM request");
        jdbcTemplate.execute("DELETE FROM trainer");
        jdbcTemplate.execute("DELETE FROM person");
        jdbcTemplate.execute("DELETE FROM option");
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM orders");
        jdbcTemplate.execute("DELETE FROM lecture");
        jdbcTemplate.execute("DELETE FROM notification");

        return "Delete All";
    }

    @GetMapping("delete-products")
    public String deleteProducts() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }

    @GetMapping("delete-diary")
    public String deleteDiary() {
        jdbcTemplate.execute("DELETE FROM exercise_set");
        jdbcTemplate.execute("DELETE FROM exercise");
        jdbcTemplate.execute("DELETE FROM diary");

        return "delete-diary";
    }

    @GetMapping("delete-orders")
    public String deleteOrders() {
        jdbcTemplate.execute("DELETE FROM orders");

        return "OK";
    }

    @GetMapping("delete-works")
    public String deleteWorks() {
        jdbcTemplate.execute("DELETE FROM work");

        return "OK";
    }
}

//서버에 요청 뭘로? 유저아디로 보낸다 트레이너가 수락 강의 채결 !! 토큰 준다. 토큰 서버에서 겟 바이 알람 요청
//
