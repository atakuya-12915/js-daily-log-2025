Controllerの役割
  ユーザーから「リクエスト」を受け取り、モデルやビューに処理を指示する。
  → 画面の表示を返すか、JSONを返すかを決める。
    ・画面（html）を返す時：@Controller
    ・データ（JSON）を返す時：@RestController（API向け）

  ルーティング：
    @RequestMapping, @GetMapping, @PostMapping で URL とメソッドを割り当てる。

  パラメータ受け取り：
    @PathVariable（URLの一部）、@RequestParam（?a=b の形式）。

  バリデーション：
    @Valid と BindingResult でフォームのチェック。

  例外処理：
    @ExceptionHandler（個別）や @ControllerAdvice（全体）で例外を拾う。


Modelの役割
  ドメインモデル（Entity）：
    データベースの行を表す「もの」（User や Role）。JPA アノテーションで DB と対応させる。

  Webの Model（org.springframework.ui.Model）：
    Controller が View に「渡すデータの箱」。View（HTML）で ${users} のように参照できる。
  

  Repository：
    JpaRepository を継承して CRUD を簡単に使えるようにする場所（DBアクセスの窓口）
      ・URLマッピング（HTTPメソッドの使い分け）：
      ・GET：画面を見せる／データ取得（安全・冪等）
      ・POST：新規作成（フォーム送信）
      ・PUT/PATCH：更新
      ・DELETE：削除

    例外処理：
      @ControllerAdvice と @ExceptionHandler で共通エラーページや JSON エラーを返せる。

    セキュリティ：
      Spring Security で「誰が何をできるか」を決める（認証＝ログイン、認可＝権限）。パスワードは BCrypt 等でハッシュ化する。

DBアクセスの最適化（ページネーション）：Pageable／Page<T> を使って大量データを分割して取得する。


【モデルでの処理の流れ】
① エンティティ（Entity）
  データベースの users テーブルの行を表すクラス。@Entity をつけると JPA がこのクラスと DB をつなぐ。

② リポジトリ（Repository）
  UserRepository extends JpaRepository<User, Long> のように書くだけで、
  保存・検索・ページ毎取得などがすぐ使える。

③ フォームとバインディング
  HTML のフォーム（名前・メール・パスワード）を SignupForm というクラスに詰めて送ると、
  Spring が自動でそのクラスのフィールドに値を入れてくれる（@ModelAttribute と @Valid）。

④ Spring の Model（画面に渡すための箱）
  Controller のメソッドに引数 Model model を入れ、
  model.addAttribute("users", userList) とすると、View の HTML で ${users} として使える。

  ⑤ 検証と結果の戻し方
  @Valid SignupForm form, BindingResult br の順で受ける。もし br.hasErrors() が true なら、同じフォーム画面にエラーメッセージ付きで戻す。

⑥ メール認証（流れ）
  1.新規登録時に enabled = false、verificationToken = UUID を保存。
  2.メールに https://your.site/users/verify?token=xxx を送り、ユーザーがクリックしたら
    token を受け取り enabled = true に変更する。
  3.その後は Spring Security と連携して enabled を確認してログインを許可。

  