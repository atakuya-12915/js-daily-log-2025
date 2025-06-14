## この学習ログの目的
* ①振り返り用：1週間ごとにまとめて読んで自身の成長を可視化
* ②言語化：どんな力を意識して伸ばしたかを言語化
* ③モチベーション維持：進捗を可視化することで学習を継続しやすくする

## 今日の学習内容
⚙️言語 Java

🕐学習時間
📅 日付　2025/06/14
開始時刻　①8:00、②15:00
終了時刻　①10:00、②20:00
合計学習時間(h)　7.0

🎯本日の学習目標
・コレクション（list,map）を理解する
・継承を使って家族を紹介するコードを実装する

📝学習内容
・JavaでArrayListとHashMapの実践コードを作成
・ChatGPTで理解度チェック：「listとmapの違いを説明した」

💡 気づき・理解できたこと
①コレクションは、データを集めて扱えるようにしたもの（配列・オブジェクトも含む広義）
  ・クラス<型指定>の書式(=ジェネリクス)で指定した型専用のオブジェクトを作成できる
  ・ArrayList<型指定> 変数 = new ArrayList<型指定>();
  ・HashMap<キーの型,値の型> 変数 = new HashMap<キーの型,値の型>();
②listは自動で[0から]要素番号が振られるが、mapは[キー]と値が紐づけて保持される
  ・list：配列 / map：オブジェクトのイメージ
  ・要素を扱うメソッドの種類
  　データの追加/削除
    　listは、変数名.add(引数); / 変数名.remove(引数);
    　mapは、変数名.put(キー,値); / 変数名.remove(キー);
    ⚠️mapは最初に[キー,値]で紐づけているため、削除後も[キー,null]で残る
  　要素の取得
      listは、変数名.get(要素番号);
      mapは、変数名.get(キー);

❓難しかったこと・疑問
Q. 呼び出し時に引数定義がなくエラーとなった、なぜ？
A. メソッドの定義の時に引数の型を指定していた。引数なしで定義すればメソッド();でOK

📤 アウトプットしたこと
・ChatGPTに「コレクションとクラスの理解度チェック」をしてもらった
・GitHubにデイリー学習ログをpush（#今日の積み上げ）

💬 ChatGPTへのプロンプト（記録用）
・Javaの配列、オブジェクト、コレクションの使い分けを中学生にもわかるように説明して
・ListとMapの違いを明確に説明して

🌱 明日の目標
・クラスの練習（文字列、数値、日時）
・辞書アプリの作成練習

📈 学習の満足度（1〜5）
☐ 1 ☐ 2 ☐ 3 ☐ 4 ◽️ 5
（理由：＿コレクションについて理解できた。継承を使ってエラーを解決しながらコーディングができた。）
