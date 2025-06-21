## この学習ログの目的
* ①振り返り用：1週間ごとにまとめて読んで自身の成長を可視化
* ②言語化：どんな力を意識して伸ばしたかを言語化
* ③モチベーション維持：進捗を可視化することで学習を継続しやすくする

## 今日の学習内容
⚙️言語 Java

🕐学習時間
📅 日付　2025/06/22
開始時刻　0:00
終了時刻　2:30
合計学習時間(h)　2.5

🎯本日の学習目標
・SQLでコマンド入力をやってみる

📝学習内容
・データベースの作成方法を学ぶ

💡 気づき・理解できたこと
・テーブル作成/削除/データの取得/追加は、データベースファイルでクエリ実行する
・評価：式の値を計算すること（TRUE or FALSEのどちらであるかを判定すること）

・オプション
  SQL文	        意味
CREATE TABLE：「テーブルを作成せよ」という意味の命令文。
IF NOT EXISTS：「もし存在しなければ」という条件をつけるオプション。今回の場合は、「もしusersという名前のテーブルが存在しなければ、それを作成せよ」という意味になる。
INT、VARCHAR：データ型。
AUTO_INCREMENT：カラムの値を自動増加に設定するオプション。データを追加する際に値を入れなかった場合、「1、2、3……」のようにデータベース側が自動的に番号を割り当ててくれる。
PRIMARY KEY：カラムにプライマリーキー（主キー）を設定するオプション。テーブル内のデータ（レコード、テーブルの横1行）を一意に識別するために設定する（主キーがないと、例えば商品名や価格など全く同じデータが2つ存在する場合に区別できなくなってしまう）。ユーザーIDや社員番号など一意、つまり重複する値が存在しないカラムに設定される。
NOT NULL：NULL（データが何もない状態）を禁止するオプション。NOT NULLを設定した場合、そのカラムに値を入れないままデータを追加しようとすると、エラーが発生する。

# 曖昧検索のワイルドカード文字と意味
%	    0文字以上の任意の文字列
_	    任意の1文字

# サンプルコード
1. データベース・テーブルの作成（CREATE文）
  ```
  CREATE DATABASE データベース名;
  ```
  ⚠️1:データベースを作成したらすぐに照合順序を設定する（意図しない順番でデータが取得されないために）
  ⚠️2:照合順序をutf8mb4-general-ciまたはutf8-general-ciのどちらかに設定する（Webアプリ開発）

2. テーブル内の任意のカラムからデータを取得する（SELECT文）
    ```
    SELECT カラム名 FROM データベース名;
    ```
3. データの追加（INSERT文）
  テーブルに任意のデータを追加する
    ```
    INSERT INTO テーブル名（カラム名1、カラム名2・・・）※テーブルのカラム順に記述
    ```
4. データの更新（UPDATE文）
  テーブルの任意のデータを更新する
    ```
    UPDATE テーブル名 SET カラム名1=**, カラム名2=**,・・・ WHERE id = (対象のid番号)
    ```
5. データの確認
  登録されたデータを確認する
    ```
    SELECT * FROM users;
    ```
  ⭐️指定範囲内のデータを確認する場合：Between演算子
  ① カラムの値が下限値以上、上限値以下のデータを抽出する
    ```
    SELECT カラム名 FROM テーブル名 WHERE カラム名 BETWEEN 下限値 AND 上限値;
    SELECT * FROM users WHERE age 30 AND 40;
    ```
  ② カラムの値がリスト（複数の値）のうちいずれかに一致するデータを抽出する
    ```
    SELECT カラム名 FROM テーブル名 WHERE カラム名 IN (値1, 値2, ......);
    SELECT * FROM users WHERE age IN (5, 15, 25, 35, 45, 55, 65, 75);
    ```
  
  ③ 指定した文字列であいまい検索を行い、一致するデータを取得する
    ```
    SELECT カラム名 FROM テーブル名 WHERE カラム名 LIKE '検索文字列';
    SELECT * FROM users WHERE name LIKE '%八%';
    ```
  ④ 取得するデータ数の上限を設定する
    ```
    SELECT カラム名 FROM テーブル名 LIMIT 最大件数;
    SELECT * FROM users LIMIT 10;
    ```

  ⑤ 取得するデータを並び替え
    特定のカラム(複数指定可)を昇順（ASC）または降順（DESC）で並び替え、その順番でデータを取得する
    ```
    SELECT 取得するカラム名 FROM テーブル名 ORDER BY 並び替えるカラム名 並べ方;
    SELECT * FROM users ORDER BY age ASC/DESC;
    SELECT 取得するカラム名 FROM テーブル名 ORDER BY 並び替えるカラム名1 並べ方, 並び替えるカラム名2 並べ方, ……;
    SELECT * FROM users ORDER BY age DESC, furigana ASC;
    ```

  ⑥ 条件に一致するレコード数をカウントする
    ```
    SELECT COUNT(*) FROM テーブル名;
    ```
    ・COUNT(*)：NULLかどうかにかかわらず、すべてのレコード数を取得する
    ・COUNT(カラム名)：NULLを除いたレコード数を取得する

6. データの削除
  対象のレコード(id指定)のみ削除
  ```
  DELETE FROM users　WHERE id = ◯;
  ```
  データベース全体の削除
  ```
  DROP DATABASE データベース名;
  ```

7. 計算
  ① 指定したカラムの合計値を取得する
    ```
    SELECT SUM(カラム名) FROM テーブル名;
    SELECT SUM(age) FROM users;
    ```
  
  ② 指定したカラムの平均値を取得する
    ```
    SELECT AVG(カラム名) FROM テーブル名;
    SELECT AVG(age) FROM users;
    ```
  
  ③ データの最大値や最小値を取得する
    ```
    SELECT MAX/MIN(カラム名) FROM テーブル名;
    SELECT MAX/MIN(age) FROM users;
    ```

8. 指定したカラムでグループ化する
  ① カラムの値が同じレコードをグループ化する
    ```
    SELECT COUNT(*) FROM users GROUP BY address;
    ```
    ⚠️このままでは、「何が」addressでグループ化されたかの「相関」がわからない。
    　→SELECT カラム名　で先に指定すると可視化が向上する
    ```
    SELECT カラム名, COUNT(*) FROM users GROUP BY address;
    SELECT address, COUNT(*) FROM users GROUP BY address;
    ```
  
  ② グループ化したデータの絞り込み
    ```
    SELECT カラム名 FROM テーブル名 GROUP BY カラム名1, カラム名2, ...... HAVING 条件式;
    SELECT address, COUNT(*) FROM users GROUP BY address HAVING address = '神奈川県';
    ```


❓難しかったこと・疑問
Q. 
A. 

📤 アウトプットしたこと
・GitHubに学習ログpush（#今日の積み上げ）

🌱 明日の目標
・教材学習（SQL）を進める

📈 学習の満足度（1〜5）
☐ 1 ☐ 2 ☐ 3 ◽️ 4 ☐ 5
（理由：PHPmyAdminでSQLコマンド操作を実際に触れて学ぶことができた）