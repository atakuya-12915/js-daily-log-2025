⭐️ユーザー認証情報取得サービス
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   private final UserRepository userRepository; // DBアクセス用

  public UserDetailsServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository; // DIでUserRepository注入
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      try {
          User user = userRepository.findByEmail(email); // メールアドレスでユーザ検索
          String userRoleName = user.getRole().getName(); // 役割名を取得
          Collection<GrantedAuthority> authorities = new ArrayList<>();
          authorities.add(new SimpleGrantedAuthority(userRoleName)); // Spring Security用権限を生成
          return new UserDetailsImpl(user, authorities); // UserDetails実装を返す
      } catch (Exception e) {
          throw new UsernameNotFoundException("ユーザーが見つかりませんでした。"); // ユーザなし例外を投げる
      }
  }
}

流れ：
    ①WebSecurityConfig.java により認証機能が実行
    ②loadUserByUsername が呼ばれてDBからユーザ情報を取得。