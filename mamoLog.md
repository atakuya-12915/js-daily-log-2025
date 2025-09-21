プロジェクト概要

Todoシンプル版は、Spring Boot と Thymeleaf を用いた簡易タスク管理アプリです。
未完了タスクと完了タスクをカード風デザインで管理し、CRUD（作成・表示・編集・削除）と完了切替が可能です。
デザインはHome画面風に統一し、ユーザー操作性を重視しています。

主な機能

タスク一覧表示

未完了タスクと完了タスクを分けて表示

完了タスクは折りたたみ可能

未完了タスクには「編集」リンクを表示

タスク新規作成

タイトル、メモ、担当者、期限日・時刻、完了フラグの入力

Home風カードデザインで統一

タスク編集

登録済タスクの内容を更新

削除機能付き（確認プロンプトあり）

完了切替

チェックボックス操作で未完了 ↔ 完了を切替

非同期でDOMを移動させて即座にUI反映

件数表示も動的更新

デザイン統一

Home画面風のカードデザイン（.todo-card, .app-shell, .main-area）

フォームもカード風デザインで統一

CSS共通化による保守性向上

使用技術

Backend: Spring Boot 3, Thymeleaf

Database: H2 / PostgreSQL / MySQL（任意）

Frontend: HTML5, CSS3, JavaScript

補助ライブラリ: Lombok（Entity用のアノテーション簡略化）

ディレクトリ構成（抜粋）
src/
 ├─ main/
 │   ├─ java/.../controller/TodoController.java
 │   ├─ java/.../entity/Todo.java
 │   ├─ java/.../repository/TodoRepository.java
 │   └─ resources/
 │        ├─ templates/
 │        │    ├─ list.html
 │        │    ├─ todo-new.html
 │        │    └─ todo-edit.html
 │        └─ static/css/
 │             ├─ style.css
 │             ├─ todo-new.css
 │             └─ todo-edit.css

アプリ作成手順（概要）

Spring Boot プロジェクトを作成し、依存関係を追加

Todo エンティティと Repository を作成

Controller で CRUD 処理と完了切替を実装

Thymeleaf テンプレートで一覧・新規作成・編集画面を作成

CSS を Home 風デザインで統一

JavaScript でチェックボックス切替・完了タスク折りたたみ・件数更新を実装

CRUD と完了切替の動作確認を行い、UI を微調整

ポートフォリオ向け解説ポイント

非同期更新: チェックボックス操作で DOM を即座に更新

デザイン統一: Home 画面風のカードスタイルとフォーム

再利用性: CSS クラス共通化により保守性が高い

初学者からの学習: Thymeleaf、fetch API、DOM操作、イベント再バインドを習得

想定される質問例と回答

Q1: なぜJSで完了切替を実装したのか？
A1: 「非同期でUIを即座に更新し、ユーザー体験を向上させるため。成功時のみDOMを移動する形にしています。」

Q2: Thymeleafを使うメリットは？
A2: 「サーバーサイドで変数を埋め込め、MVCパターンで簡潔にビューを生成できるためです。」

Q3: CSSクラスを共通化した理由は？
A3: 「Home画面と統一感を持たせ、保守性を高めるためです。」

注意点・躓きポイント（初学者視点）

Thymeleaf の th:field と input の name/ID の不一致に注意

JSでDOM移動後はイベントバインドの再適用が必要

完了タスクを折りたたむ際、件数表示も動的に更新する

フォーム初期値（select, checkbox, tag）の設定に注意

Lombok がIDEで正しく認識されない場合は設定確認

動作イメージ

タスク一覧画面

未完了タスクが縦カードで表示

完了タスクは折りたたみ表示

新規作成フォーム

Home風カードデザインで登録可能

編集画面

編集・削除が可能

完了切替も即座に反映