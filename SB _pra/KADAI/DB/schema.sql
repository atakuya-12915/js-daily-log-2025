CREATE TABLE IF NOT EXISTS roles ( -- rolesテーブルを作成。アプリで使う権限（例: ROLE_USER, ROLE_ADMIN）を格納する。
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 主キー。自動採番される整数ID。
   name VARCHAR(50) NOT NULL -- ロール名（例: ROLE_USER）。NULL不可。
);

CREATE TABLE IF NOT EXISTS users ( -- usersテーブルを作成。会員（ユーザ）情報を格納。
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 主キー。ユーザを一意に識別するID。
   name VARCHAR(50) NOT NULL, -- 表示名（必須）。
   furigana VARCHAR(50) NOT NULL, -- ふりがな（必須）。検索や表示補助に使用。
   postal_code VARCHAR(50) NOT NULL, -- 郵便番号。⚠️ハイフンや国際形式を考慮してVARCHARにする。
   address VARCHAR(255) NOT NULL, -- 住所。最大255文字。
   phone_number VARCHAR(50) NOT NULL, -- 電話番号。⚠️国番号や記号を含めるためVARCHAR。
   birthday DATE, -- 生年月日（任意）。NULL可。
   occupation VARCHAR(50), -- 職業（任意）。NULL可。
   email VARCHAR(255) NOT NULL UNIQUE, -- メールアドレス（認証用）。⚠️重複禁止のUNIQUE制約。
   password VARCHAR(255) NOT NULL, -- パスワード（⚠️ハッシュ化した文字列を格納する想定）。
   role_id INT NOT NULL, -- roles テーブルの id を参照する外部キー（⚠️ユーザの権限を指定）。
   enabled BOOLEAN NOT NULL, -- アカウント有効フラグ（メール確認や管理者停止に使用）。
   stripe_customer_id VARCHAR(255) UNIQUE, -- Stripe 顧客ID（有料プラン用）。⚠️一意制約あり。
   created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 作成日時。⚠️デフォルトで現在時刻を設定。
   updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新日時。⚠️行更新時自動更新。
   FOREIGN KEY (role_id) REFERENCES roles (id) -- role_id が roles.id を参照する外部キー制約。
);

-- 基本的な注意事項
   -- role_id は 外部キー のため、参照先のroleテーブルから定義すること

-- 制約
   -- NOT NULL ・・・必須項目
   -- UNIQUE・・・・・重複禁止🈲　（同じ email で INSERT すると、エラー：Duplicate entry になる）

