⭐️①Spring Securityの設定クラス

@Configuration // Springの設定クラスとして認識される
@EnableWebSecurity // Spring Securityを有効にする
@EnableMethodSecurity // メソッドレベルのセキュリティを有効にする（例: @PreAuthorize）
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        // URLごとのアクセス制御設定
        .authorizeHttpRequests((requests) -> requests
            // ここで指定したURLは誰でもアクセス可能（CSS,画像,JS,トップページ,サインアップページ）
            .requestMatchers("/css/**", "/images/**", "/js/**", "/storage/**", "/", "/signup/**").permitAll()
            // 上記以外のURLはログイン認証が必須（ログインしていないとアクセス不可）
            .anyRequest().authenticated()
        )
        // ログインフォームの設定
        .formLogin((form) -> form
            .loginPage("/login")               // ログインページのURL
            .loginProcessingUrl("/login")      // フォーム送信先URL（Spring Securityが処理）
            .defaultSuccessUrl("/?loggedIn")  // ログイン成功後のリダイレクト先
            .failureUrl("/login?error")        // ログイン失敗時のリダイレクト先
            .permitAll()                      // ログイン画面は誰でもアクセス可
        )
        // ログアウト設定
        .logout((logout) -> logout
            .logoutSuccessUrl("/?loggedOut") // ログアウト後のリダイレクト先
            .permitAll()                     // ログアウトも誰でもアクセス可
        );

        return http.build(); // 設定完了したフィルターを返す
}

    // パスワードのハッシュ化に使うエンコーダーをBean登録
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptアルゴリズムを使用（推奨）
    }
}

役割：
    ・Spring Securityの認証・認可の設定をまとめたクラス。
    ・URLごとのアクセス権限（許可・認証必須）を指定。
    ・ログインページ、ログイン処理、ログイン成功・失敗時のリダイレクト先を設定。
    ・ログアウト後の遷移先も設定。
    ・パスワードはBCryptでハッシュ化（安全対策）。

流れ：
    ①ログイン画面でメールアドレス・パスワード入力。
    ②WebSecurityConfig の設定で /login POST時に認証処理が動く。