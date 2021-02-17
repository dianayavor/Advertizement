package repository;

public class UserRepositoryTest {
   /* private final Logger logger = LogManager.getLogger(UserRepositoryTest.class);

	private final User user1 = new User("Anna", "Barb", "anna@g.com",LocalDate.now(), true, Role.ADMIN);
    private final User user2 = new User("Viktoria", "Barb", "viktoriya@g.com", LocalDate.now(), true, Role.USER);

    private final UserRepository userRepository = new UserRepository();

    private int countOfRecordsUsers = 0;

    @BeforeMethod
    public void countOfRecords() {
        countOfRecordsUsers = userRepository.findAll().size();
    }

    @DataProvider(name = "testDataForUpdate")
    public Object[][] getTestDataForUpdate() {
        return new Object[][]{{user1, user2.getId()}, {user2, user1.getId()}};
    }

    @DataProvider(name = "testDataForSaving")
    public Object[] getTestDataForSaving() {
        return new Object[]{user1, user2};
    }

    @Test(dataProvider = "testDataForSaving")
    public void testSavingData(User user) {
        assertEquals(user, userRepository.save(user));
    }

    @Test
    public void testSavingDataForGetKeys() {
        int countOfKey = 0;
        List<User> adverts = List.of(user1, user2);
        for (User advert : adverts) {
            userRepository.save(advert);
            if (advert.getId() != null) {
                countOfKey++;
            }
        }
        assertEquals(adverts.size(), countOfKey);
    }

    @Test
    public void testFindAll() {
        assertEquals(countOfRecordsUsers, userRepository.findAll().size());
    }

    @DataProvider(name = "testDataForFindById")
    public Object[] getDataForTestDataForFindById(){
        return new Object[] {user1, user2};
    }

    @Test(dataProvider = "testDataForFindById", priority = 1)
    public void testFindByIdAdvert(User expected) {
        userRepository.save(expected);
        assertEquals(expected, userRepository.findById(expected.getId()));
    }

    @Test(dataProvider = "testDataForUpdate", priority = 2)
    public void testUpdate(User user, Long id) {
        userRepository.save(user1);
        user.setActiveAccount(false);
        user.setId(id);
        assertEquals(user, userRepository.update(user));
    }

    @Test
    public void testDelete1() {
        userRepository.save(user1);
        assertTrue(userRepository.delete(user1.getId()), "Need true, shows correct deleting");
    }

    @BeforeClass
    public void createTable() {
        ConfigConnection config = new ConfigConnection();
        try {
            Method method = ConfigConnection.class.getDeclaredMethod("createTableUser");
            method.setAccessible(true);
            assertTrue((boolean) method.invoke(config), "Need true, because it creates table users");
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
