## この学習ログの目的
* ①振り返り用：1週間ごとにまとめて読んで自身の成長を可視化
* ②言語化：どんな力を意識して伸ばしたかを言語化
* ③モチベーション維持：進捗を可視化することで学習を継続しやすくする

## 7月の目標
📝Java bronze SE試験に合格するために紫本・黒本で勉強

## 今日の学習内容
⚙️言語 Java,Spring Boot

🕐学習時間
📅 日付　2025/07/12
開始時刻　13:00, 18:30
終了時刻　15:30, 20:00
合計学習時間(h)5.0

🎯本日の学習目標
・SpringBoot
・ブロンズ試験勉強

📝学習内容
・SpringBoot（MVC連携復習）
・紫本模試２回目

💡 気づき・理解できたこと
・「依存性」とは、 あるクラス（＝使う側）が他のクラス（＝使われる側）を利用している こと。
・Javaには以下2種類の依存がある。
この2種類の依存では、「使われる側クラス」に変更があった場合、 「使う側クラス」の修正が必要 になる。

❓難しかったこと・疑問
Q.
A. 

📤 アウトプットしたこと
・GitHubに学習ログpush（#今日の積み上げ）

🌱 明日の目標
・SpringBoot基礎復習を重ねる
・Java bronze SEの試験勉強

📈 学習の満足度（1〜5）
☐ 1 ☐ 2 ☐ 3 ☐ 4 ◽️ 5
（理由：つまづいても、時間をかけて理解できているからOK）


L復習

Controller作成
①/user/1 → パスから/user/以降の値：[1]を取り出して出力する
（@PathVariable）
②/user/1 → ①同様のメソッドだが、UserServiceクラスのメソッドを呼び出して実行する
（DI）

Point
@GetMapping("/num/{number}")　← {変数名}でルートパスを書く
※/num/number　と書くと、numberはString型の文字列として扱われる
上記条件で複数のメソッドを定義する場合、毎回/user/{}を書く必要があり面倒。
→@RequestMapping("/user")　を追記することで、
以下のすべての@GetMappingパスに[/user]が暗黙でついた状態となる。
（アクセスURL：http://localhost:8080/user/1）

@RestController で以下のコードを書くと
http://localhost:8080/user/1　でリクエストすると
パスからint型で[1]を取得し、number=1 を戻り値として返す
（=ブラウザに[1]が表示される）
※戻り値がString型以外の場合は、RestControllerで書く。

CとMを連携させて、データを取得する
コントローラーでサービスのDIを行い、サービスのメソッドを呼び出す。
コントローラークラスでコンストラクタを定義。
```
public class UserController {
final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
  }
}
```

他のクラスのメソッドを呼び出す＝インスタンス化が必要！
Javaでは、newで作るが、、、
// の部分は不要。上記のDIでコンストラクタ注入により、UserServiceクラスの
userServiceオブジェクトが使えるようになる。
```
@GetMapping("/{number}")
	public String showName(@PathVariable int number) {
 // UserService userService = new UserService();	// DIでは起動時に自動でインスタンスが作成される
		String newName = userService.showName(number);
		return newName;
	}
  ```