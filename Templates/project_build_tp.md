🌱 Spring Boot 手順書（Eclipse版）
🧭 共通：プロジェクトの作成手順（初回のみ）
1. Eclipseの起動
2. ワークスペースの選択　→ [spring_boot]で作成済
3. 新規Spring・スターター・プロジェクト作成（SpringInitiaizr / パッケージ作成）
4. 基本情報の設定（クラスファイル作成）
5. 依存関係の設定（SpringBootDevTools,SpringWeb,Lombok,Thymeleaf,MySQLDriver,SpringDataJPA,Validation,セキュリティ：SpringSecurity）
6. application.propertiesへの追記（アプリの起動時に読み込まれる）
7. pom.xmlへの追記（Mavenのビルド方法を決めるための設定ファイル）

# pom.xml(追加する内容)
ビルド時には、依存関係に指定されたものをMavenがダウンロードしてくれるが、ライブラリやフレームワークなどを追加したい場合は、下記のように編集すればOK。
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

# application.properties(追加する内容)
spring.application.name=****

spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:8889/spring_db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.sql.init.encoding=utf-8
spring.sql.init.mode=always

#接続するデータベースのURL jdbc:[データベースの種類]://[ホスト名]:[ポート番号]/[データベース名]
#データベースに接続するユーザー名(XAMPPやMAMPの初期設定はroot)
#データベースに接続するパスワード(MAMPはroot)
#使用するJDBCドライバ（Javaからデータベースに接続するためのソフトウェア）のクラス名
#SQL実行用ファイルを処理するときのエンコード設定（文字の変換方式）。アプリ起動時にSQL文を自動実行する機能で使う
#前述のSQL実行用ファイルでSQL文を実行するかどうかの設定。alwaysを設定すると、アプリ起動時に毎回、ファイル内のSQL文が実行される。

# controller クラスファイル
package com.example.springpractiece.controller;
// アノテーション（@から始まるコード） を使えるようにする。
import org.springframework.web.bind.annotation.GetMapping;
// クラスにRESTコントローラの機能を持たせる。(Viewを介さないとき)
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	@GetMapping("/")	// GETリクエストをメソッドに紐づける。
	public String first() {
    // RESTコントローラのとき(HTMLファイルを介さずに直接テキストを表示)
		return "Hello, world!";  // 表示させたい[テキスト]を入力
    // 通常のコントローラのとき(HTMLファイルを介してテキスト内容を表示)
    return "firstView";     // HTMLファイル名（拡張子不要）を記述
	}
}

# main クラスファイル
package com.example.****;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringPracticeApplication.class, args);
	}
}


MVCのポイント
# 🧩 モデル(Model)
・役割：データの入れ物＆ビジネスロジック
  ・Javaクラス、DBとのやりとり  
  ・アプリで使うデータの入れ物
  ・DBと連携したり、計算・変換などの「ビジネスロジック」も担当

📌 「データをどう持つか・扱うか」を決めるクラス！
  ```
  public class User {
    private String name;
    private int age;
    // getter, setter
  }
  ```

# 🖥️ ビュー(View) 
・役割：ユーザーに見せる画面（HTML）を出力
  ・ユーザーに見せる画面（HTML）を作る
  ・コントローラから渡されたデータを表示する
  ・Thymeleaf：JavaとHTMLを合体させてビューを出力する

📌 データを「見た目」として表示する役割！(Thymeleaf)
  ```
  <!-- このファイルで、Thymeleafを使うことを宣言 -->
  <html xmlns:th="https://www.thymeleaf.org">
  ```

# 🎮 コントローラ(Controller)
・役割：リクエストとレスポンスをつなぐ司令塔
  ・ユーザーからのリクエスト（URLアクセス）を受け取る
  ・必要に応じてモデルを呼び出し、データを取得
  ・結果をビューに渡して、レスポンスを返す

📌 ユーザーからの入力と、表示する画面をつなぐ橋渡し役！
  ```
  @GetMapping("/hello")
  public String hello(Model model) {
    model.addAttribute("name", "Taro");
    return "hello"; // → hello.htmlを表示
  }
  ```

  ⭐️@GetMappingのルートアドレス["/"]について
  ["/"]は[home]を意味する。
  →デフォルトとしてこの記載でOK ※ただし、プロジェクト内で使えるのは「１度のみ」。[/home]など、2回目以降は変える。
  また、[/]以降は任意の命名でOK。

  - 🔍 例えるなら…
  1. URL [/numbers]など は「レストランの入り口のドア」
  2. コントローラの return ["numberList"] は「そのドアを開けたらどの部屋（HTML）に通すか」
  3. [numberList.html] は「実在する部屋」


  | 項目    | Model（モデル）  | View（ビュー）           | Controller（コントローラ）    |
| ----- | ----------- | ------------------- | --------------------- |
| 役割    | データを持つ＆処理する | データを見せる（画面）         | 入力を受けて処理の流れを制御する      |
| 中身    | Javaクラス     | HTML（+ Thymeleaf等）  | Javaメソッド              |
| 例     | `User.java` | `index.html`        | `HomeController.java` |
| 開発の流れ | DB → Model  | Model → View へデータ表示 | 画面とデータの橋渡し            |


# ビュー
1. Thymeleafの使用宣言
2. HTML要素に属性を追加
  Thymeleafを使う場合、HTML要素に属性を追加する。（thはThymeleafの略）
  ```
  th:属性名=値
  ```
  テキストデータを出力する場合は、th:text属性を使う。
  ```
  <p th:text="侍太郎"></p>
  ```
3. spEL（Spring式言語）
  条件式、計算式を評価（処理）してくれるフレームワーク機能
  ```
  th:属性名=${式}
  <p th:text="${'侍' + '太郎'}"></p>
  <!-- コントローラから、変数numberのデータを送信すれば下記で画面に出力可能画面に出力が可能 -->
  <p th:text="${number}"></p>
  ```

4. ユーティリティオブジェクト（#〇〇）
  ```
  #ユーティリティオブジェクト名 
  ${#numbers.sequence(1,5)} → Javaでいう、int[] numberArray = {1,2,3,4,5};
  #numbers,#strings,#dates,#lists など
  ```

