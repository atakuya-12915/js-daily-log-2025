@Entity
@Table(name = "users") // 対応するテーブル名（users）
@Data
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
   @Column(name = "id") // users.id
   private Integer id;

   @Column(name = "name") // users.name
   private String name;

   @Column(name = "furigana") // users.furigana
   private String furigana;

   @Column(name = "postal_code") // users.postal_code
   private String postalCode;

   @Column(name = "address") // users.address
   private String address;

   @Column(name = "phone_number") // users.phone_number
   private String phoneNumber;

   @Column(name = "birthday") // users.birthday
   private LocalDate birthday;

   @Column(name = "occupation") // users.occupation
   private String occupation;

   @Column(name = "email") // users.email（UNIQUE制約あり）
   private String email;   // ⭐️UserRepository のfindByEmail()メソッドで取得
   
   @Column(name = "password") // users.password（ハッシュ化前提）
   private String password;

   @ManyToOne // 多対1: 複数ユーザが1つのRoleを持つ
   @JoinColumn(name = "role_id") // users.role_id → roles.id 外部キー
   private Role role;            // ⭐️

   @Column(name = "enabled") // users.enabled
   private Boolean enabled;

   @Column(name = "stripe_customer_id") // users.stripe_customer_id（UNIQUE制約）
   private String stripeCustomerId;

   @Column(name = "created_at", insertable = false, updatable = false) // DB自動設定
   private Timestamp createdAt;

   @Column(name = "updated_at", insertable = false, updatable = false) // DB自動更新
   private Timestamp updatedAt;
}

// @Column で明示マッピング（snake_case ↔ camelCase）

   // roleフィールド　→　RoleRepository のfindByName()メソッドで取得する。[roles.name列]の[ロール名]がセットされる
   // emailフィールド　→　UserRepository のfindByEmail()メソッドで取得する。[users.email列]の[メールアドレス]がセットされる
