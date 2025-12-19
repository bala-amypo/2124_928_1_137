@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountRepository repo;

    public UserAccount createUser(UserAccount user) {
        return repo.save(user);
    }
    public UserAccount getUser(Long id) {
        return repo.findById(id).orElse(null);
    }
}
