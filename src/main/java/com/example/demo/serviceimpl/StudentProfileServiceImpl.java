@Service
public class StudentProfileServiceImpl {

    private final StudentProfileRepository repository;
    private final UserRepository userRepository;

    public StudentProfileServiceImpl(StudentProfileRepository repository,
                                     UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public StudentProfile create(StudentProfile profile) {

        User user = profile.getUser();

        // ✅ IF USER ID IS NULL → CREATE NEW USER
        if (user.getId() == null) {
            user = userRepository.save(user);
        } 
        // ✅ IF USER ID EXISTS → FETCH FROM DB
        else {
            user = userRepository.findById(user.getId())
                    .orElseThrow(() ->
                            new RuntimeException("User not found with id " + user.getId()));
        }

        profile.setUser(user);
        return repository.save(profile);
    }
}
