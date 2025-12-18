@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "STUDENT";

    private Timestamp createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
