⭐️③Spring SecurityのUserDetails実装クラス(implements UserDetails)

public class UserDetailsImpl implements UserDetails {
  private final User user; // 独自ユーザ情報を保持
  private final Collection<GrantedAuthority> authorities; // ユーザのロール情報

  // コンストラクタでUserエンティティとロール情報を受け取る
  public UserDetailsImpl(User user, Collection<GrantedAuthority> authorities) {
      this.user = user;
      this.authorities = authorities;
  }

  // 独自User情報を返すメソッド（必要に応じて使用）
  public User getUser() {
      return user;
  }

  // ハッシュ化済みパスワードを返す（Spring Securityの認証で使用）
  @Override
  public String getPassword() {
      return user.getPassword();
  }

  // ログインに使うユーザ名を返す（ここではemail）
  @Override
  public String getUsername() {
      return user.getEmail();
  }

  // ユーザの権限（ロール）を返す
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
  }

  // 以下はアカウント状態の判定（すべてtrueで有効と見なす）
  @Override
  public boolean isAccountNonExpired() {
      return true; // アカウント期限切れでない
  }

  @Override
  public boolean isAccountNonLocked() {
      return true; // ロックされていない
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true; // パスワード期限切れでない
  }

  @Override
  public boolean isEnabled() {
      return user.getEnabled(); // ユーザの有効フラグを返す
  }
}

流れ：
    ①UserDetailsImpl に変換し、パスワードの照合やロール認可をSpring Securityが実施。
    ②認証成功なら指定の画面へ遷移、失敗ならエラー画面へ。