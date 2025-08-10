@Service // Springのサービス層コンポーネントとして登録
public class UserService {
  private final UserRepository userRepository; // Userエンティティ用のDBアクセス
  private final RoleRepository roleRepository; // Roleエンティティ用のDBアクセス
  private final PasswordEncoder passwordEncoder; // パスワードを安全にハッシュ化

  // コンストラクタで依存オブジェクトを受け取る
  public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.passwordEncoder = passwordEncoder;
  }

  @Transactional // メソッド内のDB操作を1つのトランザクションとして実行
  public User createUser(SignupForm signupForm) {
      User user = new User(); // 新しいユーザインスタンス作成
      Role role = roleRepository.findByName("ROLE_FREE_MEMBER"); // 無料会員ロールを取得

      // フォーム入力値をUserエンティティにセット
      user.setName(signupForm.getName());
      user.setFurigana(signupForm.getFurigana());
      user.setPostalCode(signupForm.getPostalCode());
      user.setAddress(signupForm.getAddress());
      user.setPhoneNumber(signupForm.getPhoneNumber());

      // 誕生日が入力されていれば変換してセット
      if (!signupForm.getBirthday().isEmpty()) {
          user.setBirthday(LocalDate.parse(signupForm.getBirthday(), DateTimeFormatter.ofPattern("yyyyMMdd")));
      } else {
          user.setBirthday(null);
      }

      // 職業が入力されていればセット
      if (!signupForm.getOccupation().isEmpty()) {
          user.setOccupation(signupForm.getOccupation());
      } else {
          user.setOccupation(null);
      }

      user.setEmail(signupForm.getEmail());
      user.setPassword(passwordEncoder.encode(signupForm.getPassword())); // パスワードをハッシュ化
      user.setRole(role); // ユーザにロールを紐付け
      user.setEnabled(true); // 有効ユーザとして設定

      return userRepository.save(user); // DBに保存して返す
  }

  // メールアドレスが登録済みか確認
  public boolean isEmailRegistered(String email) {
      User user = userRepository.findByEmail(email);
      return user != null; // nullでなければ登録済み
  }

  // パスワード一致確認
  public boolean isSamePassword(String password, String passwordConfirmation) {
      return password.equals(passwordConfirmation);
  }
}

// 役割：
  // フォーム入力値からUserエンティティを組み立て、パスワードのハッシュ化やロール付与を行い、DBに保存する。

  
// 連携関係：
  // UserRepository（DB保存・検索）

  // RoleRepository（会員ロール取得）
  
  // User / Role エンティティ（DBテーブルとの対応）
  
  // SignupForm（コントローラ層から受け取るフォームデータ）


  // DBとの連携：
    // usersテーブル : 全てのカラムに、フォーム入力結果やデフォルト値をセット
      // role_id は [RoleRepository.findByName] で取得したRoleのIDが設定される
  
    // rolesテーブル
      // ROLE_FREE_MEMBER などのロール名を事前に登録しておく必要あり