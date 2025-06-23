## この学習ログの目的
* ①振り返り用：1週間ごとにまとめて読んで自身の成長を可視化
* ②言語化：どんな力を意識して伸ばしたかを言語化
* ③モチベーション維持：進捗を可視化することで学習を継続しやすくする

## 今日の学習内容
⚙️言語 Java

🕐学習時間
📅 日付　2025/06/24
開始時刻　5:00
終了時刻　9:00
合計学習時間(h)　4.0

🎯本日の学習目標
・Java基礎演習（抽象クラス、super）

📝学習内容
・抽象クラス、superの復習（理解度UP）

💡 気づき・理解できたこと
・


❓難しかったこと・疑問
Q. superをなぜ使うのか？
A. 継承されたサブクラスで、例外的な処理を加えたい場合に、共通部分のコンストラクタを
   重複定義しないといけないケースに便利。共通部分は、super(引数);でスーパークラスから呼び出せるため、追加処理のみを追記するだけでOK!

# サンプルコード
親：Userクラス
```
public void User{
  // フィールド
  String name;  // ユーザー名
  // コンストラクター
  User(String name){
    this.name = name;
  }
}
```
子：AdminUserクラス
```
public void AdminUser{
  // フィールド
  String role;  // 権限
  // コンストラクター（super継承）
  AdminUser(String name){
    super(name);   // Userクラスのコンストラクタを使用（引数：ローカル変数name）
    this.role = "admin";
    System.out.println("Adminユーザー登録完了：" + name + " / 権限:" + role);
  }
}
```
実行：MainUserクラス
```
public class MainUser {
	public static void main(String[] args) {
		AdminUser admin = new AdminUser("taro");
	}
}
```
- 処理の流れ
①：AdminUserクラス：コンストラクタ呼び出し
   → [admin]インスタンス生成＆初期化(引数："Taro")
②：AdminUserクラスのコンストラクタの[super(name)]実行
    → Userクラス：コンストラクタ内のローカル変数nameに"Taro"を格納
③：Userクラスのnameフィールドにローカル変数nameから"Taro"が代入される
④：②で未処理だった出力処理を実行
    → 出力結果：[Adminユーザー登録完了：Taro / 権限:Admin]

📤 アウトプットしたこと
・GitHubに学習ログpush（#今日の積み上げ）

🌱 明日の目標
・Java基礎の復習を重ねる

📈 学習の満足度（1〜5）
☐ 1 ☐ 2 ☐ 3 ☐ 4 ◽️ 5
（理由：疑問点を理解でき、雲が晴れた！定着するように、復習を繰り返す。）