# 章1 — DB関係（schema.sql / data.sql）

データベース作成準備の工程

---

## 追加したプロジェクト構成（章1）

```
project-root/
├─ src/
│  ├─ main/
│  │  ├─ java/com/example/app/       <-- Javaソース（後で作成する）
│  │  │  ├─ entity/                  <-- User, Role エンティティ（DBテーブルに対応）
│  │  │  ├─ repository/              <-- JpaRepository を置く
│  │  │  ├─ service/                 <-- ビジネスロジック
│  │  │  ├─ controller/              <-- Web層（認証・会員管理等）
│  │  ├─ resources/
│  │  │  ├─ schema.sql               <-- ※今ここ（DB定義）
│  │  │  ├─ data.sql                 <-- 初期データ（rolesなど）
│  │  │  ├─ application.properties
```

---
## schema.sql

```sql
CREATE TABLE IF NOT EXISTS roles ( -- rolesテーブルを作成。アプリで使う権限（例: ROLE_USER, ROLE_ADMIN）を格納します。
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 主キー。自動採番される整数ID。
   name VARCHAR(50) NOT NULL -- ロール名（例: ROLE_USER）。NULL不可。
);

CREATE TABLE IF NOT EXISTS users ( -- usersテーブルを作成。会員（ユーザ）情報を格納します。
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 主キー。ユーザを一意に識別するID。
   name VARCHAR(50) NOT NULL, -- 表示名（必須）。
   furigana VARCHAR(50) NOT NULL, -- ふりがな（必須）。検索や表示補助に使います。
   postal_code VARCHAR(50) NOT NULL, -- 郵便番号。ハイフンや国際形式を考慮してVARCHARにしています。
   address VARCHAR(255) NOT NULL, -- 住所。最大255文字。
   phone_number VARCHAR(50) NOT NULL, -- 電話番号。国番号や記号を含めるためVARCHAR。
   birthday DATE, -- 生年月日（任意）。NULL可。
   occupation VARCHAR(50), -- 職業（任意）。NULL可。
   email VARCHAR(255) NOT NULL UNIQUE, -- メールアドレス（認証用）。重複禁止のUNIQUE制約。
   password VARCHAR(255) NOT NULL, -- パスワード（ハッシュ化した文字列を格納する想定）。
   role_id INT NOT NULL, -- roles テーブルの id を参照する外部キー（ユーザの権限を指定）。
   enabled BOOLEAN NOT NULL, -- アカウント有効フラグ（メール確認や管理者停止に使用）。
   stripe_customer_id VARCHAR(255) UNIQUE, -- Stripe 顧客ID（有料プラン用）。一意制約あり。
   created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 作成日時。デフォルトで現在時刻を設定。
   updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新日時。行更新時に自動更新。
   FOREIGN KEY (role_id) REFERENCES roles (id) -- role_id が roles.id を参照する外部キー制約。
);
```

---

## 解説

### 1) この `schema.sql` の役割

* アプリで使うデータベースの\*\*テーブル定義（スキーマ）\*\*。`roles` と `users` を作成する。
* Spring Boot プロジェクトの `src/main/resources/schema.sql` に置くことで、自動的に実行される（`application.properties` で依存設定が必要）。

### 2) テーブルと今後作る Java クラスの対応関係（概念マップ）

* DB (schema.sql)
  ↓（テーブル）
* Entity（Java の `@Entity` クラス）

  * `roles` テーブル → `Role` エンティティ（例: `Role.id`, `Role.name`）
  * `users` テーブル → `User` エンティティ（`id`, `name`, `email` など）
  * `users.role_id` は `User` 側の `@ManyToOne` / `@JoinColumn(name = "role_id")` で `Role` に紐づく。
    ↓
* Repository（`UserRepository extends JpaRepository<User, Integer>` 等）

  * DB 操作（SELECT/INSERT/UPDATE/DELETE）を担います。
    ↓
* Service（`UserService`）

  * ビジネスロジック（例: 新規登録時にパスワードハッシュ化、Stripe顧客作成など）を実装。
    ↓
* Controller（`AuthController` / `AdminController` 等）

  * Web リクエスト（ログイン、会員登録、管理画面の操作）を受け取り、Service を呼び出す。

### 3) 注意点（重要）

* `email` に `UNIQUE` 制約があるため、同じメールで `INSERT` するとエラー（`Duplicate entry`）になる。会員登録処理では事前に存在チェック or 例外ハンドリングが必要。
* `role_id` は外部キーのため、`roles` テーブルが先に作成されている必要がある（schema.sql）。
* `stripe_customer_id` に `UNIQUE` がついている。NULL が許される場合（初期は未登録）でも、DB の挙動に注意（MySQL は NULL を複数許可するが、DB固有の挙動を確認すること）。
* `created_at` / `updated_at` を DB 側で自動更新する設計。JPA を使う場合、エンティティ側でも `@CreationTimestamp` / `@UpdateTimestamp` を使う。（二重で管理すると期待どおりの値が取れないことがあるため、実装方針を統一すること）
* `BOOLEAN` 型は MySQL 実装では `TINYINT(1)` として扱われることが多い点に注意（JDBC マッピングに影響する場合あり）。

### 4) 将来のクラスとの具体的な連携イメージ（例）

* 会員登録フロー（概要）

  1. `AuthController.register()` がリクエストを受け取る（Controller）
  2. `UserService.createUser(dto)` でバリデーション、パスワードハッシュ化、Stripe 顧客作成などを行う（Service）
  3. `UserRepository.save(userEntity)` を呼んで DB に `INSERT` する（Repository）
  4. `users` テーブルに INSERT される際、`email` が既に存在すると DB が UNIQUE 制約でエラーを返す → Service/Controllerで例外処理してユーザーに分かりやすいメッセージを返す

* 管理者が会員の権限を変える場合（例）

  * `AdminController.changeRole(userId, roleId)` → `UserService.changeRole()` → `UserRepository.findById()` で該当ユーザを取得し `user.setRole(role)` を行い `userRepository.save()`。
  * このとき DB の `role_id` が更新されて `users.role_id` に反映される。

### 5) 初学者への短い注意メモ（やってしまうとエラーになりやすい例）

* `roles` テーブルを作成する前に `users` を作成すると外部キー制約によりエラーになる（順番重要）。
* `email` の重複チェックをサーバ側で行わず、DB のエラーだけで処理するとユーザに不親切なエラーメッセージになる（事前チェック or 例外キャッチを実装）。
* カラム名と Java のフィールド名を一致させないまま `@Column` 指定し忘れると、マッピングエラーやデータの不整合が出る（例: `createdAt` と `created_at` の違い）。
* `updated_at` を DB 側で自動更新していても、App 側のエンティティに変更が反映されないケースがある（`@Transactional` とエンティティの再取得（refresh）を検討する）。

---