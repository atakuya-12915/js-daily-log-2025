## 効率的に学習を進めるために（「文法→目的→実装→振り返り」ループ）
  🔍 逆引き構文リスト	「やりたいこと → 使う構文」で整理
  📝 ミニ課題集	1日1〜2問で「組み立て」の練習
  📄 コード説明ワークシート	自分のコードを分解・説明する練習
  📌 自問テンプレ：
    なぜこの構文を使ったの？
    条件の数が多い時、他の方法は？
    これを関数やクラスで再利用できない？


| やりたいこと            | 使う構文・機能                      | 備考・使用例                                          |
| ----------------- | ---------------------------- | ----------------------------------------------- |
| 条件によって処理を分けたい     | `if / else if / else`        | `if (条件) {...} else {...}`                      |
| 複数の固定選択肢を分岐したい    | `switch`                     | `switch (値) { case 'A': ... }`                  |
| 一定回数、繰り返したい       | `for`                        | `for (let i = 0; i < 5; i++) {...}`             |
| 条件が成り立つ間、繰り返したい   | `while` / `do...while`       | `while (条件) {...}`                              |
| 配列の全要素に処理したい      | `forEach` / `map`            | `array.forEach(el => {...})`                    |
| 条件を満たす配列要素を取り出したい | `filter`                     | `array.filter(el => 条件)`                        |
| 要素を1つ見つけたい        | `find`                       | `array.find(el => 条件)`                          |
| 合計値などを計算したい       | `reduce`                     | `array.reduce((acc, el) => ..., 初期値)`           |
| HTMLの要素を取得したい     | `document.getElementById` など | `document.querySelector('.class')`              |
| ユーザー操作に反応したい      | `addEventListener`           | `button.addEventListener('click', () => {...})` |
| データのまとまりを定義したい    | `オブジェクト / 配列`                | `{ name: "John", age: 30 }`                     |
| 同じ処理を繰り返し使いたい     | `関数 function / アロー関数`        | `const greet = (name) => {...}`                 |
| クラスのような設計図を使いたい   | `class`                      | `class Dog { constructor(name) {...} }`         |


## JavaScript練習問題
ステップ1：条件分岐の基礎
📝【問題1】年齢が20歳以上なら「成人です」と表示したい。
ゴール：if文が使えるようにする。
使用構文：if / else

📝【問題2】点数に応じて評価を表示したい（80点以上はA、それ以下はB）
ゴール：if / else if / elseの基本理解。
使用構文：if / else if

ステップ2：繰り返しと配列操作
📝【問題3】5回「こんにちは」と表示するコードを書いてみよう
ゴール：for文の基本。
使用構文：for

📝【問題4】配列 ["りんご", "バナナ", "みかん"] を1つずつ表示したい
ゴール：forEachの使い方。
使用構文：forEach

ステップ3：ユーザー操作とDOM操作
📝【問題5】ボタンをクリックしたら「クリックされました」と表示
ゴール：addEventListenerの基本。
使用構文：document.getElementById, addEventListener

📝【問題6】テキストボックスに入力された値を表示する
ゴール：入力値の取得・表示。
使用構文：value, textContent

## 実装課題(じゃんけんアプリ)
課題内容
「グー」「チョキ」「パー」ボタンをクリックすると、ランダムにコンピューターの手と勝敗が表示される。

(ヒント)
・ユーザーの手：ボタンのidを使って判定
・コンピューターの手：Math.random()で配列からランダム選択
・勝敗判定：ifで全パターン分岐

【解答】
<button id="rock">グー</button>
<button id="scissors">チョキ</button>
<button id="paper">パー</button>
<p id="result"></p>

<script>
  const hands = ["rock", "scissors", "paper"];
  const resultText = document.getElementById("result");

  document.querySelectorAll("button").forEach(btn => {
    btn.addEventListener("click", () => {
      const userHand = btn.id;
      const computerHand = hands[Math.floor(Math.random() * 3)];

      let result = "";
      if (userHand === computerHand) {
        result = "あいこ";
      } else if (
        (userHand === "rock" && computerHand === "scissors") ||
        (userHand === "scissors" && computerHand === "paper") ||
        (userHand === "paper" && computerHand === "rock")
      ) {
        result = "あなたの勝ち！";
      } else {
        result = "あなたの負け！";
      }

      resultText.textContent = `あなた: ${userHand} / 相手: ${computerHand} → ${result}`;
    });
  });
</script>