@Controller
public class AuthController {
   private final UserService userService; // ユーザ登録・認証ロジックを呼び出すサービスクラス

  // コンストラクタでUserServiceをDI（依存性注入）
  public AuthController(UserService userService) {
      this.userService = userService;
  }

  @GetMapping("/login") // GETリクエストで/loginにアクセスされたら
  public String login() {
      return "auth/login"; // ログイン画面（templates/auth/login.html）を表示
  }

  @GetMapping("/signup") // GETリクエストで/signupにアクセスされたら
  public String signup(Model model) {
      model.addAttribute("signupForm", new SignupForm()); // 空のフォームオブジェクトをモデルにセット
      return "auth/signup"; // 会員登録画面（templates/auth/signup.html）を表示
  }

  @PostMapping("/signup") // POSTリクエストで/signupにアクセスされたら（フォーム送信）
  public String signup(@ModelAttribute @Validated SignupForm signupForm, // 入力されたフォームデータを取得しバリデーション実行
                        BindingResult bindingResult, // バリデーション結果の格納先
                        RedirectAttributes redirectAttributes, // リダイレクト時に一時的なメッセージを渡すためのオブジェクト
                        Model model) // Viewにデータを渡すためのモデル
  {
      // すでに登録済みのメールかチェック。登録済みならエラーを追加
      if (userService.isEmailRegistered(signupForm.getEmail())) {
          FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスす。");
          bindingResult.addError(fieldError);
      }

      // パスワードと確認用パスワードが不一致ならエラーを追加
      if (!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
          FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
          bindingResult.addError(fieldError);
      }

      // 何かしらの入力エラーがあれば、再度フォーム画面に戻す
      if (bindingResult.hasErrors()) {
          model.addAttribute("signupForm", signupForm); // 入力済み内容を保持して戻す
          return "auth/signup";
      }

      // 入力に問題なければ新規ユーザを作成
      userService.createUser(signupForm);

      // 成功メッセージをフラッシュスコープに保存（リダイレクト先で1回だけ表示可能）
      redirectAttributes.addFlashAttribute("successMessage", "会員登録が完了しました。");

      // トップページへリダイレクト
      return "redirect:/";
  }
}

役割：
    ・会員登録・ログイン画面を提供し、会員登録フォーム送信を処理。

    ・MVCの「Controller」層で、フォームのバリデーション・エラー処理を担当。
      // 入力チェック後、問題なければ「UserService」の createUser を呼び出してDB登録を実施。
      // 成功時はリダイレクトしてメッセージを表示する仕組み。


Controllerが行う処理の流れ
    ①「Webからのリクエスト受け取る」 
        GETリクエスト
          ログイン：@GetMapping("/login")
            → return "auth/login";
          会員登録：@GetMapping("/signup")
            → return "auth/signup";
        POSTリクエスト
          フォーム送信：@PostMapping("/signup")
            → バリデーション処理実行
              (@ModelAttribute @Validated SignupForm signupForm, // 入力されたフォームデータを取得しバリデーション実行
                                BindingResult bindingResult, // バリデーション結果の格納先
                                RedirectAttributes redirectAttributes, // リダイレクト時に一時的なメッセージを渡すためのオブジェクト
                                Model model) // Viewにデータを渡すためのモデル

    ②「フォームの入力値検証（バリデーション）」
        If文でバリデーションの条件式を記述
          1）Emailの重複チェック(isEmailRegistered)
              if (userService.isEmailRegistered(signupForm.getEmail())) {
                  FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスす。");
                  bindingResult.addError(fieldError);
              }

          2）Passwordの一致チェック(isSamePassword)
              if (!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
                  FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
                  bindingResult.addError(fieldError);
              }

          3）Error時にフォーム画面に戻す(hasErrors)
              if (bindingResult.hasErrors()) {
                  model.addAttribute("signupForm", signupForm); // 入力済み内容を保持して戻す
                  return "auth/signup";
              }

    ③「サービス層の呼び出し」
          createUser()  入力に問題なければ新規ユーザを作成
              userService.createUser(signupForm);

    ④「結果のView表示 or リダイレクト」
              redirectAttributes.addFlashAttribute("successMessage", "会員登録が完了しました。");
              return "redirect:/";

    ⭐️入力エラー時は同じフォーム画面に戻り、エラー内容と入力済み値を保持して再表示する。
      成功時のリダイレクト先は「PRGパターン（Post/Redirect/Get）」で画面リロードによる二重送信を防ぐ。