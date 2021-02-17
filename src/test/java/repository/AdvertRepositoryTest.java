package repository;

public class AdvertRepositoryTest {
  /*  private final Logger logger = LogManager.getLogger(AdvertRepositoryTest.class);

    private Advert advert1;
    private Advert advert2;
    private Advert advert3;
    private Advert advert4;
    private Advert advert5;
    private Advert advert6;
    private Advert expected1;
    private Advert expected2;
    private Advert expected3;
    private Advert expected4;
    private Advert expected5;
    private Advert expected6;

    private final AdvertRepository advertRepository = new AdvertRepository();

    private static int countOfRecordsAdverts = 0;

    private final List<Advert> adverts = new ArrayList<>();

    @BeforeSuite
    public void createData() {
        User user1 = new User("Anna", "Barb", "anna@g.com", LocalDate.now(), true, Role.ADMIN);
        User user2 = new User("Viktoria", "Barb", "viktoriya@g.com", LocalDate.now(), true, Role.USER);
        User user3 = new User("Karla", "Barb", "karla@g.com", LocalDate.now(), true, Role.USER);
        User user4 = new User("Albina", "Barb", "albina@g.com", LocalDate.now(), true, Role.ANONIM);
        CrudRepository<User> userRepository = new UserRepository();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        advert1 = new Advert("Title 1", "Description1", user1, LocalDate.now(), LocalDate.now(), Heading.GAMES, true);
        advert2 = new Advert("Title 2", "Description2", user2, LocalDate.now(), LocalDate.now(), Heading.HAPPINESS, false);
        advert3 = new Advert("Title 3", "Description3", user1, LocalDate.now(), LocalDate.now(), Heading.PROGRAMING, true);
        advert4 = new Advert("Title 4", "Description4", user1, LocalDate.now(), LocalDate.now(), Heading.GAMES, true);
        advert5 = new Advert("Title 5", "Description5", user3, LocalDate.now(), LocalDate.now(), Heading.HAPPINESS, false);
        advert6 = new Advert("Title 6", "Description6", user4, LocalDate.now(), LocalDate.now(), Heading.PROGRAMING, true);

        expected6 = new Advert(1L, "Title 1", "Description1", user1, LocalDate.now(), LocalDate.now(), Heading.GAMES, true);
        expected5 = new Advert(2L, "Title 2", "Description2", user2, LocalDate.now(), LocalDate.now(), Heading.HAPPINESS, false);
        expected4 = new Advert(3L, "Title 3", "Description3", user1, LocalDate.now(), LocalDate.now(), Heading.PROGRAMING, true);
        expected3 = new Advert(4L, "Title 4", "Description4", user1, LocalDate.now(), LocalDate.now(), Heading.GAMES, true);
        expected2 = new Advert(5L, "Title 5", "Description5", user3, LocalDate.now(), LocalDate.now(), Heading.HAPPINESS, false);
        expected1 = new Advert(6L, "Title 6", "Description6", user4, LocalDate.now(), LocalDate.now(), Heading.PROGRAMING, true);

        adverts.addAll(Arrays.asList(advert1, advert2, advert3, advert4, advert5, advert6));
    }

    @BeforeMethod
    public void countOfRecords() {
        countOfRecordsAdverts = advertRepository.findAll().size();
    }

    @DataProvider(name = "testDataForUpdate")
    public Object[][] getTestDataForUpdate() {
        return new Object[][]{{advert1, expected1.getId()}, {advert2, expected2.getId()}, {advert3, expected3.getId()}, {advert4, expected4.getId()}, {advert5, expected5.getId()}, {advert6, expected6.getId()}};
    }

    @DataProvider(name = "testDataForSaving")
    public Object[] getTestDataForSaving() {
        return new Object[]{advert1, advert2, advert3, advert4, advert5, advert6};
    }

    @Test(dataProvider = "testDataForSaving")
    public void testSavingData(Advert advert) {
        assertEquals(advert, advertRepository.save(advert));
    }

    @Test
    public void testFindAll() {
        assertEquals(countOfRecordsAdverts, advertRepository.findAll().size());
    }

    @Test(dataProvider = "testDataForUpdate")
    public void testUpdate(Advert advert, Long id) {
        advertRepository.save(advert1);
        advertRepository.save(advert4);
        advert.setActive(false);
        advert.setId(id);
        assertEquals(advert, advertRepository.update(advert));
    }

    @Test
    public void testDelete1() {
        assertTrue(advertRepository.delete(expected1.getId()), "Need true, shows correct deleting");
    }

    @Test
    public void testDelete2() {
        assertTrue(advertRepository.delete(expected1.getId()), "Need true, shows correct deleting");
    }

    @Test
    public void testFindAllByDateByDesc() {

    }

    @Test
    public void testFindAllByDateByAsc() {

    }

    @Test
    public void testFindByParamAndValueLike() {

    }

    @Test
    public void testFindByParamAndValueEquals() {

    }

    @BeforeClass
    public void createTable() {
        ConfigConnection config = new ConfigConnection();
        try {
            Method method = ConfigConnection.class.getDeclaredMethod("createTableUser");
            method.setAccessible(true);
            assertTrue((boolean) method.invoke(config), "Need true, because it creates table users");
            method.setAccessible(false);

            method = ConfigConnection.class.getDeclaredMethod("createTableAdvert");
            method.setAccessible(true);
            assertTrue((boolean) method.invoke(config), "Need true, because it creates table adverts");
            method.setAccessible(false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage());
        }
    }

    @AfterClass
    public void cleanTable() {
        ConfigConnection config = new ConfigConnection();
        try {
            Method method = ConfigConnection.class.getDeclaredMethod("dropTables");
            method.setAccessible(true);
            assertTrue((boolean) method.invoke(config), "Need true, because it drops");
            method.setAccessible(false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage());
        }
    }*/
}
